package co.infinum.productive.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.adapters.SimpleAdapter;
import co.infinum.productive.adapters.SimpleSectionedRecyclerViewAdapter;
import co.infinum.productive.dagger.components.DaggerProjectsFragmentComponent;
import co.infinum.productive.dagger.modules.ProjectsFragmentModule;
import co.infinum.productive.models.Project;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.views.ProjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsFragment extends BaseFragment implements ProjectView {

    @Inject
    ProjectPresenter projectPresenter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);

        DaggerProjectsFragmentComponent.builder()
                .projectsFragmentModule(new ProjectsFragmentModule(this))
                .build()
                .inject(this);

        initRecyclerView();

        projectPresenter.getProjects();

        return view;
    }

    @Override
    public void onSuccess() {
        initAdapters();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(super.context));
    }

    private void initAdapters() {
        ArrayList<Project> projects = ProductiveApp.getInstance().getCacheInteractor().getProjects();
        ArrayList<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();

        mAdapter = new SimpleAdapter(super.context, projects);

        // calculate each clients project count
        String prevClientName = "";
        int overallOffset = 0;
        int currentOffset = 0;

        for (int i = 0; i < projects.size(); ++i) {
            String currClientName = projects.get(i).getClient().getName();

            if (currClientName.equals(prevClientName)) {
                ++currentOffset;
            } else {
                overallOffset += currentOffset;
                sections.add(new SimpleSectionedRecyclerViewAdapter.Section(overallOffset, currClientName));
                currentOffset = 1;
            }

            prevClientName = currClientName;
        }

        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];

        SimpleSectionedRecyclerViewAdapter mSectionAdapter =
                new SimpleSectionedRecyclerViewAdapter(super.context, R.layout.list_section_separator, R.id.section_title, mAdapter);

        mSectionAdapter.setSections(sections.toArray(dummy));

        mRecyclerView.setAdapter(mSectionAdapter);
    }
}
