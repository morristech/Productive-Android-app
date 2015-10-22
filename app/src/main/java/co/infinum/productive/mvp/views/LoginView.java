package co.infinum.productive.mvp.views;

public interface LoginView extends BaseView {

    void proceedToOrganizationFetching();

    void onUsernameEmpty(String message);

    void onPasswordEmpty(String message);
}
