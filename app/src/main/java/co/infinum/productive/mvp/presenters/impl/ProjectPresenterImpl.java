package co.infinum.productive.mvp.presenters.impl;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.infinum.productive.helpers.ElapsedTimeFormatter;
import co.infinum.productive.helpers.TaskByDateComparator;
import co.infinum.productive.helpers.TileComparator;
import co.infinum.productive.listeners.TaskActivityListener;
import co.infinum.productive.models.Project;
import co.infinum.productive.models.ProjectTile;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskActivityResponse;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.ProjectInteractor;
import co.infinum.productive.mvp.interactors.TaskActivitiesInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.views.ProjectView;

/**
 * Created by mjurinic on 11.11.15..
 */
public class ProjectPresenterImpl implements ProjectPresenter {

    public static final int ORGANIZATION_ID = 491;

    private ProjectView projectView;
    private ProjectInteractor projectInteractor;
    private TaskInteractor taskInteractor;
    private TaskActivitiesInteractor taskActivitiesInteractor;
    private CacheInteractor cacheInteractor;
    private Resources resources;

    private int tasksCnt = 0;

    private HashMap<Integer, List<TaskActivityResponse>> allActivties;

    @Inject
    public ProjectPresenterImpl(ProjectView projectView, ProjectInteractor projectInteractor, TaskInteractor taskInteractor,
                                TaskActivitiesInteractor taskActivitiesInteractor, CacheInteractor cacheInteractor, Resources resources) {
        this.projectView = projectView;
        this.projectInteractor = projectInteractor;
        this.taskInteractor = taskInteractor;
        this.taskActivitiesInteractor = taskActivitiesInteractor;
        this.cacheInteractor = cacheInteractor;
        this.resources = resources;

        allActivties = new HashMap<>();
    }

    @Override
    public void getProjects() {
        projectInteractor.fetchProjects(projectListener);
    }

    @Override
    public void getTasks(List<Project> projects) {
        //taskInteractor.fetchTasks(taskListener, ProductiveApp.getInstance().getCacheInteractor().getOrganizations().get(0).getId());
        taskInteractor.fetchTasks(taskListener, ORGANIZATION_ID);
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
        projectInteractor.cancel();
    }

    private Listener<ArrayList<Project>> projectListener = new Listener<ArrayList<Project>>() {
        @Override
        public void onSuccess(ArrayList<Project> projects) {
            projects = filterProjects(projects);
            getTasks(projects);
        }

        @Override
        public void onFailure(String message) {
            projectView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            projectView.showError(message);
        }
    };

    private Listener<ArrayList<Task>> taskListener = new Listener<ArrayList<Task>>() {
        @Override
        public void onSuccess(ArrayList<Task> tasks) {
            tasks = filterTasks(sortTasks(tasks));

            tasksCnt = tasks.size();

            getTaskActivities(tasks);
        }

        @Override
        public void onFailure(String message) {
            projectView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            projectView.showError(message);
        }
    };

    private TaskActivityListener taskActivitiesListener = new TaskActivityListener() {
        @Override
        public void onSuccess(List<TaskActivityResponse> taskActivityResponse, int projectId) {
            --tasksCnt;

            if (taskActivityResponse.size() != 0) {
                allActivties.put(projectId, taskActivityResponse);
            }

            if (tasksCnt > 0) {
                return;
            }

            ArrayList<Project> projects = cacheInteractor.getProjects();
            ArrayList<ProjectTile> tiles = new ArrayList<>();

            for (Map.Entry<Integer, List<TaskActivityResponse>> entry : allActivties.entrySet()) {
                Integer keyProjectId = entry.getKey();
                List<TaskActivityResponse> activities = entry.getValue();

                ProjectTile tile = new ProjectTile();

                for (int i = 0; i < projects.size(); ++i) {
                    Project project = projects.get(i);

                    if (project.getId() == keyProjectId) {
                        tile = new ProjectTile(project.getName(),
                                project.getClient().getAvatarUrl(),
                                project.getClient().getName(),
                                ElapsedTimeFormatter.getElapsedTime(project.getUpdatedAt(), resources),
                                activities.get(0).getPerson().getName());
                        break;
                    }
                }

                tiles.add(tile);
            }

            for (int i = 0; i < projects.size(); ++i) {
                Project project = projects.get(i);
                boolean projectExists = false;

                for (int j = 0; j < tiles.size(); ++j) {
                    if (project.getName().equals(tiles.get(j).getProjectName())) {
                        projectExists = true;
                        break;
                    }
                }

                if (!projectExists) {
                    tiles.add(new ProjectTile(project.getName(),
                            project.getClient().getAvatarUrl(),
                            project.getClient().getName(),
                            ElapsedTimeFormatter.getElapsedTime(project.getUpdatedAt(), resources),
                            project.getProjectManager().getName()));
                }
            }

            Collections.sort(tiles, new TileComparator());

            allActivties.clear();

            projectView.onSuccess(tiles);
        }

        @Override
        public void onFailure(String message) {
            projectView.showError(message);
        }

        @Override
        public void onConnectionFailure(String message) {
            projectView.showError(message);
        }
    };

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

    private ArrayList<Task> sortTasks(ArrayList<Task> tasks) {
        Collections.sort(tasks, new TaskByDateComparator());
        return tasks;
    }

    /**
     * keeps just the last updated tasks for a project
     */
    private ArrayList<Task> filterTasks(ArrayList<Task> tasks) {
        ArrayList<Task> ret = new ArrayList<>();
        HashMap<String, Boolean> isUsed = new HashMap<>();

        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);

            if (isUsed.get(task.getProjectName()) == null) {
                ret.add(task);
                isUsed.put(task.getProjectName(), true);
            }
        }

        return ret;
    }

    private void saveProjectsToCache(ArrayList<Project> ret) {
        cacheInteractor.cacheProjects(ret);
    }
}
