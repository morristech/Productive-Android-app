package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.network.ApiService;
import co.infinum.productive.network.BaseCallback;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by mjurinic on 14.11.15..
 */
public class TaskInteractorImpl implements TaskInteractor {

    private ApiService apiService;

    private Call<BaseResponse<ArrayList<Task>>> call;

    private BaseCallback<BaseResponse<ArrayList<Task>>> callback;

    @Inject
    public TaskInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchTasks(final Listener<ArrayList<Task>> listener, int organizationId) {
        call = apiService.getTasks(organizationId);

        callback = new BaseCallback<BaseResponse<ArrayList<Task>>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<ArrayList<Task>> body, Response<BaseResponse<ArrayList<Task>>> response) {
                listener.onSuccess(body.getResponse());
            }
        };

        call.enqueue(callback);
    }

    @Override
    public void fetchTaskPerProject(final Listener<ArrayList<Task>> listener, int organizationId, int projectId) {
        call = apiService.getTaskPerProject(organizationId, projectId);

        callback = new BaseCallback<BaseResponse<ArrayList<Task>>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<ArrayList<Task>> body, Response<BaseResponse<ArrayList<Task>>> response) {
                listener.onSuccess(body.getResponse());
            }
        };

        call.enqueue(callback);
    }

    @Override
    public void cancel() {
        if (call != null && callback != null) {
            call.cancel();
            callback.cancel();
        }
    }
}
