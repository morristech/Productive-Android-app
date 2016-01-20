package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.NewTaskInteractor;
import co.infinum.productive.mvp.interactors.impl.NewTaskInteractorImpl;
import co.infinum.productive.mvp.presenters.NewTaskPresenter;
import co.infinum.productive.mvp.presenters.impl.NewTaskPresenterImpl;
import co.infinum.productive.mvp.views.NewTaskView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by noxqs on 20.01.16..
 */
@Module
public class NewTaskModule {

    private NewTaskView view;

    public NewTaskModule(NewTaskView view) {
        this.view = view;
    }

    @Provides
    public NewTaskView provideView() {
        return view;
    }

    @Provides
    public NewTaskPresenter providePresenter(NewTaskPresenterImpl impl) {
        return impl;
    }

    @Provides
    public NewTaskInteractor provideInteractor(NewTaskInteractorImpl impl) {
        return impl;
    }
}
