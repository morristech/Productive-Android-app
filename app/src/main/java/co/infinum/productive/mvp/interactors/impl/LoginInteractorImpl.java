package co.infinum.productive.mvp.interactors.impl;

import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.LoginInteractor;

/**
 * Created by dino on 12/10/15.
 */
public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void authorize(String username, String password, Listener<LoginResponse> listener) {
        // TODO
    }

    @Override
    public void cancel() {
        // TODO
    }
}
