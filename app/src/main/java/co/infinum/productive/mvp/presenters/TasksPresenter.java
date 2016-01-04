package co.infinum.productive.mvp.presenters;

import org.joda.time.LocalDate;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;

import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.Task;

/**
 * Created by noxqs on 15.11.15..
 */
public interface TasksPresenter extends BasePresenter {

    void getTasks();

    void getSubscribersOnTask(Task task);

    String modifyTime(LocalDate time);

    void setupSubscribers(LinearLayout container, Context context, ArrayList<Assignee> fetchedSubscribers, float px);
}
