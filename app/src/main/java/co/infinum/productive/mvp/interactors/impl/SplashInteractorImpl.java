package co.infinum.productive.mvp.interactors.impl;

import android.content.Context;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import co.infinum.productive.mvp.interactors.SplashInteractor;

/**
 * Created by noxqs on 03.12.15..
 */
public class SplashInteractorImpl implements SplashInteractor {

    public static final String TOKEN = "TOKEN";

    private Context context;

    @Inject
    public SplashInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean checkUserLoggedIn() {
        String token = PreferenceManager.getDefaultSharedPreferences(context).getString(TOKEN, "");
        return !token.equals("");
    }
}
