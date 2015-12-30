package co.infinum.productive.mvp.interactors;

import co.infinum.productive.listeners.TaskActivitiesListener;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskActivitiesInteractor extends BaseInteractor {

    void fetchTaskActivities(TaskActivitiesListener listener, int projectId, int taskId);
}
