package co.infinum.productive.mvp.presenters;

import java.util.List;

import co.infinum.productive.models.Task;

/**
 * Created by noxqs on 15.11.15..
 */
public interface TasksPresenter extends BasePresenter {

    void getTasks();

    void getTaskActivities(List<Task> tasks);
}
