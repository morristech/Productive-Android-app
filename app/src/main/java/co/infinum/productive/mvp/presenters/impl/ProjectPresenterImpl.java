package co.infinum.productive.mvp.presenters.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import co.infinum.productive.helpers.ProjectComparator;
import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Project;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskDetails;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.ProjectInteractor;
import co.infinum.productive.mvp.interactors.TaskDetailsInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.views.ProjectView;

/**
 * Created by mjurinic on 11.11.15..
 */
public class ProjectPresenterImpl implements ProjectPresenter {

    private ProjectView projectView;
    private ProjectInteractor projectInteractor;
    private TaskInteractor taskInteractor;
    private TaskDetailsInteractor taskDetailsInteractor;
    private CacheInteractor cacheInteractor;

    @Inject
    public ProjectPresenterImpl(ProjectView projectView, ProjectInteractor projectInteractor, TaskInteractor taskInteractor,
                                TaskDetailsInteractor taskDetailsInteractor, CacheInteractor cacheInteractor) {
        this.projectView = projectView;
        this.projectInteractor = projectInteractor;
        this.taskInteractor = taskInteractor;
        this.taskDetailsInteractor = taskDetailsInteractor;
        this.cacheInteractor = cacheInteractor;
    }

    @Override
    public void getProjects() {
        projectInteractor.fetchProjects(projectListener);
    }

    @Override
    public void getTasks(ArrayList<Project> projects) {
        //TODO iter through projects and get Tasks for every project...
    }

    @Override
    public void getTaskDetails(ArrayList<Project> projects) {
        //TODO iter through Tasks and get Task Details (sort details by date and get first name on the list)
    }

    @Override
    public void cancel() {
        projectView.hideProgress();
        projectInteractor.cancel();
    }

    private Listener<ArrayList<Project>> projectListener = new Listener<ArrayList<Project>>() {
        @Override
        public void onSuccess(ArrayList<Project> projects) {
            //TODO rework this solution
            // temp solution so skliba can continue
            projectView.hideProgress();
            projectView.onSuccess(sortProjects(filterProjects(projects)));
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
    };

    private Listener<ArrayList<Task>> taskListener = new Listener<ArrayList<Task>>() {
        @Override
        public void onSuccess(ArrayList<Task> tasks) {

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
    };

    private Listener<ArrayList<TaskDetails>> taskDetailsListener = new Listener<ArrayList<TaskDetails>>() {
        @Override
        public void onSuccess(ArrayList<TaskDetails> taskDetailses) {
            projectView.hideProgress();
            //projectView.onSuccess(); //TODO should return ProjectTitle?
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
    };

    private ArrayList<Project> sortProjects(ArrayList<Project> projects) {
        Collections.sort(projects, new ProjectComparator());

        return projects;
    }

    // removes "deleted" projects
    private ArrayList<Project> filterProjects(ArrayList<Project> projects) {
        ArrayList<Project> ret = new ArrayList<>();

        for (int i = 0; i < projects.size(); ++i) {
            if (!projects.get(i).isDeleted()) {
                ret.add(projects.get(i));
            }
        }
        saveProjectsToCache(ret);
        return ret;
    }

    private void saveProjectsToCache(ArrayList<Project> ret) {
        cacheInteractor.cacheProjects(ret);
        Log.e("PROJECTS CACHE", "" + cacheInteractor.getProjects());
    }
}
