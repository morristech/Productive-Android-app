package co.infinum.productive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import javax.inject.Inject;

import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerSplashComponent;
import co.infinum.productive.dagger.modules.SplashModule;
import co.infinum.productive.mvp.presenters.SplashPresenter;
import co.infinum.productive.mvp.views.SplashView;

public class SplashActivity extends BaseActivity implements SplashView {

    private static final int SPLASH_DURATION = 1500; // millis

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DaggerSplashComponent.builder()
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.checkIfUserExists();
            }
        }, SPLASH_DURATION);


    }

    @Override
    public void userLoggedIn() {
        startScreen(MainActivity.class);
    }

    @Override
    public void userNotLoggedIn() {
        startScreen(LoginActivity.class);
    }

    private void startScreen(Class<? extends Activity> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        finish();
    }

}
