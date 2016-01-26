package co.infinum.productive.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.productive.R;
import co.infinum.productive.adapters.TaskActivitiesAdapter;
import co.infinum.productive.dagger.components.DaggerTaskActivitiesComponent;
import co.infinum.productive.dagger.modules.TaskActivitiesFragmentModule;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskActivityResponse;
import co.infinum.productive.mvp.presenters.TaskActivitiesPresenter;
import co.infinum.productive.mvp.views.TaskActivitiesView;

/**
 * Created by mjurinic on 28.11.15..
 */
public class TaskActivityFragment extends BaseFragment implements TaskActivitiesView {

    public static final String TASK = "task";

    @Inject
    TaskActivitiesPresenter taskActivitiesPresenter;

    @Bind(R.id.task_activities_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.empty_task_activities_info)
    TextView emptyActivitiesInfo;

    @Bind(R.id.task_activities_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.et_task_activities_comment)
    EditText etComment;

    private LinearLayoutManager layoutManager;
    private Task task;
    private boolean isRefreshed = false;
    private TaskActivitiesAdapter taskActivitiesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_activity, container, false);
        ButterKnife.bind(this, view);

        DaggerTaskActivitiesComponent.builder()
                .taskActivitiesFragmentModule(new TaskActivitiesFragmentModule(this))
                .build()
                .inject(this);

        init();

        return view;
    }

    @Override
    public void onActivityFetchSuccess(List<TaskActivityResponse> taskActivities) {
        if (taskActivities.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyActivitiesInfo.setVisibility(View.VISIBLE);
        } else {
            emptyActivitiesInfo.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        if (isRefreshed) {
            refreshAdapter(taskActivities);
        } else {
            initAdapter(taskActivities);
        }
    }

    @Override
    public void onPostCommentSuccess(TaskActivityResponse comment) {
        taskActivitiesAdapter.appendComment(comment);
    }

    @OnClick(R.id.btn_task_activities_comment)
    public void btnSendPressed() {
        if (!etComment.getText().toString().equals("")) {
            this.showProgress();

            taskActivitiesPresenter.postComment(etComment.getText().toString());

            etComment.setText("");
        } else {
            etComment.setError(getResources().getString(R.string.empty_comment));
        }
    }

    private void init() {
        layoutManager = new LinearLayoutManager(getActivity());

        initSwipeRefresh();
        initRecyclerView();

        task = (Task) getActivity().getIntent().getSerializableExtra(TASK);

        this.showProgress();
        taskActivitiesPresenter.getTaskActivities(task.getProjectId(), task.getId());
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshed = true;
                taskActivitiesPresenter.getTaskActivities(task.getProjectId(), task.getId());
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void refreshAdapter(List<TaskActivityResponse> taskActivities) {
        if (recyclerView != null) {
            isRefreshed = false;

            taskActivitiesAdapter.refresh(taskActivities);

            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void initAdapter(List<TaskActivityResponse> taskActivities) {
        taskActivitiesAdapter = new TaskActivitiesAdapter(taskActivities, getActivity());
        recyclerView.setAdapter(taskActivitiesAdapter);
    }
}
