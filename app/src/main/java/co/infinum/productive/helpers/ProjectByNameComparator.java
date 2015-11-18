package co.infinum.productive.helpers;

import java.util.Comparator;

import co.infinum.productive.models.Project;

/**
 * Created by mjurinic on 13.11.15..
 */
public class ProjectByNameComparator implements Comparator<Project> {

    @Override
    public int compare(Project lhs, Project rhs) {
        return lhs.getClient().getName().compareTo(rhs.getClient().getName());
    }
}
