package co.infinum.productive.mvp.views;

public interface LoginView extends BaseView {

    void onLoginSuccess(String token);

    void onUsernameEmpty(String message);

    void onPasswordEmpty(String message);
}
