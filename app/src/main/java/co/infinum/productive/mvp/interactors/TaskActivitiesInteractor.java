package co.infinum.productive.mvp.interactors;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.listeners.TaskActivitiesListener;
import co.infinum.productive.models.Comment;
import co.infinum.productive.models.TaskActivityResponse;

/**
 * Created by mjurinic on 14.11.15..
 */
public interface TaskActivitiesInteractor extends BaseInteractor {

    void fetchTaskActivities(TaskActivitiesListener listener, int projectId, int taskId);

    void postComment(Listener<TaskActivityResponse> listener);

    void patchTaskActivities(Listener<Void> listener, int projectId, int taskId, Comment comment);
}
