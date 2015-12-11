package co.infinum.productive.adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
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
import co.infinum.productive.models.Task;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private Context mContext;

    private ArrayList<Task> tasks;

    private Resources res;

    private TasksSectionAdapter tasksSectionAdapter;


    public TasksAdapter(Context mContext, ArrayList<Task> tasks, Resources res) {
        this.mContext = mContext;
        this.tasks = tasks;
        this.res = res;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TasksViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tasks_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        holder.tasksItemTitle.setText(tasks.get(position).getTitle());

        String updateInfo;
        String updatedBy;

        updatedBy = "" + tasks.get(position).getUpdater().getName();

        String elapsedTime = ElapsedTimeFormatter.getElapsedTime(tasks.get(position).getUpdatedAt(), res);

        if (Integer.parseInt(elapsedTime.replaceAll("\\D+", "")) != 1) {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy));
        } else {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy));
        }

        holder.tasksItemDescription.setText(updateInfo);
        if (tasks.get(position).getUpdater().getName() != null) {
            Glide.with(mContext).load(tasks.get(position).getUpdater().getAvatarUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.itemThumbnail);
        } else {
            //TODO replace this image with a no avatar image
            Glide.with(mContext).load(R.drawable.ic_action_android)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.itemThumbnail);
        }

        if (tasksSectionAdapter.isSectionHeaderPosition(position + tasksSectionAdapter.getSectionOffset(position + 1))
                || position == tasks.size() - 1) {
            holder.tasksContentLayout.setBackground(null);
        } else {
            holder.tasksContentLayout.setBackgroundResource(R.drawable.item_card_border);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasksSectionAdapter(TasksSectionAdapter tasksSectionAdapter) {
        this.tasksSectionAdapter = tasksSectionAdapter;
    }

    public void refresh(ArrayList<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);

        notifyDataSetChanged();
    }


    public class TasksViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tasks_item_title)
        TextView tasksItemTitle;

        @Bind(R.id.tasks_item_description)
        TextView tasksItemDescription;

        @Bind(R.id.tasks_content_layout)
        LinearLayout tasksContentLayout;

        @Bind(R.id.item_thumbnail)
        CircleImageView itemThumbnail;

        public TasksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
