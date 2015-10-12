package co.infinum.productive.mvp.interactors.impl;

import javax.inject.Inject;

import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.network.ApiService;

/**
 * Created by dino on 12/10/15.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private ApiService apiService;

    @Inject
    public LoginInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void authorize(String username, String password, Listener<LoginResponse> listener) {
        // TODO
    }

    @Override
    public void cancel() {
        // TODO
    }
}
