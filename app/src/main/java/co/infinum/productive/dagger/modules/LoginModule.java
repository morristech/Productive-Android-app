package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.LoginInteractor;
import co.infinum.productive.mvp.presenters.LoginPresenter;
import co.infinum.productive.mvp.presenters.impl.LoginPresenterImpl;
import co.infinum.productive.mvp.views.LoginView;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    public LoginView provideView() {
        return view;
    }

    @Provides
    public LoginInteractor provideInteractor() {
        return null; // TODO
    }

    @Provides
    public LoginPresenter providePresenter(LoginPresenterImpl presenter) {
        return presenter;
    }
}
