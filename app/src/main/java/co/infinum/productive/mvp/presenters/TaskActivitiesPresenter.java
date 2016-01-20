package co.infinum.productive.mvp.presenters;

import co.infinum.productive.models.Comment;

/**
 * Created by mjurinic on 28.12.15..
 */
public interface TaskActivitiesPresenter {

    void getTaskActivities(int projectId, int taskId);

    void postComment(String commentBody);

    void patchTaskActivities(Comment comment);
}
