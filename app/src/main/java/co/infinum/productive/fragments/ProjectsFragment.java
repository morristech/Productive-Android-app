package co.infinum.productive.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerProjectsFragmentComponent;
import co.infinum.productive.dagger.modules.ProjectsFragmentModule;
import co.infinum.productive.mvp.presenters.ProjectPresenter;
import co.infinum.productive.mvp.views.ProjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsFragment extends BaseFragment implements ProjectView {

    @Inject
    ProjectPresenter projectPresenter;

    public ProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);

        DaggerProjectsFragmentComponent.builder()
                .projectsFragmentModule(new ProjectsFragmentModule(this))
                .build()
                .inject(this);

        projectPresenter.getProjects();

        return view;
    }

    @Override
    public void onSuccess() {
        //TODO adapters
    }
}
