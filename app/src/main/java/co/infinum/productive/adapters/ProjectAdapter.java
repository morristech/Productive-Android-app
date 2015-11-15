package co.infinum.productive.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.joda.time.DateTime;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.listeners.OnProjectClickListener;
import co.infinum.productive.models.Project;

/**
 * Created by mjurinic on 09.11.15..
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.SimpleViewHolder> {

    private Context mContext;
    private ArrayList<Project> projects;
    private Resources res;
    private OnProjectClickListener listener;

    public ProjectAdapter(Context context, Resources res, ArrayList<Project> projects, OnProjectClickListener listener) {
        mContext = context;
        this.res = res;
        this.projects = projects;
        this.listener = listener;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(projects.get(position).getName());

        String updateInfo = "";
        String updatedBy = projects.get(position).getProjectManager().getName();

        //TODO add TasksInteractor & TaskDetailsInteractor in order to get the right person
        String elapsedTime = getElapsedTime(projects.get(position).getUpdatedAt());

        if (Integer.parseInt(elapsedTime.replaceAll("\\D+", "")) != 1) {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy));
        } else {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy));
        }

        holder.description.setText(updateInfo);

        Glide.with(mContext).load(projects.get(position).getClient().getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onProjectsClick(projects.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void refresh(ArrayList<Project> projects) {
        this.projects.clear();
        this.projects.addAll(projects); //memory efficient, we're always updating the initial List
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

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_title)
        TextView title;

        @Bind(R.id.item_description)
        TextView description;

        @Bind(R.id.item_thumbnail)
        ImageView thumbnail;

        public SimpleViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
