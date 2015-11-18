package co.infinum.productive.helpers;

import java.util.Comparator;

import co.infinum.productive.models.ProjectTile;

/**
 * Created by mjurinic on 18.11.15..
 */
public class TilesByClientNameComparator implements Comparator<ProjectTile> {

    @Override
    public int compare(ProjectTile lhs, ProjectTile rhs) {
        return lhs.getClientName().compareTo(rhs.getClientName());
    }
}
