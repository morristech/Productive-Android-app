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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.listeners.OnProjectClickListener;
import co.infinum.productive.models.ProjectTile;

/**
 * Created by mjurinic on 09.11.15..
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.SimpleViewHolder> {

    private Context mContext;
    private ArrayList<ProjectTile> projectTiles;
    private Resources res;
    private OnProjectClickListener listener;

    public ProjectAdapter(Context context, Resources res, ArrayList<ProjectTile> projectTiles, OnProjectClickListener listener) {
        mContext = context;
        this.res = res;
        this.projectTiles = projectTiles;
        this.listener = listener;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(projectTiles.get(position).getProjectName());

        String updateInfo = "";
        String updatedBy = projectTiles.get(position).getUpdatedBy();
        String elapsedTime = projectTiles.get(position).getElapsedTime();

        if (Integer.parseInt(elapsedTime.replaceAll("\\D+", "")) != 1) {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy));
        } else {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy));
        }

        holder.description.setText(updateInfo);

        Glide.with(mContext).load(projectTiles.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onProjectsClick(projectTiles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectTiles.size();
    }

    public void refresh(ArrayList<ProjectTile> projectTiles) {
        this.projectTiles.clear();
        this.projectTiles.addAll(projectTiles); //memory efficient, we're always updating the initial List
        notifyDataSetChanged();
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
