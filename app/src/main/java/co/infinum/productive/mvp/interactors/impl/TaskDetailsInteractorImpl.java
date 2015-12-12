package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.TaskDetails;
import co.infinum.productive.mvp.interactors.TaskDetailsInteractor;
import co.infinum.productive.network.ApiService;
import co.infinum.productive.network.BaseCallback;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by mjurinic on 14.11.15..
 */
public class TaskDetailsInteractorImpl implements TaskDetailsInteractor {

    // testing purposes
    public static final int ORGANIZATION_ID = 491;

    private ApiService apiService;
    private Call<BaseResponse<ArrayList<TaskDetails>>> call;
    private BaseCallback<BaseResponse<ArrayList<TaskDetails>>> callback;

    @Inject
    public TaskDetailsInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchTaskDetails(final Listener<ArrayList<TaskDetails>> listener, int projectId, int taskId) {
        //int organizationID = ProductiveApp.getInstance().getCacheInteractor().getOrganizations().get(0);

        call = apiService.getTaskDetails(ORGANIZATION_ID, projectId, taskId);

        callback = new BaseCallback<BaseResponse<ArrayList<TaskDetails>>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<ArrayList<TaskDetails>> body, Response<BaseResponse<ArrayList<TaskDetails>>> response) {
                listener.onSuccess(body.getResponse());
            }
        };

        call.enqueue(callback);
    }

    @Override
    public void cancel() {

    }
}
