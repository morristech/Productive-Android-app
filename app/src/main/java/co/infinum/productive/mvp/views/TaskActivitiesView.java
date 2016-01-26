package co.infinum.productive.mvp.views;

import java.util.List;

import co.infinum.productive.models.TaskActivityResponse;

/**
 * Created by mjurinic on 28.12.15..
 */
public interface TaskActivitiesView extends BaseView {

    void onActivityFetchSuccess(List<TaskActivityResponse> taskActivities);

    void onPostCommentSuccess(TaskActivityResponse comment);
}
