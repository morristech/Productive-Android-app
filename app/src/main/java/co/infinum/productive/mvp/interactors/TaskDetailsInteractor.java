package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.TaskDetails;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskDetailsInteractor extends BaseInteractor {

    void fetchTaskDetails(Listener<ArrayList<TaskDetails>> listener, int projectId, int taskId);
}
