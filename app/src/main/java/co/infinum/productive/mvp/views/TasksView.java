package co.infinum.productive.mvp.views;

import java.util.ArrayList;

import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.Task;

/**
 * Created by noxqs on 15.11.15..
 */
public interface TasksView extends BaseView {

    void onTasksFetched(ArrayList<Task> tasks);

    void onUnsuccessfulTaskFetch(String message);

    void onTaskSubscribersFetched(ArrayList<Assignee> subscriber);

    void onTaskSubscriberError(String error);

    void onTaskPerProjectFetched(ArrayList<Task> tasks);

    void onTaskPerProjectFailed(String error);
}
