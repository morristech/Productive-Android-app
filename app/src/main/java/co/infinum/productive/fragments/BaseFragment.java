package co.infinum.productive.fragments;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;

import co.infinum.productive.R;
import co.infinum.productive.mvp.views.BaseView;

/**
 * Created by mjurinic on 11.11.15..
 */
public class BaseFragment extends android.support.v4.app.Fragment implements BaseView {

    private MaterialDialog progressDialog;

    private Context context;

    private MaterialDialog basicDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(context)
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
        final AlertDialogWrapper.Builder matBuilder = new AlertDialogWrapper.Builder(context);
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
    public void showBasicDialog(String title, String message, MaterialDialog.SingleButtonCallback positiveCallback,
            MaterialDialog.SingleButtonCallback negativeCallback, String positiveButtonText, String negativeButtonText) {
        if (basicDialog == null || !basicDialog.isShowing()) {
            basicDialog = new MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .positiveText(positiveButtonText)
                    .positiveColor(ContextCompat.getColor(context, R.color.signinButtonDefaultColor))
                    .onPositive(positiveCallback)
                    .onNegative(negativeCallback)
                    .negativeText(negativeButtonText)
                    .negativeColor(ContextCompat.getColor(context, R.color.signinButtonDefaultColor))
                    .build();
            basicDialog.setCanceledOnTouchOutside(false);

            if (!isRemoving()) {
                basicDialog.show();
            }

        }
    }

}
