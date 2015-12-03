package co.infinum.productive.mvp.views;

import java.util.ArrayList;

import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskTile;

/**
 * Created by noxqs on 15.11.15..
 */
public interface TasksView extends BaseView {

    void onTasksFetched(ArrayList<Task> tasks);
    void onSuccess(ArrayList<TaskTile> tiles);

}
