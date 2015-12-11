package co.infinum.productive.mvp.views;

import com.afollestad.materialdialogs.MaterialDialog;

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showBasicDialog(String title, String message, MaterialDialog.SingleButtonCallback positiveCallback,
            MaterialDialog.SingleButtonCallback negativeCallback, String positiveButtonText, String negativeButtonText);
}
