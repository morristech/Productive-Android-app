package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;
import java.util.HashMap;

import co.infinum.productive.models.Organization;
import co.infinum.productive.models.Project;
import co.infinum.productive.models.User;

/**
 * Created by mjurinic on 27.10.15..
 */
public interface CacheInteractor {

    void cacheOrganizations(ArrayList<Organization> organizations);

    ArrayList<Organization> getOrganizations();

    void cacheUser(User user);

    User getUser();

    void cacheProjects(ArrayList<Project> projects);

    HashMap<Integer, String> getProjects();
}
