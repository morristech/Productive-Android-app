package co.infinum.productive.dagger.modules;

import android.content.res.Resources;

import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.interactors.impl.TaskActivitiesInteractorImpl;
import co.infinum.productive.mvp.interactors.impl.TaskInteractorImpl;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.presenters.impl.TasksPresenterImpl;
import co.infinum.productive.mvp.views.TasksView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by noxqs on 15.11.15..
 */
@Module
public class TasksModule {

    private TasksView tasksView;
    private Resources resources;

    public TasksModule(TasksView tasksView, Resources resources) {
        this.tasksView = tasksView;
        this.resources = resources;
    }

    @Provides
    public Resources provideResources() {
        return resources;
    }

    @Provides
    public TasksView provideTaskView() {
        return tasksView;
    }

    @Provides
    public TasksPresenter provideTaskPresenter(TasksPresenterImpl tasksPresenter) {
        return tasksPresenter;
    }

    @Provides
    public TaskInteractor provideTaskInteractor(TaskInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public TaskActivitiesInteractor provideTaskActivitiesInteractor(TaskActivitiesInteractorImpl impl) {
        return impl;
    }
}
