package co.infinum.productive.mvp.presenters;

import org.joda.time.LocalDate;

import co.infinum.productive.models.Task;

/**
 * Created by noxqs on 15.11.15..
 */
public interface TasksPresenter extends BasePresenter {

    void getTasks();

    void getSubscribersOnTask(Task task);

    String modifyTime(LocalDate time);
}
