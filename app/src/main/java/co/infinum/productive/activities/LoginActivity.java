package co.infinum.productive.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerLoginComponent;
import co.infinum.productive.dagger.modules.LoginModule;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // inject dependencies
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void navigateToMainScreen() {
        // TODO
    }

    @Override
    public void showProgress() {
        // TODO
    }

    @Override
    public void hideProgress() {
        // TODO
    }

    @Override
    public void showError(String title, String message) {
        // TODO
    }
}
