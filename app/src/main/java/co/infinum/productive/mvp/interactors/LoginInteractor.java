package co.infinum.productive.mvp.interactors;

import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.mvp.Listener;

/**
 * Created by dino on 12/10/15.
 */
public interface LoginInteractor extends BaseInteractor {

    void authorize(String username, String password, Listener<LoginResponse> listener);
}
