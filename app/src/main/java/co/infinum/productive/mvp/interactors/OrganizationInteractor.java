package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.models.Organization;
import co.infinum.productive.mvp.Listener;

/**
 * Created by mjurinic on 22.10.15..
 */
public interface OrganizationInteractor extends BaseInteractor {

    void fetchOrganizations(Listener<ArrayList<Organization>> listener);
}
