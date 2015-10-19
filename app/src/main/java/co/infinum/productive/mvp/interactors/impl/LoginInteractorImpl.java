package co.infinum.productive.mvp.interactors.impl;

import javax.inject.Inject;

import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.models.Response;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.network.ApiService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

public class LoginInteractorImpl implements LoginInteractor {

    private ApiService apiService;

    private boolean isCanceled = false;

    @Inject
    public LoginInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void authorize(String username, String password, final Listener<LoginResponse> listener) {
        Call<Response<LoginResponse>> call = apiService.login(username, password);
        call.enqueue(new Callback<Response<LoginResponse>>() {
            @Override
            public void onResponse(retrofit.Response<Response<LoginResponse>> response, Retrofit retrofit) {
                if (!isCanceled) {
                    if (response.isSuccess()) {
                        listener.onSuccess(response.body().getResponse());
                    } else {
                        listener.onFailure(response.message());
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                listener.onConnectionFailure(t.getMessage());
            }
        });
    }

    @Override
    public void cancel() {
        isCanceled = true;
    }
}
