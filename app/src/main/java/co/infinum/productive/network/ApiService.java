package co.infinum.productive.network;

import java.util.ArrayList;
import java.util.List;

import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.BaseResponse;
import co.infinum.productive.models.Comment;
import co.infinum.productive.models.Organization;
import co.infinum.productive.models.Project;
import co.infinum.productive.models.Task;
import co.infinum.productive.models.TaskActivityResponse;
import co.infinum.productive.models.TaskDetails;
import co.infinum.productive.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/v1/users/login")
    Call<BaseResponse<User>> login(@Field("username") String username, @Field("password") String password);

    @GET("/api/v1/organizations")
    Call<BaseResponse<ArrayList<Organization>>> getOrganizations();

    @GET("/api/v1/{organizationId}/projects")
    Call<BaseResponse<ArrayList<Project>>> getProjects(@Path("organizationId") int organizationId);

    @GET("/api/v1/{organizationId}/tasks")
    Call<BaseResponse<ArrayList<Task>>> getTasks(@Path("organizationId") int organizationId);

    @GET("/api/v1/{organizationId}/projects/{projectId}/tasks/{taskId}")
    Call<BaseResponse<ArrayList<TaskDetails>>> getTaskDetails(@Path("organizationId") int organizationId,
            @Path("projectId") int projectId,
            @Path("taskId") int taskId);

    @GET("/api/v1/{organizationId}/projects/{projectId}/tasks/{taskId}/activities")
    Call<BaseResponse<List<TaskActivityResponse>>> getTaskActivities(@Path("organizationId") int organizationId,
            @Path("projectId") int projectId,
            @Path("taskId") int taskId);

    @GET("/api/v1/{organizationId}/projects/{projectId}/tasks/{taskId}/subscribers")
    Call<BaseResponse<ArrayList<Assignee>>> getSubscribersOnTask(@Path("organizationId") int organizationId,
            @Path("projectId") int projectId,
            @Path("taskId") int taskId);


    @GET("/api/v1/{organizationId}/projects/{projectId}/tasks")
    Call<BaseResponse<ArrayList<Task>>> getTaskPerProject(@Path("organizationId") int organizationId, @Path("projectId") int projectId);

    @GET("/api/v1/{organizationId}/projects/{projectId}/tasks")
    Call<BaseResponse<ArrayList<Task>>> getMyTasks(@Path("organizationId") int organizationId,
            @Path("projectId") int projectId, @Query("filter_id") int id);


    @POST("/api/v1/{organizationId}/comments")
    Call<BaseResponse<TaskActivityResponse>> postComment(@Path("organizationId") int organizationId);

    @PATCH("/api/v1/{organizationId}/projects/{projectId}/tasks/{taskId}")
    Call<BaseResponse<Task>> updateTaskActivity(@Path("organizationId") int organizationId,
                                                @Path("projectId") int projectId,
                                                @Path("taskId") int taskId,
                                                @Body Comment comment);
}



