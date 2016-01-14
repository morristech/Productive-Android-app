package co.infinum.productive.fragments;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;

import android.text.Html;

import co.infinum.productive.R;
import co.infinum.productive.activities.BaseActivity;
import co.infinum.productive.mvp.views.BaseView;

/**
 * Created by mjurinic on 11.11.15..
 */
public class BaseFragment extends android.support.v4.app.Fragment implements BaseView {

    private MaterialDialog progressDialog;

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.app_name)
                    .content(R.string.please_wait)
                    .progress(true, 0)
                    .build();
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!isRemoving()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing() && !isRemoving()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        final AlertDialogWrapper.Builder matBuilder = new AlertDialogWrapper.Builder(getActivity());
        matBuilder.setTitle(R.string.app_name);

        if (message != null) {
            matBuilder.setMessage(Html.fromHtml(message));
        } else {
            matBuilder.setMessage("");
        }
        matBuilder.setPositiveButton(android.R.string.ok, null);
        if (!isRemoving()) {
            matBuilder.show();
        }
    }

    @Override
    public void showDialog(String title, String message, MaterialDialog.SingleButtonCallback positiveCallback,
            MaterialDialog.SingleButtonCallback negativeCallback, String positiveButtonText, String negativeButtonText) {
        getBaseActivity().showDialog(title, message, positiveCallback, negativeCallback, positiveButtonText, negativeButtonText);
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

}
