package co.infinum.productive.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SECTION_TYPE = 0;
    public static final int TILE_TYPE = 1;
    public static final String REPLACE_ALL_REGULAR_EXPRESSION = "\\D+";

    private Context context;
    private SparseArray<ProjectSection> mSections = new SparseArray<>();
    private ProjectSection[] projectSections;
    private OnProjectClickListener listener;
    private ArrayList<ProjectTile> projectTiles;
    private Resources res;

    public ProjectAdapter(Context context, Resources res, ArrayList<ProjectTile> projectTiles, OnProjectClickListener listener) {
        this.context = context;
        this.res = res;
        this.projectTiles = projectTiles;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
        if (typeView == SECTION_TYPE) {
            return new ProjectSectionViewHolder(LayoutInflater.from(context).inflate(R.layout.projects_list_item_separator, parent, false));
        } else {
            return new ProjectViewHolder(LayoutInflater.from(context).inflate(R.layout.projects_list_item, parent, false), listener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isSectionHeaderPosition(position)) {
            bindSectionViewHolder((ProjectSectionViewHolder) holder, position);
        } else {
            bindProjectViewHolder((ProjectViewHolder) holder, sectionedPositionToPosition(position));
        }
    }

    private void bindSectionViewHolder(ProjectSectionViewHolder holder, int position) {
        holder.title.setText(mSections.get(position).title);
    }

    private void bindProjectViewHolder(ProjectViewHolder holder, int position) {
        holder.title.setText(projectTiles.get(position).getProjectName());

        String updateInfo;
        String updatedBy = projectTiles.get(position).getUpdatedBy();
        String elapsedTime = projectTiles.get(position).getElapsedTime();

        if (Integer.parseInt(elapsedTime.replaceAll(REPLACE_ALL_REGULAR_EXPRESSION, "")) != 1) {
            updateInfo = res.getQuantityString(R.plurals.elapsed_time_text, 2, elapsedTime, updatedBy);
        } else {
            updateInfo = res.getQuantityString(R.plurals.elapsed_time_text, 1, elapsedTime, updatedBy);
        }

        holder.description.setText(updateInfo);

        Glide.with(context).load(projectTiles.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        if (isSectionHeaderPosition(position + getSectionOffset(position + 1))
                || position == projectTiles.size() - 1) {
            holder.contentLayout.setBackground(null);
        } else {
            holder.contentLayout.setBackgroundResource(R.drawable.item_card_border);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isSectionHeaderPosition(position) ? SECTION_TYPE : TILE_TYPE;
    }

    // used for removing the last border on an item
    public int getSectionOffset(int position) {
        int offset = 0;

        for (ProjectSection projectSection : projectSections) {
            if (projectSection.firstPosition > position) {
                break;
            }

            ++offset;
        }

        return offset;
    }

    public void setProjectSections(ProjectSection[] projectSections) {
        this.projectSections = projectSections;

        mSections.clear();

        int offset = 0; // offset positions for the headers we're adding

        for (ProjectSection projectSection : projectSections) {
            projectSection.sectionedPosition = projectSection.firstPosition + offset;
            mSections.append(projectSection.sectionedPosition, projectSection);
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

    @Override
    public int getItemCount() {
        return projectTiles.size() + mSections.size();
    }

    public void refresh(ArrayList<ProjectTile> projectTiles) {
        this.projectTiles.clear();
        this.projectTiles.addAll(projectTiles); //memory efficient, we're always updating the initial List
        notifyDataSetChanged();
    }

    public ProjectTile getItem(int position) {
        return projectTiles.get(position);
    }

    public class ProjectSectionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.projects_section_title)
        TextView title;

        public ProjectSectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.projects_item_title)
        TextView title;

        @Bind(R.id.projects_item_description)
        TextView description;

        @Bind(R.id.item_thumbnail)
        ImageView thumbnail;

        @Bind(R.id.content_layout)
        LinearLayout contentLayout;

        private OnProjectClickListener listener;

        public ProjectViewHolder(View v, OnProjectClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onProjectsClick(sectionedPositionToPosition(getAdapterPosition()));
        }
    }

    public static class ProjectSection {

        int firstPosition;
        int sectionedPosition;
        String title;

        public ProjectSection(int firstPosition, String title) {
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
