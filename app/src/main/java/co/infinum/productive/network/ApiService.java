package co.infinum.productive.network;

import java.util.ArrayList;

import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Organization;
import co.infinum.productive.models.Project;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskDetails;
import co.infinum.productive.models.User;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Call<BaseResponse<User>> login(@Field("username") String username, @Field("password") String password);

    @GET("/api/v1/organizations")
    Call<BaseResponse<ArrayList<Organization>>> getOrganizations();

    @GET("/api/v1/{organizationId}/projects")
    Call<BaseResponse<ArrayList<Project>>> getProjects(@Path("organizationId") int organizationId);

    @GET("/api/v1/projects/{projectId}/tasks")  //TODO sort response by updated_at DESC
    Call<BaseResponse<ArrayList<Task>>> getTasks(@Path("projectId") int projectId);

    @GET("/api/v1/{organizationId}/projects/{projectId}/tasks/{taskId}")
    Call<BaseResponse<ArrayList<TaskDetails>>> getTaskDetails(@Path("organizationId") int organizationId,
                                                              @Path("projectId") int projectId,
                                                              @Path("taskId") int taskId);

    // TODO specify REST API
}
