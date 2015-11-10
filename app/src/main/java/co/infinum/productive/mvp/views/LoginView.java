package co.infinum.productive.mvp.views;

public interface LoginView extends BaseView {

    void onLoginSuccess();

    void onUsernameEmpty(String message);

    void onPasswordEmpty(String message);
}
