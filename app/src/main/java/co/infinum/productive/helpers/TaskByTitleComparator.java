package co.infinum.productive.helpers;

import java.util.Comparator;

import co.infinum.productive.models.Task;

/**
 * Created by noxqs on 04.12.15..
 */
public class TaskByTitleComparator implements Comparator<Task> {

    @Override
    public int compare(Task lhs, Task rhs) {
        return !lhs.getProject().getName().equals(rhs.getProject().getName())
                ? lhs.getProject().getName().compareTo(rhs.getProject().getName())
                : lhs.getTitle().compareTo(rhs.getTitle());
    }
}
