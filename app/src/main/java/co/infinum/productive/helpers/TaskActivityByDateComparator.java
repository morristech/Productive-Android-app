package co.infinum.productive.helpers;

import java.util.Comparator;

import co.infinum.productive.models.TaskActivityResponse;

/**
 * Created by mjurinic on 17.11.15..
 */
public class TaskActivityByDateComparator implements Comparator<TaskActivityResponse> {

    @Override
    public int compare(TaskActivityResponse lhs, TaskActivityResponse rhs) {
        return rhs.getPerson().getUpdatedAt().compareTo(lhs.getPerson().getUpdatedAt());
    }
}
