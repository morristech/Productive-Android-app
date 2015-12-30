package co.infinum.productive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import co.infinum.productive.models.TaskActivityResponse;

/**
 * Created by mjurinic on 29.12.15..
 */
public class TaskActivitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    //TODO srediti parsiranja razlicitih tipova aktivnosti
    //TODO prikazati attachmente
    private void bindTaskActivitiesViewHolder(TaskActivitiesViewHolder holder, int position) {
        holder.title.setText(taskActivities.get(position).getTitle());
        holder.description.setText(Html.fromHtml(taskActivities.get(position).getBody()));

        Glide.with(context).load(taskActivities.get(position).getPerson().getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);
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

    public class TaskActivitiesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_task_activities_thumbnail)
        ImageView thumbnail;

        @Bind(R.id.tv_task_activities_title)
        TextView title;

        @Bind(R.id.tv_task_activities_description)
        TextView description;

        public TaskActivitiesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
