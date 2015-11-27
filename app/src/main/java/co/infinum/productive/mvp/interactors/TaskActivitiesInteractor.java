package co.infinum.productive.mvp.interactors;

import co.infinum.productive.listeners.TaskActivityListener;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskActivitiesInteractor extends BaseInteractor {

    void fetchTaskActivities(TaskActivityListener listener, int projectId, int taskId);
}
