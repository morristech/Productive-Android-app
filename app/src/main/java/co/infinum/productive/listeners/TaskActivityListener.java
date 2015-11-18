package co.infinum.productive.listeners;

import java.util.List;

import co.infinum.productive.models.TaskActivityResponse;

/**
 * Created by mjurinic on 18.11.15..
 */
public interface TaskActivityListener {

    void onSuccess(List<TaskActivityResponse> taskActivityResponse, int projectId);

    void onFailure(String message);

    //applies to the cases where internet is not accessible or not turned on
    //or for some hardware reasons connection to the internet could not be established
    void onConnectionFailure(String message);
}
