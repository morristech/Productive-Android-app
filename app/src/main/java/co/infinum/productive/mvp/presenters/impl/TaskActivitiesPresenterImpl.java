package co.infinum.productive.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.listeners.TaskActivitiesListener;
import co.infinum.productive.models.Comment;
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
    private int projectId;
    private int taskId;
    private String commentBody;
    private TaskActivityResponse response;

    @Inject
    public TaskActivitiesPresenterImpl(TaskActivitiesInteractor taskActivitiesInteractor, TaskActivitiesView taskActivitiesView) {
        this.taskActivitiesInteractor = taskActivitiesInteractor;
        this.taskActivitiesView = taskActivitiesView;
    }

    @Override
    public void getTaskActivities(int projectId, int taskId) {
        this.projectId = projectId;
        this.taskId = taskId;

        taskActivitiesInteractor.fetchTaskActivities(taskActivitiesListener, projectId, taskId);
    }

    @Override
    public void postComment(String commentBody) {
        this.commentBody = commentBody;

        taskActivitiesInteractor.postComment(postListener);
    }

    @Override
    public void patchTaskActivities(Comment comment) {
        taskActivitiesInteractor.patchTaskActivities(patchListener, projectId, taskId, comment);
    }

    private Listener<Void> patchListener = new Listener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            taskActivitiesView.hideProgress();

            response.setBody(commentBody);

            taskActivitiesView.onPostCommentSuccess(response);
        }

        @Override
        public void onFailure(String message) {
            taskActivitiesView.hideProgress();
            taskActivitiesView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            taskActivitiesView.hideProgress();
            taskActivitiesView.showError(message);
        }
    };

    private Listener<TaskActivityResponse> postListener = new Listener<TaskActivityResponse>() {
        @Override
        public void onSuccess(TaskActivityResponse taskActivityResponse) {
            response = taskActivityResponse;

            patchTaskActivities(new Comment(taskActivityResponse.getId(), commentBody));
        }

        @Override
        public void onFailure(String message) {
            taskActivitiesView.hideProgress();
            taskActivitiesView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            taskActivitiesView.hideProgress();
            taskActivitiesView.showError(message);
        }
    };

    private TaskActivitiesListener taskActivitiesListener = new TaskActivitiesListener() {
        @Override
        public void onSuccess(List<TaskActivityResponse> taskActivityResponse, int projectId) {
            taskActivitiesView.hideProgress();
            taskActivitiesView.onActivityFetchSuccess(taskActivityResponse);
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
