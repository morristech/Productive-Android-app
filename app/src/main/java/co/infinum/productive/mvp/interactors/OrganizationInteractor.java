package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Organization;

/**
 * Created by mjurinic on 22.10.15..
 */
public interface OrganizationInteractor extends BaseInteractor {

    void fetchOrganizations(Listener<ArrayList<Organization>> listener);
}
