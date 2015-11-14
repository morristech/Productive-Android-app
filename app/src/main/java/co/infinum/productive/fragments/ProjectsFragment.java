package co.infinum.productive.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.adapters.ProjectAdapter;
import co.infinum.productive.adapters.ProjectSectionAdapter;
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

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.empty_projects_info)
    TextView emptyProjectsInfo;

    private RecyclerView.Adapter mAdapter;
    private ProjectSectionAdapter mSectionAdapter;
    private Context context;
    private boolean isRefreshed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);

        DaggerProjectsFragmentComponent.builder()
                .projectsFragmentModule(new ProjectsFragmentModule(this))
                .build()
                .inject(this);

        context = getActivity();

        initSwipeRefresh();
        initRecyclerView();

        projectPresenter.getProjects();

        return view;
    }

    @Override
    public void onSuccess(ArrayList<Project> projects) {
        if (projects.size() != 0) {
            mRecyclerView.bringToFront();
        } else {
            emptyProjectsInfo.bringToFront();
        }

        initAdapters(projects);

       if (isRefreshed) {
            refreshAdapters(projects);
       }
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshed = true;
                projectPresenter.getProjects();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initAdapters(ArrayList<Project> projects) {
        if (mAdapter == null) {
            mAdapter = new ProjectAdapter(context, projects);
        }

        if (mSectionAdapter == null) {
            mSectionAdapter = new ProjectSectionAdapter(context, R.layout.list_section_separator, mAdapter);
        }

        setSections(projects);

        mRecyclerView.setAdapter(mSectionAdapter);
    }

    private void setSections(ArrayList<Project> projects) {
        ArrayList<ProjectSectionAdapter.Section> sections = new ArrayList<>();

        // calculates offset for each client and sets sections
        String prevClientName = "";
        int overallOffset = 0;
        int currentOffset = 0;

        for (int i = 0; i < projects.size(); ++i) {
            String currClientName = projects.get(i).getClient().getName();

            if (currClientName.equals(prevClientName)) {
                ++currentOffset;
            } else {
                overallOffset += currentOffset;
                sections.add(new ProjectSectionAdapter.Section(overallOffset, currClientName));
                currentOffset = 1;
            }

            prevClientName = currClientName;
        }

        ProjectSectionAdapter.Section[] sectionsList = new ProjectSectionAdapter.Section[sections.size()];

        mSectionAdapter.setSections(sections.toArray(sectionsList));
    }

    private void refreshAdapters(ArrayList<Project> projects) {
        if (mAdapter != null && mSectionAdapter != null) {
            isRefreshed = false;

            setSections(projects);
            ((ProjectAdapter) mAdapter).refresh(projects);

            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
