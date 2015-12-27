package co.infinum.productive.fragments;

import android.app.Fragment;
import android.content.Intent;
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
import co.infinum.productive.activities.TasksListActivity;
import co.infinum.productive.adapters.ProjectAdapter;
import co.infinum.productive.dagger.components.DaggerProjectsComponent;
import co.infinum.productive.dagger.modules.ProjectsFragmentModule;
import co.infinum.productive.listeners.OnProjectClickListener;
import co.infinum.productive.models.ProjectTile;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.views.ProjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsFragment extends BaseFragment implements ProjectView, OnProjectClickListener {

    public static final String PROJECT = "project";

    @Inject
    ProjectPresenter projectPresenter;

    @Bind(R.id.projects_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.projects_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.empty_projects_info)
    TextView emptyProjectsInfo;

    private ProjectAdapter mSectionAdapter;

    private boolean isRefreshed = false;

    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);

        DaggerProjectsComponent.builder()
                .projectsFragmentModule(new ProjectsFragmentModule(this, getResources()))
                .build()
                .inject(this);

        layoutManager = new LinearLayoutManager(getActivity());

        initSwipeRefresh();
        initRecyclerView();

        this.showProgress();
        projectPresenter.getProjects();

        return view;
    }

    @Override
    public void onSuccess(ArrayList<ProjectTile> projectTiles) {

        this.hideProgress();

        if (projectTiles.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
            emptyProjectsInfo.setVisibility(View.VISIBLE);
        } else {
            emptyProjectsInfo.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        if (isRefreshed) {
            refreshAdapter(projectTiles);
        } else {
            initAdapter(projectTiles);
        }
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
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
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initAdapter(ArrayList<ProjectTile> projectTiles) {
        mSectionAdapter = new ProjectAdapter(getActivity(), getResources(), projectTiles, this);

        setSections(projectTiles);

        mRecyclerView.setAdapter(mSectionAdapter);
    }

    private void setSections(ArrayList<ProjectTile> projectTiles) {
        ArrayList<ProjectAdapter.ProjectSection> projectSections = new ArrayList<>();

        // calculates offset for each client and sets projectSections
        String prevClientName = "";
        int overallOffset = 0;
        int currentOffset = 0;

        for (int i = 0; i < projectTiles.size(); ++i) {
            String currClientName = projectTiles.get(i).getClientName();

            if (currClientName.equals(prevClientName)) {
                ++currentOffset;
            } else {
                overallOffset += currentOffset;
                projectSections.add(new ProjectAdapter.ProjectSection(overallOffset, currClientName));
                currentOffset = 1;
            }

            prevClientName = currClientName;
        }

        ProjectAdapter.ProjectSection[] sectionsList = new ProjectAdapter.ProjectSection[projectSections.size()];

        mSectionAdapter.setProjectSections(projectSections.toArray(sectionsList));
    }

    private void refreshAdapter(ArrayList<ProjectTile> projectTiles) {
        if (mSectionAdapter != null) {
            isRefreshed = false;

            setSections(projectTiles);
            mSectionAdapter.refresh(projectTiles);

            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onProjectsClick(int position) {
        Intent intent = new Intent(getActivity(), TasksListActivity.class);
        intent.putExtra(PROJECT, mSectionAdapter.getItem(position));
        startActivity(intent);
    }
}
