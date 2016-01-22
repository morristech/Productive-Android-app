package co.infinum.productive.mvp.presenters.impl;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Organization;
import co.infinum.productive.models.User;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;


public class LoginPresenterImpl implements LoginPresenter {

    public static final String TOKEN = "TOKEN";

    public static final String USER_ID = "userId";

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
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            ProductiveApp app = ProductiveApp.getInstance();
            loginView.onBothEmpty(app.getString(R.string.empty_email), app.getString(R.string.empty_password));
        } else if (TextUtils.isEmpty(username)) {
            loginView.onUsernameEmpty(ProductiveApp.getInstance().getString(R.string.empty_email));
        } else if (TextUtils.isEmpty(password)) {
            loginView.onPasswordEmpty(ProductiveApp.getInstance().getString(R.string.empty_password));
        } else {
            loginView.showProgress();
            loginInteractor.authorize(username, password, userListener);
        }
    }

    @Override
    public void onToggle(EditText etPassword, Button togglePassword, Context context) {
        if (!togglePassword.getText().equals(context.getString(R.string.alternative_show_hide_text))) {
            togglePassword.setText(R.string.alternative_show_hide_text);
            etPassword.setTransformationMethod(null);
            etPassword.setSelection(etPassword.getText().length());
        } else {
            togglePassword.setText(R.string.show_button_text);
            etPassword.setTransformationMethod(new PasswordTransformationMethod());
            etPassword.setSelection(etPassword.getText().length());
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
            cacheInteractor.cacheUser(user);

            //placing token into shared preferences cause of splash screen implementation
            PreferenceManager.getDefaultSharedPreferences(ProductiveApp.getInstance())
                    .edit().putString(TOKEN, user.getToken()).apply();

            //TODO remove this
            PreferenceManager.getDefaultSharedPreferences(ProductiveApp.getInstance())
                    .edit().putLong(USER_ID, user.getUserId()).apply();

            getOrganizations();
        }

        @Override
        public void onFailure(String message) {
            loginView.hideProgress();
            loginView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            loginView.hideProgress();
            loginView.showError(message);
        }
    };

    private Listener<ArrayList<Organization>> organizationListener = new Listener<ArrayList<Organization>>() {
        @Override
        public void onSuccess(ArrayList<Organization> organizations) {
            loginView.hideProgress();
            cacheInteractor.cacheOrganizations(organizations);
            loginView.onLoginSuccess();
        }

        @Override
        public void onFailure(String message) {
            loginView.hideProgress();
            loginView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            loginView.hideProgress();
            loginView.showError(message);
        }
    };

    @Override
    public void cancel() {
        loginView.hideProgress();
        loginInteractor.cancel();
        organizationInteractor.cancel();
    }
}
