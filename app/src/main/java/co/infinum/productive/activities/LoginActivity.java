package co.infinum.productive.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerLoginComponent;
import co.infinum.productive.dagger.modules.LoginModule;
import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String TOKEN = "token";

    private ProgressDialog progressDialog;

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Inject
    LoginPresenter presenter;

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
        presenter.onLoginClicked(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen(LoginResponse loginResponse) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(TOKEN, loginResponse.getToken()).apply();
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void showError(String title, String message) {
        showErrorDialog(message);
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void showErrorDialog(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);

        if (message != null) {
            builder.setMessage(Html.fromHtml(message));
        } else {
            builder.setMessage("");
        }
        builder.setPositiveButton(android.R.string.ok, null);

        if (!isFinishing()) {
            builder.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(EMAIL, etEmail.getText().toString());
        outState.putString(PASSWORD, etPassword.getText().toString());
    }

    private void showProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.login_progress_dialog_message));
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!isFinishing()) {
            progressDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            if (!isFinishing()) {
                progressDialog.dismiss();
            }
        }
    }


}
