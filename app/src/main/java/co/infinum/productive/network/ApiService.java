package co.infinum.productive.network;

import java.util.ArrayList;

import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Organization;
import co.infinum.productive.models.User;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Call<BaseResponse<User>> login(@Field("username") String username, @Field("password") String password);

    @GET("/api/v1/organizations")
    Call<BaseResponse<ArrayList<Organization>>> getOrganizations();

    // TODO specify REST API
}
