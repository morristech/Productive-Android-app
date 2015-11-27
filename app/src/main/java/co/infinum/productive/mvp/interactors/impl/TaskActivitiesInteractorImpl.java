package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import co.infinum.productive.listeners.TaskActivityListener;
import co.infinum.productive.models.BaseResponse;
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
    private Call<BaseResponse<List<TaskActivityResponse>>> call;
    private BaseCallback<BaseResponse<List<TaskActivityResponse>>> callback;

    @Inject
    public TaskActivitiesInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchTaskActivities(final TaskActivityListener listener, final int projectId, int taskId) {
        //int organizationID = ProductiveApp.getInstance().getCacheInteractor().getOrganizations().get(0);

        call = apiService.getTaskActivities(ORGANIZATION_ID, projectId, taskId);

        callback = new BaseCallback<BaseResponse<List<TaskActivityResponse>>>() {
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
