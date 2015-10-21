package co.infinum.productive.mvp.presenters;

public interface LoginPresenter extends BasePresenter {

    void onLoginClicked(String username, String password);
}
