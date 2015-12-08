package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.SplashInteractor;
import co.infinum.productive.mvp.interactors.impl.SplashInteractorImpl;
import co.infinum.productive.mvp.presenters.SplashPresenter;
import co.infinum.productive.mvp.presenters.impl.SplashPresenterImpl;
import co.infinum.productive.mvp.views.SplashView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by noxqs on 03.12.15..
 */
@Module
public class SplashModule {

    private SplashView view;

    public SplashModule(SplashView view) {
        this.view = view;
    }

    @Provides
    public SplashView provideSplashView() {
        return view;
    }

    @Provides
    public SplashPresenter provideSplashPresenter(SplashPresenterImpl impl) {
        return impl;
    }

    @Provides
    public SplashInteractor provideSplashInteractor(SplashInteractorImpl impl) {
        return impl;
    }
}
