package co.infinum.productive.mvp.presenters.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.views.TasksView;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksPresenterImpl implements TasksPresenter {

    private TaskInteractor taskInteractor;
    private TasksView view;
    private CacheInteractor cacheInteractor;

    @Inject
    public TasksPresenterImpl(TaskInteractor taskInteractor, TasksView view, CacheInteractor cacheInteractor) {
        this.taskInteractor = taskInteractor;
        this.view = view;
        this.cacheInteractor = cacheInteractor;
    }

    @Override
    public void getTasks() {
        if(cacheInteractor.getProjects() != null){
            HashMap<Integer, String> projectIds = cacheInteractor.getProjects();
            for(Integer projectId : projectIds.keySet()){
                taskInteractor.fetchTasks(tasksListener, projectId);
            }
        }
    }

    @Override
    public void cancel() {
    }

    private Listener<ArrayList<Task>> tasksListener = new Listener<ArrayList<Task>>() {
        @Override
        public void onSuccess(ArrayList<Task> tasks) {
            view.onTasksFetched(tasks);
        }

        @Override
        public void onFailure(String message) {
            Log.e("FAILURE", message);
        }

        @Override
        public void onConnectionFailure(String message) {

        }
    };
}
