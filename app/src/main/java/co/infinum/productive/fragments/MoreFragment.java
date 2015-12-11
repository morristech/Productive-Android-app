package co.infinum.productive.fragments;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.productive.ProductiveApp;
import co.infinum.productive.R;
import co.infinum.productive.activities.LoginActivity;

public class MoreFragment extends BaseFragment {

    private MaterialDialog dialog;

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
        showDialog();
    }

    private void showDialog() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new MaterialDialog.Builder(context)
                    .content(R.string.logout_message)
                    .positiveText(R.string.sign_out_dialog_button_text)
                    .positiveColor(ContextCompat.getColor(context, R.color.signinButtonDefaultColor))
                    .onPositive(callback)
                    .negativeText(R.string.cancel_dialog_button_text)
                    .negativeColor(ContextCompat.getColor(context, R.color.signinButtonDefaultColor))
                    .build();
            dialog.setCanceledOnTouchOutside(false);

            if (!isRemoving()) {
                dialog.show();
            }

        }
    }

    private MaterialDialog.SingleButtonCallback callback = new MaterialDialog.SingleButtonCallback() {
        @Override
        public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
            PreferenceManager.getDefaultSharedPreferences(ProductiveApp.getInstance()).edit().clear().apply();
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
