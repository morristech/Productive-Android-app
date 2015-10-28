package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.models.Organization;
import co.infinum.productive.models.User;

/**
 * Created by mjurinic on 27.10.15..
 */
public interface CacheInteractor {

    void cacheOrganizations(ArrayList<Organization> organizations);

    Object getOrganizations();

    void cacheUser(User user);

    Object getUser();
}
