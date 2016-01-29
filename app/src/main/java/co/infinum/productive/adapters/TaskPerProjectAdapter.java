package co.infinum.productive.adapters;

import com.bumptech.glide.Glide;

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
import co.infinum.productive.listeners.OnTasksClickListener;
import co.infinum.productive.models.Task;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by noxqs on 21.01.16..
 */
public class TaskPerProjectAdapter extends RecyclerView.Adapter<TaskPerProjectAdapter.ViewHolder> {

    private ArrayList<Task> tasks;

    private OnTasksClickListener listener;

    public static final String REPLACE_ALL_REGEX = "\\D+";

    private Context context;

    private Resources res;

    public TaskPerProjectAdapter(ArrayList<Task> tasks, Context context, OnTasksClickListener listener, Resources res) {
        this.tasks = tasks;
        this.context = context;
        this.listener = listener;
        this.res = res;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.tasks_list_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tasksItemTitle.setText(tasks.get(position).getTitle());

        String updateInfo;
        String updatedBy;

        updatedBy = tasks.get(position).getUpdater().getName();

        String elapsedTime = ElapsedTimeFormatter.getElapsedTime(tasks.get(position).getUpdatedAt(), res);

        if (Integer.parseInt(elapsedTime.replaceAll(REPLACE_ALL_REGEX, "")) != 1) {
            updateInfo = res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy);
        } else {
            updateInfo = res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy);
        }

        holder.tasksItemDescription.setText(updateInfo);

        Glide.with(context)
                .load(tasks.get(position).getUpdater().getAvatarUrl())
                .into(holder.itemThumbnail);

        if (position == tasks.size() - 1) {
            holder.tasksContentLayout.setBackground(null);
        } else {
            holder.tasksContentLayout.setBackgroundResource(R.drawable.item_card_border);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tasks_item_title)
        TextView tasksItemTitle;

        @Bind(R.id.tasks_item_description)
        TextView tasksItemDescription;

        @Bind(R.id.tasks_content_layout)
        LinearLayout tasksContentLayout;

        @Bind(R.id.item_thumbnail)
        CircleImageView itemThumbnail;

        private OnTasksClickListener listener;

        public ViewHolder(View itemView, OnTasksClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onTasksClick(getAdapterPosition());
        }
    }


}
