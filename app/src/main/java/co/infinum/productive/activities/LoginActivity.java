package co.infinum.productive.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.infinum.productive.R;
import co.infinum.productive.mvp.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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
