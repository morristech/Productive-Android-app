package co.infinum.productive.mvp.views;

import java.util.ArrayList;

import co.infinum.productive.models.Task;

/**
 * Created by noxqs on 15.11.15..
 */
public interface TasksView {

    void onTasksFetched(ArrayList<Task> tasks);
}