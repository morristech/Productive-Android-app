package co.infinum.productive.dagger.modules;

import android.content.res.Resources;

import co.infinum.productive.mvp.interactors.ProjectInteractor;
import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.interactors.impl.ProjectInteractorImpl;
import co.infinum.productive.mvp.interactors.impl.TaskActivitiesInteractorImpl;
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
public class ProjectsFragmentModule {

    private ProjectView projectView;
    private Resources resources;

    public ProjectsFragmentModule(ProjectView projectView, Resources resources) {
        this.projectView = projectView;
        this.resources = resources;
    }

    @Provides
    public ProjectView provideProjectView() {
        return projectView;
    }

    @Provides
    public Resources provideResources() {
        return resources;
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
    public TaskActivitiesInteractor provideTaskActivities(TaskActivitiesInteractorImpl interactor) {
        return interactor;
    }
}
