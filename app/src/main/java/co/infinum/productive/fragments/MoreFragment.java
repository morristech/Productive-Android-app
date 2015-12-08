package co.infinum.productive.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.activities.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_sign_out)
    public void signOutClicked() {
        PreferenceManager.getDefaultSharedPreferences(ProductiveApp.getInstance()).edit().clear().apply();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
