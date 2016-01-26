package co.infinum.productive.mvp.interactors.impl;

import javax.inject.Inject;

import co.infinum.productive.mvp.interactors.NewTaskInteractor;
import co.infinum.productive.network.ApiService;

/**
 * Created by noxqs on 20.01.16..
 */
public class NewTaskInteractorImpl implements NewTaskInteractor {

    private ApiService apiService;

    @Inject
    public NewTaskInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void addTask() {

    }

    @Override
    public void cancel() {

    }
}
