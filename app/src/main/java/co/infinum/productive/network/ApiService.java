package co.infinum.productive.network;

import co.infinum.productive.models.LoginResponse;
import co.infinum.productive.models.Response;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by dino on 12/10/15.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Call<Response<LoginResponse>> login(@Field("username") String username, @Field("password") String password);

    // TODO specify REST API
}
