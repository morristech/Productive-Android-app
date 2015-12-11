package co.infinum.productive.adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
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
import co.infinum.productive.listeners.OnProjectClickListener;
import co.infinum.productive.models.ProjectTile;

/**
 * Created by mjurinic on 09.11.15..
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.SimpleViewHolder> {

    public static final String REPLACE_ALL_REGULAR_EXPRESSION = "\\D+";

    private Context mContext;
    private ArrayList<ProjectTile> projectTiles;
    private Resources res;
    private OnProjectClickListener listener;
    private ProjectSectionAdapter projectSectionAdapter;

    public ProjectAdapter(Context context, Resources res, ArrayList<ProjectTile> projectTiles, OnProjectClickListener listener) {
        mContext = context;
        this.res = res;
        this.projectTiles = projectTiles;
        this.listener = listener;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.projects_list_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(projectTiles.get(position).getProjectName());

        String updateInfo;
        String updatedBy = projectTiles.get(position).getUpdatedBy();
        String elapsedTime = projectTiles.get(position).getElapsedTime();

        if (Integer.parseInt(elapsedTime.replaceAll(REPLACE_ALL_REGULAR_EXPRESSION, "")) != 1) {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy));
        } else {
            updateInfo = String.format(res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy));
        }

        holder.description.setText(updateInfo);

        Glide.with(mContext).load(projectTiles.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        if (projectSectionAdapter.isSectionHeaderPosition(position + projectSectionAdapter.getSectionOffset(position + 1))
                || position == projectTiles.size() - 1) {
            holder.contentLayout.setBackground(null);
        } else {
            holder.contentLayout.setBackgroundResource(R.drawable.item_card_border);
        }
    }

    @Override
    public int getItemCount() {
        return projectTiles.size();
    }

    public ProjectTile getItem(int position) {
        return projectTiles.get(position);
    }

    public void refresh(ArrayList<ProjectTile> projectTiles) {
        this.projectTiles.clear();
        this.projectTiles.addAll(projectTiles); //memory efficient, we're always updating the initial List
        notifyDataSetChanged();
    }

    public void setProjectSectionAdapter(ProjectSectionAdapter projectSectionAdapter) {
        this.projectSectionAdapter = projectSectionAdapter;
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.projects_item_title)
        TextView title;

        @Bind(R.id.projects_item_description)
        TextView description;

        @Bind(R.id.item_thumbnail)
        ImageView thumbnail;

        @Bind(R.id.content_layout)
        LinearLayout contentLayout;

        private OnProjectClickListener listener;

        public SimpleViewHolder(View v, OnProjectClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onProjectsClick(projectSectionAdapter.sectionedPositionToPosition(getAdapterPosition()));
        }
    }
}
