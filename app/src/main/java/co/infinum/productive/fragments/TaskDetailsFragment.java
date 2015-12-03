package co.infinum.productive.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.infinum.productive.R;

/**
 * Created by mjurinic on 28.11.15..
 */
public class TaskDetailsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_details, container, false);
    }
}
