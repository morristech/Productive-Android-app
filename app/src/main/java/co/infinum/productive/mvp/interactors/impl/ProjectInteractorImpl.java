package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Project;
import co.infinum.productive.mvp.interactors.ProjectInteractor;
import co.infinum.productive.network.ApiService;
import co.infinum.productive.network.BaseCallback;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by mjurinic on 11.11.15..
 */
public class ProjectInteractorImpl implements ProjectInteractor {

    public static final int ORGANIZATION_ID = 491;
    //public static final int ORGANIZATION_ID = 514;

    private ApiService apiService;
    private Call<BaseResponse<ArrayList<Project>>> call;
    private BaseCallback<BaseResponse<ArrayList<Project>>> callback;

    @Inject
    public ProjectInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchProjects(final Listener<ArrayList<Project>> listener) {
        //call = apiService.getProjects(ProductiveApp.getInstance().getCacheInteractor().getOrganizations().get(0).getId());
        call = apiService.getProjects(ORGANIZATION_ID); //debug purpose only (we have more projects here)

        callback = new BaseCallback<BaseResponse<ArrayList<Project>>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<ArrayList<Project>> body, Response<BaseResponse<ArrayList<Project>>> response) {
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
