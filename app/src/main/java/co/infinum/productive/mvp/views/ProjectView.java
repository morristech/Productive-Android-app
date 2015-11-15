package co.infinum.productive.mvp.views;

import java.util.ArrayList;

import co.infinum.productive.models.Project;

/**
 * Created by mjurinic on 11.11.15..
 */
public interface ProjectView extends BaseView {

    void onSuccess(ArrayList<Project> projects);
}
