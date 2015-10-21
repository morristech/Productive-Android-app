package co.infinum.productive.mvp.views;

public interface LoginView extends BaseView {

    void navigateToMainScreen(String token);

    void onUsernameEmpty(String message);

    void onPasswordEmpty(String message);
}
