package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
import co.infinum.productive.mvp.interactors.impl.TaskActivitiesInteractorImpl;
import co.infinum.productive.mvp.presenters.TaskActivitiesPresenter;
import co.infinum.productive.mvp.presenters.impl.TaskActivitiesPresenterImpl;
import co.infinum.productive.mvp.views.TaskActivitiesView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mjurinic on 29.12.15..
 */
@Module
public class TaskActivitiesFragmentModule {

    private TaskActivitiesView taskActivitiesView;

    public TaskActivitiesFragmentModule(TaskActivitiesView taskActivitiesView) {
        this.taskActivitiesView = taskActivitiesView;
    }

    @Provides
    public TaskActivitiesView getTaskActivitiesView() {
        return taskActivitiesView;
    }

    @Provides
    public TaskActivitiesPresenter provideTaskActivitiesPresenter(TaskActivitiesPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public TaskActivitiesInteractor provideTaskActivitiesInteractor(TaskActivitiesInteractorImpl interactor) {
        return interactor;
    }
}
