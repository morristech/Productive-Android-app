package co.infinum.productive.activities;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;

import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import co.infinum.productive.R;
import co.infinum.productive.mvp.views.BaseView;

/**
 * Created by noxqs on 20.10.15..
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    private MaterialDialog progressDialog;

    private void showErrorDialog(String message) {

        final AlertDialogWrapper.Builder matBuilder = new AlertDialogWrapper.Builder(this);
        matBuilder.setTitle(R.string.app_name);

        if (message != null) {
            matBuilder.setMessage(Html.fromHtml(message));
        } else {
            matBuilder.setMessage("");
        }
        matBuilder.setPositiveButton(android.R.string.ok, null);
        if (!isFinishing()) {
            matBuilder.show();
        }
    }

    private void showProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(this)
                    .title(R.string.app_name)
                    .content(R.string.please_wait)
                    .progress(true, 0)
                    .cancelable(false)
                    .build();
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!isFinishing()) {
            progressDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing() && !isFinishing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void showError(String message) {
        showErrorDialog(message);
    }
}
