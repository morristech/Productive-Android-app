package co.infinum.productive.mvp.interactors;

import java.util.ArrayList;

import co.infinum.productive.listeners.Listener;
import co.infinum.productive.models.Project;

/**
 * Created by mjurinic on 11.11.15..
 */
public interface ProjectInteractor extends BaseInteractor {

    void fetchProjects(Listener<ArrayList<Project>> listener);
}
