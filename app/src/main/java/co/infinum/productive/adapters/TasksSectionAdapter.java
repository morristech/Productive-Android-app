package co.infinum.productive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksSectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SECTION_TYPE = 0;
    private boolean mValid = true;
    private int mSectionResourceId;
    private Context mContext;
    private RecyclerView.Adapter mBaseAdapter;
    private SparseArray<TaskSection> mSections = new SparseArray<>();
    private TaskSection[] sections;

    public TasksSectionAdapter(int mSectionResourceId, Context mContext, RecyclerView.Adapter mBaseAdapter) {
        this.mSectionResourceId = mSectionResourceId;
        this.mContext = mContext;
        this.mBaseAdapter = mBaseAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_TYPE) {
            return new TaskSectionViewHolder(LayoutInflater.from(mContext).inflate(mSectionResourceId, parent, false));
        } else {
            return mBaseAdapter.onCreateViewHolder(parent, viewType - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isSectionHeaderPosition(position)) {
            ((TaskSectionViewHolder) holder).title.setText(mSections.get(position).title);
        } else {
            mBaseAdapter.onBindViewHolder(holder, sectionedPositionToPosition(position));
        }
    }

    @Override
    public int getItemCount() {
        return mValid ? mBaseAdapter.getItemCount() + mSections.size() : 0;
    }


    @Override
    public int getItemViewType(int position) {
        return isSectionHeaderPosition(position) ? SECTION_TYPE : mBaseAdapter.getItemViewType(sectionedPositionToPosition(position)) + 1;
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

        Arrays.sort(sections, new Comparator<TaskSection>() {
            @Override
            public int compare(TaskSection o, TaskSection o1) {
                return o.firstPosition == o1.firstPosition ? 0 : o.firstPosition < o1.firstPosition ? -1 : 1;
            }
        });

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

    @Override
    public long getItemId(int position) {
        return isSectionHeaderPosition(position)
                ? Integer.MAX_VALUE - mSections.indexOfKey(position)
                : mBaseAdapter.getItemId(sectionedPositionToPosition(position));
    }

    public static class TaskSectionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tasks_section_title)
        TextView title;

        public TaskSectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
    }
}
