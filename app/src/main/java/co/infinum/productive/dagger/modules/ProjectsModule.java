package co.infinum.productive.dagger.modules;

import co.infinum.productive.mvp.interactors.ProjectInteractor;
import co.infinum.productive.mvp.interactors.TaskDetailsInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.interactors.impl.ProjectInteractorImpl;
import co.infinum.productive.mvp.interactors.impl.TaskDetailsInteractorImpl;
import co.infinum.productive.mvp.interactors.impl.TaskInteractorImpl;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.presenters.impl.ProjectPresenterImpl;
import co.infinum.productive.mvp.views.ProjectView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mjurinic on 11.11.15..
 */
@Module
public class ProjectsModule {

    private ProjectView projectView;

    public ProjectsModule(ProjectView projectView) {
        this.projectView = projectView;
    }

    @Provides
    public ProjectView provideProjectView() {
        return projectView;
    }

    @Provides
    public ProjectPresenter provideProjectPresenter(ProjectPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public ProjectInteractor provideProjectInteractor(ProjectInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public TaskInteractor provideTaskInteractor(TaskInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public TaskDetailsInteractor provideTaskDetailsInteractor(TaskDetailsInteractorImpl interactor) {
        return interactor;
    }
}
