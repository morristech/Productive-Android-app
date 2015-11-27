package co.infinum.productive.helpers;

import java.util.Comparator;

import co.infinum.productive.models.Task;

/**
 * Created by mjurinic on 16.11.15..
 */
public class TaskByDateComparator implements Comparator<Task> {

    @Override
    public int compare(Task lhs, Task rhs) {
        return rhs.getUpdatedAt().compareTo(lhs.getUpdatedAt());
    }
}
