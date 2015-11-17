package co.infinum.productive.adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.joda.time.DateTime;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.models.Task;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private Context mContext;

    private ArrayList<Task> tasks;

    private Resources res;


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

        String updateInfo = "";
        String updatedBy;
        if (tasks.get(position).getAssignee() != null) {
            updatedBy = "" + tasks.get(position).getAssignee().getName();
        } else {
            updatedBy = "";
        }

        String elapsedTime = getElapsedTime(tasks.get(position).getUpdatedAt());

        if (Integer.parseInt(elapsedTime.replaceAll("\\D+", "")) != 1) {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy));
        } else {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy));
        }

        holder.tasksItemDescription.setText(updateInfo);
        if (tasks.get(position).getAssignee().getAvatarUrl() != null) {
            Glide.with(mContext).load(tasks.get(position).getAssignee().getAvatarUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.itemThumbnail);
        } else {
            //TODO replace this image with a no avatar image
            Glide.with(mContext).load(R.drawable.ic_action_android)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.itemThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void refresh(ArrayList<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks); //memory efficient, we're always updating the initial List

        for (int i = 0; i < tasks.size(); ++i) {
            Log.d("DEBUG", tasks.get(i).getProjectName());
        }

        notifyDataSetChanged();
    }

    private String getElapsedTime(DateTime updatedAt) {
        DateTime currentTime = new DateTime();
        String ret = "";

        int years = Math.abs(currentTime.getYear() - updatedAt.getYear());
        int months = Math.abs(currentTime.getMonthOfYear() - updatedAt.getMonthOfYear());
        int days = Math.abs(currentTime.getDayOfMonth() - updatedAt.getDayOfMonth());
        int hours = Math.abs(currentTime.getHourOfDay() - updatedAt.getHourOfDay());
        int minutes = Math.abs(currentTime.getMinuteOfHour() - updatedAt.getMinuteOfHour());
        int seconds = Math.abs(currentTime.getSecondOfMinute() - updatedAt.getSecondOfMinute());

        if (years != 0) {
            ret += years + res.getString(R.string.year_text);
        } else if (months != 0) {
            ret += months + res.getString(R.string.month_text);
        } else if (days != 0) {
            ret += days + res.getString(R.string.day_text);
        } else if (hours != 0) {
            ret += hours + res.getString(R.string.hour_text);
        } else if (minutes != 0) {
            ret += minutes + res.getString(R.string.minute_text);
        } else if (seconds != 0) {
            ret += seconds + res.getString(R.string.second_text);
        }

        return ret;
    }

    public class TasksViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tasks_item_title)
        TextView tasksItemTitle;

        @Bind(R.id.tasks_item_description)
        TextView tasksItemDescription;

        @Bind(R.id.tasks_content_layout)
        LinearLayout tasksContentLayout;

        @Bind(R.id.tasks_arrow_right)
        ImageView tasksArrowRight;

        @Bind(R.id.item_thumbnail)
        CircleImageView itemThumbnail;

        public TasksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
