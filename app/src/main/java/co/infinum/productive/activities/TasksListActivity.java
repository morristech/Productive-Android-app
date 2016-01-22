package co.infinum.productive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.adapters.TaskPerProjectAdapter;
import co.infinum.productive.dagger.components.DaggerTasksComponent;
import co.infinum.productive.dagger.modules.TasksModule;
import co.infinum.productive.listeners.OnTasksClickListener;
import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.ProjectTile;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.views.TasksView;

public class TasksListActivity extends BaseActivity implements TasksView, OnTasksClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String PROJECT = "project";

    public static final String TASK = "task";

    @Bind(R.id.tasks_list_recycler)
    RecyclerView tasksListRecycler;

    @Bind(R.id.tasks_swipe_refresh_layout)
    SwipeRefreshLayout tasksSwipeRefreshLayout;

    private ProjectTile project;

    private boolean isRefreshed = false;

    private ArrayList<Task> tasks;

    private Switch mySwitch;

    @Inject
    TasksPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        ButterKnife.bind(this);

        DaggerTasksComponent.builder()
                .tasksModule(new TasksModule(this))
                .build()
                .inject(this);

        init();
        initSwipeRefresh();

        project = (ProjectTile) getIntent().getSerializableExtra(PROJECT);

        if (project.getId() != 0) {
            showProgress();
            presenter.getAllTasksOnProject(project.getId());
        }
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tasksListRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initSwipeRefresh() {
        tasksSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        tasksSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshed = true;
                presenter.getAllTasksOnProject(project.getId());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_list_activity, menu);

        mySwitch = (Switch) menu.findItem(R.id.toggle_my_projects_menu).getActionView().findViewById(R.id.toggle_my_projects);
        mySwitch.setOnCheckedChangeListener(this);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onTaskPerProjectFetched(ArrayList<Task> tasks) {
        hideProgress();
        this.tasks = tasks;
        tasksListRecycler.setAdapter(new TaskPerProjectAdapter(tasks, getApplicationContext(), this, getResources()));
    }

    @Override
    public void onTaskPerProjectFailed(String error) {
        showError(error);
    }

    @Override
    public void removeOtherTasks(ArrayList<Task> onlyMyTasks) {
        tasks = onlyMyTasks;
        tasksListRecycler.setAdapter(new TaskPerProjectAdapter(onlyMyTasks, getApplicationContext(), this, getResources()));
    }

    @Override
    public void onTasksClick(int position) {
        Intent intent = new Intent(this, TaskContentActivity.class);
        intent.putExtra(TASK, tasks.get(position));
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            presenter.showMyTasksOnly(tasks);
        } else {
            presenter.getAllTasksOnProject(project.getId());
        }

    }


    @Override
    public void onTasksFetched(ArrayList<Task> tasks) {
        //nothing
    }

    @Override
    public void onUnsuccessfulTaskFetch(String message) {
        //nothing
    }

    @Override
    public void onTaskSubscribersFetched(ArrayList<Assignee> subscriber) {
        //nothing
    }

    @Override
    public void onTaskSubscriberError(String error) {
        //nothing
    }
}
