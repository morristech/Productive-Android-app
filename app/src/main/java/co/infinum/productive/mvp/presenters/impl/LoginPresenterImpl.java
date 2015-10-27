package co.infinum.productive.mvp.presenters.impl;

import android.text.TextUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.models.Organization;
import co.infinum.productive.models.User;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;


public class LoginPresenterImpl implements LoginPresenter {

    public static final String USER = "user";
    public static final String ORGANIZATIONS = "organizations";

    private final LoginView loginView;
    private final LoginInteractor loginInteractor;
    private final OrganizationInteractor organizationInteractor;
    private final CacheInteractor cacheInteractor;

    @Inject
    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor,
                              OrganizationInteractor organizationInteractor, CacheInteractor cacheInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.organizationInteractor = organizationInteractor;
        this.cacheInteractor = cacheInteractor;
    }

    @Override
    public void onLoginClicked(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            loginView.onUsernameEmpty(ProductiveApp.getInstance().getString(R.string.empty_email));
        } else if (TextUtils.isEmpty(password)) {
            loginView.onPasswordEmpty(ProductiveApp.getInstance().getString(R.string.empty_password));
        } else {
            loginView.showProgress();
            loginInteractor.authorize(username, password, userListener);
        }
    }

    @Override
    public void getOrganizations() {
        organizationInteractor.fetchOrganizations(organizationListener);
    }

    private Listener<User> userListener = new Listener<User>() {
        @Override
        public void onSuccess(User user) {
            loginView.hideProgress();
            cacheInteractor.setCache(USER, user);
            loginView.onLoginSuccess(user.getToken());
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
    };

    private Listener<ArrayList<Organization>> organizationListener = new Listener<ArrayList<Organization>>() {
        @Override
        public void onSuccess(ArrayList<Organization> organizations) {
            loginView.hideProgress();
            cacheInteractor.setCache(ORGANIZATIONS, organizations);
            loginView.navigateToMainScreen();
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
    };

    @Override
    public void cancel() {
        loginView.hideProgress();
        loginInteractor.cancel();
    }
}
