package co.infinum.productive.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import co.infinum.productive.listeners.TaskActivitiesListener;
import co.infinum.productive.models.TaskActivityResponse;
import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
import co.infinum.productive.mvp.presenters.TaskActivitiesPresenter;
import co.infinum.productive.mvp.views.TaskActivitiesView;

/**
 * Created by mjurinic on 28.12.15..
 */
public class TaskActivitiesPresenterImpl implements TaskActivitiesPresenter {

    private TaskActivitiesInteractor taskActivitiesInteractor;
    private TaskActivitiesView taskActivitiesView;

    @Inject
    public TaskActivitiesPresenterImpl(TaskActivitiesInteractor taskActivitiesInteractor, TaskActivitiesView taskActivitiesView) {
        this.taskActivitiesInteractor = taskActivitiesInteractor;
        this.taskActivitiesView = taskActivitiesView;
    }

    @Override
    public void getTaskActivities(int projectId, int taskId) {
        taskActivitiesInteractor.fetchTaskActivities(taskActivitiesListener, projectId, taskId);
    }

    private TaskActivitiesListener taskActivitiesListener = new TaskActivitiesListener() {
        @Override
        public void onSuccess(List<TaskActivityResponse> taskActivityResponse, int projectId) {
            taskActivitiesView.hideProgress();
            taskActivitiesView.onSuccess(taskActivityResponse);
        }

        @Override
        public void onFailure(String message) {
            taskActivitiesView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            taskActivitiesView.showError(message);
        }
    };
}
