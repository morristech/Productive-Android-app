package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.TaskInteractor;
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

    public TasksModule(TasksView tasksView) {
        this.tasksView = tasksView;
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
}
