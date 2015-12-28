package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Task;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskInteractor extends BaseInteractor {

    void fetchTasks(Listener<ArrayList<Task>> listener, int projectId);

}
