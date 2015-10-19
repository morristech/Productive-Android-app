package co.infinum.productive.mvp.views;

import co.infinum.productive.models.LoginResponse;

public interface LoginView extends BaseView {

    void navigateToMainScreen(LoginResponse loginResponse);
}
