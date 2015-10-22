package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;
import co.infinum.productive.mvp.interactors.impl.LoginInteractorImpl;
import co.infinum.productive.mvp.interactors.impl.OrganizationInteractorImpl;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.presenters.OrganizationPresenter;
import co.infinum.productive.mvp.presenters.impl.LoginPresenterImpl;
import co.infinum.productive.mvp.presenters.impl.OrganizationPresenterImpl;
import co.infinum.productive.mvp.views.LoginView;
import co.infinum.productive.mvp.views.OrganizationView;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginView loginView;
    private OrganizationView organizationView;

    public LoginModule(LoginView loginView, OrganizationView organizationView) {
        this.loginView = loginView;
        this.organizationView = organizationView;
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
    public OrganizationView provideOrganizationView() {
        return organizationView;
    }

    @Provides
    public OrganizationPresenter provideOrganizationPresenter(OrganizationPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public OrganizationInteractor provideOrganizationInteractor(OrganizationInteractorImpl interactor) {
        return interactor;
    }
}
