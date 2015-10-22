package co.infinum.productive.mvp.presenters.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.models.Organization;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;
import co.infinum.productive.mvp.presenters.OrganizationPresenter;
import co.infinum.productive.mvp.views.OrganizationView;

/**
 * Created by mjurinic on 22.10.15..
 */
public class OrganizationPresenterImpl implements OrganizationPresenter, Listener<ArrayList<Organization>> {

    private OrganizationView organizationView;
    private OrganizationInteractor organizationInteractor;

    @Inject
    public OrganizationPresenterImpl(OrganizationView organizationView, OrganizationInteractor organizationInteractor) {
        this.organizationView = organizationView;
        this.organizationInteractor = organizationInteractor;
    }

    @Override
    public void getOrganizations() {
        organizationInteractor.fetchOrganizations(this);
    }

    @Override
    public void cancel() {
        organizationView.hideProgress();
        organizationInteractor.cancel();
    }

    @Override
    public void onSuccess(ArrayList<Organization> organizations) {
        organizationView.hideProgress();
        ProductiveApp.setOrganizations(organizations);
        organizationView.navigateToMainScreen();
    }

    @Override
    public void onFailure(String message) {
        organizationView.hideProgress();
        organizationView.showError(message);
    }

    @Override
    public void onConnectionFailure(String message) {
        organizationView.showError(message);
    }
}
