package co.infinum.productive.mvp.views;

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(String title, String message);
}
