package co.infinum.productive.mvp.presenters.impl;

import javax.inject.Inject;

import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;

/**
 * Created by dino on 12/10/15.
 */
public class LoginPresenterImpl implements LoginPresenter, Listener<LoginResponse> {

    private final LoginView loginView;

    private final LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onLoginClicked(String username, String password) {
        // TODO display error on view if username or password empty
        loginView.showProgress();
        loginInteractor.authorize(username, password, this);
    }

    @Override
    public void cancel() {
        loginView.hideProgress();
        loginInteractor.cancel();
    }

    @Override
    public void onSuccess(LoginResponse loginResponse) {
        loginView.hideProgress();
        loginView.navigateToMainScreen();
    }

    @Override
    public void onFailure(String message) {
        loginView.hideProgress();
        loginView.showError(null, message);
    }
}
