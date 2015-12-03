package co.infinum.productive.mvp.presenters.impl;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.infinum.productive.helpers.ElapsedTimeFormatter;
import co.infinum.productive.helpers.TaskByDateComparator;
import co.infinum.productive.helpers.TaskTileComparator;
import co.infinum.productive.listeners.Listener;
import co.infinum.productive.listeners.TaskActivityListener;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskActivityResponse;
import co.infinum.productive.models.TaskTile;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
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

    private CacheInteractor cacheInteractor;

    private TaskActivitiesInteractor taskActivitiesInteractor;

    private HashMap<Integer, List<TaskActivityResponse>> allActivties;

    private int tasksCnt = 0;

    private ArrayList<Task> taskList;

    private Resources resources;


    @Inject
    public TasksPresenterImpl(TaskInteractor taskInteractor, TasksView view, CacheInteractor cacheInteractor,
            TaskActivitiesInteractor taskActivitiesInteractor, Resources resources) {
        this.taskInteractor = taskInteractor;
        this.view = view;
        this.cacheInteractor = cacheInteractor;
        this.taskActivitiesInteractor = taskActivitiesInteractor;
        this.resources = resources;

        taskList = new ArrayList<>();
        allActivties = new HashMap<>();
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
    public void getTaskActivities(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            taskActivitiesInteractor.fetchTaskActivities(taskActivitiesListener, task.getProjectId(), task.getId());
        }
    }

    @Override
    public void cancel() {
        taskInteractor.cancel();
    }

    private Listener<ArrayList<Task>> tasksListener = new Listener<ArrayList<Task>>() {
        @Override
        public void onSuccess(ArrayList<Task> tasks) {
            // view.onTasksFetched(tasks);
            taskList.clear();
            taskList.addAll(sortTasks(tasks));
            getTaskActivities(sortTasks(tasks));
        }

        @Override
        public void onFailure(String message) {
            Log.e("FAILURE", message);
        }

        @Override
        public void onConnectionFailure(String message) {

        }
    };

    private TaskActivityListener taskActivitiesListener = new TaskActivityListener() {
        @Override
        public void onSuccess(List<TaskActivityResponse> taskActivityResponse, int projectId) {
            --tasksCnt;
            ArrayList<TaskTile> tiles = new ArrayList<>();

            if (taskActivityResponse.size() != 0) {
                allActivties.put(projectId, taskActivityResponse);
            }
            if (tasksCnt > 0) {
                return;
            }

            for (Map.Entry<Integer, List<TaskActivityResponse>> entry : allActivties.entrySet()) {
                Integer keyProjectId = entry.getKey();
                List<TaskActivityResponse> activities = entry.getValue();

                TaskTile tile = new TaskTile();

                for (int i = 0; i < taskList.size(); ++i) {
                    Task task = taskList.get(i);

                    if (task.getProject().getId() == keyProjectId) {
                        tile = new TaskTile(task.getTitle(),
                                task.getAssignee().getAvatarUrl(),
                                task.getProject().getName(),
                                ElapsedTimeFormatter.getElapsedTime(task.getUpdatedAt(), resources),
                                activities.get(0).getPerson().getName());
                        break;
                    }
                }
                tiles.add(tile);
            }

            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                boolean taskExists = false;

                for (int j = 0; j < tiles.size(); ++j) {
                    if (task.getProjectName().equals(tiles.get(j).getTaskName())) {
                        taskExists = true;
                        break;
                    }
                }

                if (!taskExists) {
                    if (task.getAssignee() != null) {
                        tiles.add(new TaskTile(task.getTitle(),
                                task.getAssignee().getAvatarUrl(),
                                task.getProject().getName(),
                                ElapsedTimeFormatter.getElapsedTime(task.getUpdatedAt(), resources),
                                task.getAssignee().getName()));
                    } else {
                        tiles.add(new TaskTile(task.getTitle(),
                                "",
                                task.getProject().getName(),
                                ElapsedTimeFormatter.getElapsedTime(task.getUpdatedAt(), resources),
                                "No assignee assigned"));
                    }
                }
            }
            Collections.sort(tiles, new TaskTileComparator());

            allActivties.clear();

            view.onSuccess(tiles);
        }

        @Override
        public void onFailure(String message) {

        }

        @Override
        public void onConnectionFailure(String message) {

        }
    };


    private ArrayList<Task> sortTasks(ArrayList<Task> tasks) {
        Collections.sort(tasks, new TaskByDateComparator());
        return tasks;
    }
}
