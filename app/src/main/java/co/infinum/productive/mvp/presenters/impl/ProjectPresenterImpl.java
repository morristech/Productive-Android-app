package co.infinum.productive.mvp.presenters.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.models.Project;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.ProjectInteractor;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.views.ProjectView;

/**
 * Created by mjurinic on 11.11.15..
 */
public class ProjectPresenterImpl implements ProjectPresenter, Listener<ArrayList<Project>> {

    private ProjectView projectView;
    private ProjectInteractor projectInteractor;
    private CacheInteractor cacheInteractor;

    @Inject
    public ProjectPresenterImpl(ProjectView projectView, ProjectInteractor projectInteractor, CacheInteractor cacheInteractor) {
        this.projectView = projectView;
        this.projectInteractor = projectInteractor;
        this.cacheInteractor = cacheInteractor;
    }

    @Override
    public void getProjects() {
        projectInteractor.fetchProjects(this);
    }

    @Override
    public void cancel() {
        projectView.hideProgress();
        projectInteractor.cancel();
    }

    @Override
    public void onSuccess(ArrayList<Project> projects) {
        projectView.hideProgress();
        cacheInteractor.cacheProjects(projects);
        projectView.onSuccess();
    }

    @Override
    public void onFailure(String message) {
        projectView.hideProgress();
        projectView.showError(message);
    }

    @Override
    public void onConnectionFailure(String message) {
        projectView.hideProgress();
        projectView.showError(message);
    }
}
