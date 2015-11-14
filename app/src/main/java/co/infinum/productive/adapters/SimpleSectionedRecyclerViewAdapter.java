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
 * Created by mjurinic on 09.11.15..
 */
public class SimpleSectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SECTION_TYPE = 0;

    private boolean mValid = true;
    private int mSectionResourceId;
    private Context mContext;
    private RecyclerView.Adapter mBaseAdapter;
    private SparseArray<Section> mSections = new SparseArray<>();

    public SimpleSectionedRecyclerViewAdapter(Context context, int sectionResourceId, RecyclerView.Adapter baseAdapter) {
        mSectionResourceId = sectionResourceId;
        mBaseAdapter = baseAdapter;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
        if (typeView == SECTION_TYPE) {
            return new SectionViewHolder(LayoutInflater.from(mContext).inflate(mSectionResourceId, parent, false));
        } else {
            return mBaseAdapter.onCreateViewHolder(parent, typeView - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder sectionViewHolder, int position) {
        if (isSectionHeaderPosition(position)) {
            ((SectionViewHolder) sectionViewHolder).title.setText(mSections.get(position).title);
        } else {
            mBaseAdapter.onBindViewHolder(sectionViewHolder, sectionedPositionToPosition(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isSectionHeaderPosition(position) ? SECTION_TYPE : mBaseAdapter.getItemViewType(sectionedPositionToPosition(position)) + 1;
    }

    public void setSections(Section[] sections) {
        mSections.clear();

        Arrays.sort(sections, new Comparator<Section>() {
            @Override
            public int compare(Section o, Section o1) {
                return  o.firstPosition == o1.firstPosition ? 0 : o.firstPosition < o1.firstPosition ? -1 : 1;
            }
        });

        int offset = 0; // offset positions for the headers we're adding

        for (Section section : sections) {
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

    @Override
    public int getItemCount() {
        return mValid ? mBaseAdapter.getItemCount() + mSections.size() : 0;
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.section_title)
        TextView title;

        public SectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class Section {

        int firstPosition;
        int sectionedPosition;
        String title;

        public Section(int firstPosition, String title) {
            this.firstPosition = firstPosition;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
