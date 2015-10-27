package co.infinum.productive.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerLoginComponent;
import co.infinum.productive.dagger.modules.LoginModule;
import co.infinum.productive.helpers.SharedPrefsHelper;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;

public class LoginActivity extends BaseActivity implements LoginView {

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            etEmail.setText(savedInstanceState.getString(EMAIL));
            etPassword.setText(savedInstanceState.getString(PASSWORD));
        }

        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.login_button)
    public void onLoginClick() {
        loginPresenter.onLoginClicked(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
    }

    @Override
    public void onLoginSuccess(String token) {
        SharedPrefsHelper.saveToken(token);
        loginPresenter.getOrganizations();
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onUsernameEmpty(String message) {
        etEmail.setError(message);
    }

    @Override
    public void onPasswordEmpty(String message) {
        etPassword.setError(message);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.cancel();
    }
}
