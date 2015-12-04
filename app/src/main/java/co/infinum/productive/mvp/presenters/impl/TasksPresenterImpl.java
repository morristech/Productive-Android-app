package co.infinum.productive.mvp.presenters.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import co.infinum.productive.helpers.TaskByTitleComparator;
import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.views.TasksView;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksPresenterImpl implements TasksPresenter {

    public static final int ORGANIZATIONS = 491;
    private TaskInteractor taskInteractor;
    private TasksView view;

    @Inject
    public TasksPresenterImpl(TaskInteractor taskInteractor, TasksView view) {
        this.taskInteractor = taskInteractor;
        this.view = view;
    }

    @Override
    public void getTasks() {
        /*
        TODO temporary solution because in organization 491 we have more projects and therefore tasks, will get modified as soon as
        TODO the testing phase is done
        taskInteractor.fetchTasks(tasksListener, cacheInteractor.getOrganizations().get(0).getId());
        */
        taskInteractor.fetchTasks(tasksListener, ORGANIZATIONS);
    }

    @Override
    public void cancel() {
        taskInteractor.cancel();
    }

    private Listener<ArrayList<Task>> tasksListener = new Listener<ArrayList<Task>>() {
        @Override
        public void onSuccess(ArrayList<Task> tasks) {
            view.onTasksFetched(sortTasks(tasks));
        }

        @Override
        public void onFailure(String message) {
            Log.e("FAILURE", message);
        }

        @Override
        public void onConnectionFailure(String message) {

        }
    };

    private ArrayList<Task> sortTasks(ArrayList<Task> tasks) {
        Collections.sort(tasks, new TaskByTitleComparator());
        return tasks;
    }
}
