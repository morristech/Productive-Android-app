package co.infinum.productive.mvp.presenters.impl;

import org.joda.time.LocalDate;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import co.infinum.productive.helpers.SubscribersViewGroup;
import co.infinum.productive.helpers.TaskByTitleComparator;
import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.interactors.TaskDetailsInteractor;
import co.infinum.productive.mvp.interactors.TaskInteractor;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.views.TasksView;

/**
 * Created by noxqs on 15.11.15..
 */
public class TasksPresenterImpl implements TasksPresenter {

    public static final int ORGANIZATIONS = 491;

    private TaskInteractor taskInteractor;

    private TaskDetailsInteractor taskDetailsInteractor;

    private TasksView view;


    @Inject
    public TasksPresenterImpl(TaskInteractor taskInteractor, TasksView view, TaskDetailsInteractor taskDetailsInteractor) {
        this.taskInteractor = taskInteractor;
        this.view = view;
        this.taskDetailsInteractor = taskDetailsInteractor;
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
    public String modifyTime(LocalDate time) {
        String formattedTime = "";

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();

        for (int i = 0; i < months.length; i++) {
            if (i == time.getMonthOfYear()) {
                formattedTime = months[i];
            }
        }

        formattedTime += " " + time.getDayOfMonth() + ", ";
        formattedTime += "" + time.getYear();

        return formattedTime;
    }

    @Override
    public void setupSubscribers(LinearLayout container, Context context, ArrayList<Assignee> fetchedSubscribers, float px) {

        SubscribersViewGroup v = new SubscribersViewGroup(context);

        int totalWidth = 0;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        v.setLayoutParams(params);

        container.addView(v);

        for (int i = 0; i < fetchedSubscribers.size(); i++) {

            totalWidth = totalWidth + v.addSubscriber(fetchedSubscribers.get(i).getName());

            if (totalWidth > container.getWidth() - px) {
                totalWidth = 0;
                v.removeViewAt(v.getChildCount() - 1);
                i--;
                v = new SubscribersViewGroup(context);
                container.addView(v);
            }
        }
    }

    @Override
    public void getSubscribersOnTask(Task task) {
        taskDetailsInteractor.fetchTaskSubscribers(taskSubscribersListener, task);
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
            view.onUnsuccessfulTaskFetch(message);
        }

        @Override
        public void onConnectionFailure(String message) {

        }
    };

    private Listener<ArrayList<Assignee>> taskSubscribersListener = new Listener<ArrayList<Assignee>>() {
        @Override
        public void onSuccess(ArrayList<Assignee> assignees) {
            view.onTaskSubscribersFetched(assignees);
        }

        @Override
        public void onFailure(String message) {
            view.onTaskSubscriberError(message);
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
