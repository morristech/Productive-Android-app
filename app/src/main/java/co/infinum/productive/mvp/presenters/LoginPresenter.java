package co.infinum.productive.mvp.presenters;

/**
 * Created by dino on 12/10/15.
 */
public interface LoginPresenter extends BasePresenter {

    void onLoginClicked(String username, String password);
}
