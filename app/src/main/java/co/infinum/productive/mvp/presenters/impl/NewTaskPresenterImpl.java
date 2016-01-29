package co.infinum.productive.mvp.presenters.impl;

import javax.inject.Inject;

import co.infinum.productive.mvp.interactors.NewTaskInteractor;
import co.infinum.productive.mvp.presenters.NewTaskPresenter;
import co.infinum.productive.mvp.views.NewTaskView;

/**
 * Created by noxqs on 20.01.16..
 */
public class NewTaskPresenterImpl implements NewTaskPresenter {

    private NewTaskInteractor interactor;

    private NewTaskView view;

    @Inject
    public NewTaskPresenterImpl(NewTaskInteractor interactor, NewTaskView view) {
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void addTask() {

    }

    @Override
    public void cancel() {

    }
}
