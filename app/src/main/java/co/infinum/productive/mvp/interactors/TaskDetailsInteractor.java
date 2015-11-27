package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.models.TaskDetails;
import co.infinum.productive.mvp.Listener;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskDetailsInteractor extends BaseInteractor {

    void fetchTaskDetails(Listener<ArrayList<TaskDetails>> listener, int projectId, int taskId);
}
