package co.infinum.productive.helpers;

import java.util.Comparator;

import co.infinum.productive.models.TaskTile;

/**
 * Created by noxqs on 03.12.15..
 */
public class TaskTileComparator implements Comparator<TaskTile> {

    @Override
    public int compare(TaskTile lhs, TaskTile rhs) {
        return !lhs.getProjectName().equals(rhs.getProjectName())
                ? lhs.getProjectName().compareTo(rhs.getProjectName())
                : lhs.getTaskName().compareTo(rhs.getTaskName());
    }
}
