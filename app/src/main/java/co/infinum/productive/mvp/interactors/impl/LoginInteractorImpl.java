package co.infinum.productive.mvp.interactors.impl;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.User;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.network.ApiService;
import co.infinum.productive.network.BaseCallback;
import retrofit.Call;
import retrofit.Response;

public class LoginInteractorImpl implements LoginInteractor {

    private ApiService apiService;

    private Call<BaseResponse<User>> call;

    private BaseCallback<BaseResponse<User>> callback;

    @Inject
    public LoginInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void authorize(String username, String password, final Listener<User> listener) {
        call = apiService.login(username, password);
        callback = new BaseCallback<BaseResponse<User>>() {
            @Override
            public void onUnknownError(@Nullable String error) {
                listener.onFailure(error);
            }

            @Override
            public void onSuccess(BaseResponse<User> body, Response<BaseResponse<User>> response) {
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
