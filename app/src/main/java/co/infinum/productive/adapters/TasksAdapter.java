package co.infinum.productive.adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.helpers.ElapsedTimeFormatter;
import co.infinum.productive.listeners.OnTasksClickListener;
import co.infinum.productive.models.Task;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SECTION_TYPE = 0;

    public static final int TILE_TYPE = 1;

    public static final String REPLACE_ALL_REGEX = "\\D+";

    private Context mContext;

    private SparseArray<TaskSection> mSections = new SparseArray<>();

    private TaskSection[] sections;

    private OnTasksClickListener listener;

    private Resources res;

    private ArrayList<Task> tasks;

    public TasksAdapter(Context mContext, Resources res, ArrayList<Task> taskList, OnTasksClickListener listener) {
        this.mContext = mContext;
        this.res = res;
        this.tasks = taskList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_TYPE) {
            return new TaskSectionViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tasks_list_item_separator, parent, false));
        } else {
            return new TaskViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tasks_list_item, parent, false), listener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isSectionHeaderPosition(position)) {
            bindSectionViewHolder((TaskSectionViewHolder) holder, position);
        } else {
            bindTaskViewHolder((TaskViewHolder) holder, sectionedPositionToPosition(position));
        }
    }

    private void bindSectionViewHolder(TaskSectionViewHolder holder, int position) {
        holder.title.setText(mSections.get(position).title);
    }


    private void bindTaskViewHolder(TaskViewHolder holder, int position) {
        holder.tasksItemTitle.setText(tasks.get(position).getTitle());

        String updateInfo;
        String updatedBy;

        updatedBy = tasks.get(position).getUpdater().getName();

        String elapsedTime = ElapsedTimeFormatter.getElapsedTime(tasks.get(position).getUpdatedAt(), res);

        if (Integer.parseInt(elapsedTime.replaceAll(REPLACE_ALL_REGEX, "")) != 1) {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy));
        } else {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy));
        }

        holder.tasksItemDescription.setText(updateInfo);

        if (tasks.get(position).getUpdater().getName() != null) {
            Glide.with(mContext)
                    .load(tasks.get(position).getUpdater().getAvatarUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.itemThumbnail);
        } else {
            //TODO replace this image with a no avatar image
            Glide.with(mContext)
                    .load(R.drawable.ic_action_android)
                    .into(holder.itemThumbnail);
        }

        if (isSectionHeaderPosition(position + getSectionOffset(position + 1)) || position == tasks.size() - 1) {
            holder.tasksContentLayout.setBackground(null);
        } else {
            holder.tasksContentLayout.setBackgroundResource(R.drawable.item_card_border);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size() + mSections.size();
    }

    @Override
    public int getItemViewType(int position) {
        return isSectionHeaderPosition(position) ? SECTION_TYPE : TILE_TYPE;
    }

    // used for removing the last border on an item
    public int getSectionOffset(int position) {
        int offset = 0;

        for (TaskSection section : sections) {
            if (section.firstPosition > position) {
                break;
            }

            ++offset;
        }

        return offset;
    }

    public void setSections(TaskSection[] sections) {
        this.sections = sections;

        mSections.clear();

        int offset = 0; // offset positions for the headers we're adding

        for (TaskSection section : sections) {
            section.sectionedPosition = section.firstPosition + offset;
            mSections.append(section.sectionedPosition, section);
            ++offset;
        }

        notifyDataSetChanged();
    }

    public int sectionedPositionToPosition(int sectionedPosition) {
        if (isSectionHeaderPosition(sectionedPosition)) {
            return RecyclerView.NO_POSITION;
        }

        int offset = 0;

        for (int i = 0; i < mSections.size(); ++i) {
            if (mSections.valueAt(i).sectionedPosition > sectionedPosition) {
                break;
            }

            --offset;
        }

        return sectionedPosition + offset;
    }

    public boolean isSectionHeaderPosition(int position) {
        return mSections.get(position) != null;
    }

    public void refresh(ArrayList<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks); //memory efficient, we're always updating the initial List
        notifyDataSetChanged();
    }

    public Task getItem(int position) {
        return tasks.get(position);
    }

    public static class TaskSectionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.section_title)
        TextView title;

        public TaskSectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tasks_item_title)
        TextView tasksItemTitle;

        @Bind(R.id.tasks_item_description)
        TextView tasksItemDescription;

        @Bind(R.id.tasks_content_layout)
        LinearLayout tasksContentLayout;

        @Bind(R.id.item_thumbnail)
        CircleImageView itemThumbnail;

        private OnTasksClickListener listener;

        public TaskViewHolder(View itemView, OnTasksClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onTasksClick(sectionedPositionToPosition(getAdapterPosition()));
        }
    }

    public static class TaskSection {

        int firstPosition;

        int sectionedPosition;

        String title;

        public TaskSection(int firstPosition, String title) {
            this.firstPosition = firstPosition;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public int getFirstPosition() {
            return firstPosition;
        }
    }
}
