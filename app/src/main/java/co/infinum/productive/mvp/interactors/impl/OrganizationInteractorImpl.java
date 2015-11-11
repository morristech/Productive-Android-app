package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Organization;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;
import co.infinum.productive.network.ApiService;
import co.infinum.productive.network.BaseCallback;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by mjurinic on 22.10.15..
 */
public class OrganizationInteractorImpl implements OrganizationInteractor {

    private ApiService apiService;

    private Call<BaseResponse<ArrayList<Organization>>> call;
    private BaseCallback<BaseResponse<ArrayList<Organization>>> callback;

    @Inject
    public OrganizationInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchOrganizations(final Listener<ArrayList<Organization>> listener) {
        call = apiService.getOrganizations();

        callback = new BaseCallback<BaseResponse<ArrayList<Organization>>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<ArrayList<Organization>> body, Response<BaseResponse<ArrayList<Organization>>> response) {
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
