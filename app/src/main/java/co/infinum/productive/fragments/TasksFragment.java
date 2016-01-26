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
import co.infinum.productive.activities.TaskContentActivity;
import co.infinum.productive.adapters.TasksAdapter;
import co.infinum.productive.dagger.components.DaggerTasksComponent;
import co.infinum.productive.dagger.modules.TasksModule;
import co.infinum.productive.listeners.OnTasksClickListener;
import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.views.TasksView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends BaseFragment implements TasksView, OnTasksClickListener {

    public static final String TASK = "task";

    @Bind(R.id.tasks_recycler_view)
    RecyclerView tasksRecyclerView;

    @Bind(R.id.tasks_swipe_refresh_layout)
    SwipeRefreshLayout tasksSwipeRefreshLayout;

    @Bind(R.id.empty_tasks_info)
    TextView emptyTasksInfo;

    @Inject
    public TasksPresenter tasksPresenter;

    private TasksAdapter sectionAdapter;

    private boolean isRefreshed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        DaggerTasksComponent.builder()
                .tasksModule(new TasksModule(this))
                .build()
                .inject(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initSwipeRefresh();
        tasksPresenter.getTasks();
    }

    @Override
    public void onTasksFetched(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            tasksRecyclerView.setVisibility(View.GONE);
            emptyTasksInfo.setVisibility(View.VISIBLE);
        } else {
            emptyTasksInfo.setVisibility(View.GONE);
            tasksRecyclerView.setVisibility(View.VISIBLE);
        }

        if (isRefreshed) {
            refreshAdapters(tasks);
        } else {
            initAdapters(tasks);
        }
    }

    @Override
    public void onUnsuccessfulTaskFetch(String message) {
        showError(message);
    }

    @Override
    public void onTaskSubscribersFetched(ArrayList<Assignee> subscriber) {

    }

    @Override
    public void onTaskSubscriberError(String error) {

    }

    @Override
    public void onTaskPerProjectFetched(ArrayList<Task> tasks) {

    }

    @Override
    public void onTaskPerProjectFailed(String error) {

    }

    @Override
    public void removeOtherTasks(ArrayList<Task> onlyMyTasks) {

    }


    private void initSwipeRefresh() {
        tasksSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        tasksSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshed = true;
                tasksPresenter.getTasks();

            }
        });
    }

    private void initRecyclerView() {
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initAdapters(ArrayList<Task> tasks) {
        sectionAdapter = new TasksAdapter(getActivity(), getResources(), tasks, this);

        setSections(tasks);

        tasksRecyclerView.setAdapter(sectionAdapter);
    }


    private void setSections(ArrayList<Task> tasks) {
        ArrayList<TasksAdapter.TaskSection> sections = new ArrayList<>();

        // calculates offset for each client and sets sections
        String prevClientName = "";
        int overallOffset = 0;
        int currentOffset = 0;

        for (int i = 0; i < tasks.size(); ++i) {
            String currClientName = tasks.get(i).getProjectName();

            if (currClientName.equals(prevClientName)) {
                ++currentOffset;
            } else {
                overallOffset += currentOffset;
                sections.add(new TasksAdapter.TaskSection(overallOffset, currClientName));
                currentOffset = 1;
            }

            prevClientName = currClientName;
        }

        TasksAdapter.TaskSection[] sectionsList = new TasksAdapter.TaskSection[sections.size()];

        sectionAdapter.setSections(sections.toArray(sectionsList));
    }

    private void refreshAdapters(ArrayList<Task> tasks) {
        if (sectionAdapter != null) {
            isRefreshed = false;

            setSections(tasks);
            sectionAdapter.refresh(tasks);

            tasksSwipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onTasksClick(int position) {
        Intent intent = new Intent(getActivity(), TaskContentActivity.class);
        intent.putExtra(TASK, sectionAdapter.getItem(position));
        startActivity(intent);
    }
}
