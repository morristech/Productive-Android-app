package co.infinum.productive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.helpers.ElapsedTimeFormatter;
import co.infinum.productive.helpers.TrimHtml;
import co.infinum.productive.models.Attachment;
import co.infinum.productive.models.TaskActivityResponse;

/**
 * Created by mjurinic on 29.12.15..
 */
public class TaskActivitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String REGEX_EXTRACT_NUMBER = "\\D+";
    public static final String COMMENTED = "commented";

    private List<TaskActivityResponse> taskActivities;
    private Context context;

    public TaskActivitiesAdapter(List<TaskActivityResponse> taskActivities, Context context) {
        this.taskActivities = taskActivities;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskActivitiesViewHolder(LayoutInflater.from(context).inflate(R.layout.task_activities_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindTaskActivitiesViewHolder((TaskActivitiesViewHolder) holder, position);
    }

    private void bindTaskActivitiesViewHolder(TaskActivitiesViewHolder holder, int position) {
        holder.title.setText(taskActivities.get(position).getTitle());

        int leftPadding = holder.description.getPaddingLeft();
        int topPadding = holder.description.getPaddingTop();
        int rightPadding = holder.description.getPaddingRight();
        int bottomPadding = holder.description.getPaddingBottom();

        if (taskActivities.get(position).getTitle().contains(COMMENTED)) {
            holder.description.setBackgroundResource(R.drawable.ic_comment_background);
        } else {
            holder.description.setBackground(null);
        }

        holder.description.setText(TrimHtml.Trim(Html.fromHtml(taskActivities.get(position).getBody())));
        holder.description.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

        String updateInfo;
        String elapsedTime = ElapsedTimeFormatter.getElapsedTime(taskActivities.get(position).getCreatedAt(), context.getResources());

        if (Integer.parseInt(elapsedTime.replaceAll(REGEX_EXTRACT_NUMBER, "")) != 1) {
            updateInfo = context.getResources().getQuantityString(R.plurals.elapsed_time_text_activities, 2, elapsedTime);
        } else {
            updateInfo = context.getResources().getQuantityString(R.plurals.elapsed_time_text_activities, 1, elapsedTime);
        }

        holder.elapsedTime.setText(updateInfo);

        Glide.with(context).load(taskActivities.get(position).getPerson().getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        if (taskActivities.get(position).getAttachment() != null) {
            List<Attachment> attachments = taskActivities.get(position).getAttachment();

            holder.description.setMovementMethod(LinkMovementMethod.getInstance());

            for (int i = 0; i < attachments.size(); i++) {
                String aTag = "<a href=\"" + attachments.get(i).getUrl() + "\">" + attachments.get(i).getFileName() + "</a><br>";
                holder.description.append(Html.fromHtml(aTag));
            }
        }
    }

    @Override
    public int getItemCount() {
        return taskActivities.size();
    }

    public void refresh(List<TaskActivityResponse> taskActivities) {
        this.taskActivities.clear();
        this.taskActivities.addAll(taskActivities); //memory efficient, we're always updating the initial List
        notifyDataSetChanged();
    }

    public void appendComment(TaskActivityResponse comment) {
        taskActivities.add(0, comment);
        notifyDataSetChanged();
    }

    public class TaskActivitiesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_task_activities_thumbnail)
        ImageView thumbnail;

        @Bind(R.id.tv_task_activities_title)
        TextView title;

        @Bind(R.id.tv_task_activities_description)
        TextView description;

        @Bind(R.id.tv_task_activities_elapsed_time)
        TextView elapsedTime;

        public TaskActivitiesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
