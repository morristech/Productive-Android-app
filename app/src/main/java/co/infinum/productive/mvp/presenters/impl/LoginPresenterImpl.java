package co.infinum.productive.mvp.presenters.impl;

import android.text.TextUtils;

import javax.inject.Inject;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.models.User;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;


public class LoginPresenterImpl implements LoginPresenter, Listener<User> {

    private final LoginView loginView;

    private final LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onLoginClicked(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            loginView.onUsernameEmpty(ProductiveApp.getInstance().getString(R.string.empty_email));
        } else if (TextUtils.isEmpty(password)) {
            loginView.onPasswordEmpty(ProductiveApp.getInstance().getString(R.string.empty_password));
        } else {
            loginView.showProgress();
            loginInteractor.authorize(username, password, this);
        }
    }

    @Override
    public void cancel() {
        loginView.hideProgress();
        loginInteractor.cancel();
    }

    @Override
    public void onSuccess(User user) {
        loginView.hideProgress();
        ProductiveApp.setUserSession(user);
        loginView.navigateToMainScreen(user.getToken());
    }

    @Override
    public void onFailure(String message) {
        loginView.hideProgress();
        loginView.showError(message);
    }

    @Override
    public void onConnectionFailure(String message) {
        loginView.showError(message);
    }
}
