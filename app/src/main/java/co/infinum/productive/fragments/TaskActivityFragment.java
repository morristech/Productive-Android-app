package co.infinum.productive.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;

/**
 * Created by mjurinic on 28.11.15..
 */
public class TaskActivityFragment extends BaseFragment {

    @Bind(R.id.task_activities_recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_activity, container, false);
        ButterKnife.bind(this, view);

        //dagger

        layoutManager = new LinearLayoutManager(getActivity());

        initRecyclerView();

        //this.showProgress();
        //taskActivitiesPresenter.getActivities();

        return view;
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
