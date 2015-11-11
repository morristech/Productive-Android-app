package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;
import co.infinum.productive.mvp.interactors.impl.LoginInteractorImpl;
import co.infinum.productive.mvp.interactors.impl.OrganizationInteractorImpl;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.presenters.impl.LoginPresenterImpl;
import co.infinum.productive.mvp.views.LoginView;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    public LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    public LoginInteractor provideLoginInteractor(LoginInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(LoginPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public OrganizationInteractor provideOrganizationInteractor(OrganizationInteractorImpl interactor) {
        return interactor;
    }
}
