package co.infinum.productive.mvp.presenters.impl;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Inject;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;

/**
 * Created by dino on 12/10/15.
 */
public class LoginPresenterImpl implements LoginPresenter, Listener<LoginResponse> {

    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String NO_INTERNET_ACCESS = "No internet access";

    private final LoginView loginView;

    private final LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onLoginClicked(String username, String password) {
        if(username.isEmpty() || password.isEmpty()){
            Context con = ProductiveApp.getInstance();
            loginView.showError(INVALID_CREDENTIALS, con.getString(R.string.empty_email_or_password_text));
        }
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

        loginView.navigateToMainScreen(loginResponse);
    }

    @Override
    public void onFailure(String message) {
        loginView.hideProgress();
        loginView.showError(null, message);
    }

    @Override
    public void onConnectionFailure(String message) {
        loginView.showError(NO_INTERNET_ACCESS, message);
    }
}
