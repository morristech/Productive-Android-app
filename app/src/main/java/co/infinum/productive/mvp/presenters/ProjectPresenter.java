package co.infinum.productive.mvp.presenters;

import java.util.List;

import co.infinum.productive.models.Project;
import co.infinum.productive.models.Task;

/**
 * Created by mjurinic on 11.11.15..
 */
public interface ProjectPresenter extends BasePresenter {

    void getProjects();

    void getTasks(List<Project> projects);

    void getTaskActivities(List<Task> tasks);
}
