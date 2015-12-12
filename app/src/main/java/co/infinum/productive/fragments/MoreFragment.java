package co.infinum.productive.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.productive.R;
import co.infinum.productive.activities.LoginActivity;
import co.infinum.productive.helpers.SharedPrefsHelper;

public class MoreFragment extends BaseFragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_sign_out)
    public void signOutClicked() {
        showBasicDialog(null, getResources().getString(R.string.logout_message), callback, null,
                getResources().getString(R.string.sign_out_dialog_button_text),
                getResources().getString(R.string.cancel_dialog_button_text));
    }

    private MaterialDialog.SingleButtonCallback callback = new MaterialDialog.SingleButtonCallback() {
        @Override
        public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
            SharedPrefsHelper.clearEntireSharedPrefs(context);
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
