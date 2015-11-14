package co.infinum.productive.mvp.presenters;

import java.util.ArrayList;

import co.infinum.productive.models.Project;

/**
 * Created by mjurinic on 11.11.15..
 */
public interface ProjectPresenter extends BasePresenter {

    void getProjects();

    void getTasks(ArrayList<Project> projects);

    void getTaskDetails(ArrayList<Project> projects);
}
