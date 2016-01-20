package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.listeners.TaskActivitiesListener;
import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Comment;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskActivityResponse;
import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
import co.infinum.productive.network.ApiService;
import co.infinum.productive.network.BaseCallback;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by mjurinic on 14.11.15..
 */
public class TaskActivitiesInteractorImpl implements TaskActivitiesInteractor {

    // testing purposes
    public static final int ORGANIZATION_ID = 491;

    private ApiService apiService;

    private Call<BaseResponse<List<TaskActivityResponse>>> fetchCall;
    private BaseCallback<BaseResponse<List<TaskActivityResponse>>> fetchCallback;

    private Call<BaseResponse<TaskActivityResponse>> postCall;
    private BaseCallback<BaseResponse<TaskActivityResponse>> postCallBack;

    private Call<BaseResponse<Task>> patchCall;
    private BaseCallback<BaseResponse<Task>> patchCallBack;

    @Inject
    public TaskActivitiesInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchTaskActivities(final TaskActivitiesListener listener, final int projectId, int taskId) {
        //int organizationID = ProductiveApp.getInstance().getCacheInteractor().getOrganizations().get(0);

        fetchCall = apiService.getTaskActivities(ORGANIZATION_ID, projectId, taskId);

        fetchCallback = new BaseCallback<BaseResponse<List<TaskActivityResponse>>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<List<TaskActivityResponse>> body,
                                  Response<BaseResponse<List<TaskActivityResponse>>> response) {
                listener.onSuccess(body.getResponse(), projectId);
            }
        };

        fetchCall.enqueue(fetchCallback);
    }

    @Override
    public void postComment(final Listener<TaskActivityResponse> listener) {
        postCall = apiService.postComment(ORGANIZATION_ID);

        postCallBack = new BaseCallback<BaseResponse<TaskActivityResponse>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<TaskActivityResponse> body, Response<BaseResponse<TaskActivityResponse>> response) {
                listener.onSuccess(body.getResponse());
            }
        };

        postCall.enqueue(postCallBack);
    }

    @Override
    public void patchTaskActivities(final Listener<Void> listener, int projectId, int taskId, Comment comment) {
        patchCall = apiService.updateTaskActivity(ORGANIZATION_ID, projectId, taskId, comment);

        patchCallBack = new BaseCallback<BaseResponse<Task>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<Task> body, Response<BaseResponse<Task>> response) {
                listener.onSuccess(null);
            }
        };

        patchCall.enqueue(patchCallBack);
    }

    @Override
    public void cancel() {
        if (fetchCall != null && fetchCallback != null) {
            fetchCall.cancel();
            fetchCallback.cancel();
        }
    }
}
