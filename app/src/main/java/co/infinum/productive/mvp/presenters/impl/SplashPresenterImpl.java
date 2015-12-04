package co.infinum.productive.mvp.presenters.impl;

import javax.inject.Inject;

import co.infinum.productive.mvp.interactors.SplashInteractor;
import co.infinum.productive.mvp.presenters.SplashPresenter;
import co.infinum.productive.mvp.views.SplashView;

/**
 * Created by noxqs on 03.12.15..
 */
public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;

    private SplashInteractor interactor;

    @Inject
    public SplashPresenterImpl(SplashView view, SplashInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkIfUserExists() {
        if (interactor.checkUserLoggedIn()) {
            view.userLoggedIn();
        } else {
            view.userNotLoggedIn();
        }
    }
}
