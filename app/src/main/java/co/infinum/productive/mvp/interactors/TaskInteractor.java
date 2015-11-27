package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.Listener;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskInteractor extends BaseInteractor {

    void fetchTasks(Listener<ArrayList<Task>> listener, int organizationId);
}
