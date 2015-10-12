package co.infinum.productive.network;

import retrofit.http.POST;

/**
 * Created by dino on 12/10/15.
 */
public interface ApiService {

    @POST("/users/login")
    void login();

    // TODO specify REST API
}
