package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mjurinic on 22.10.15..
 */
public class Organization implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("deals_url")
    private String dealsUrl;

    @SerializedName("projects_url")
    private String projectsUrl;

    @SerializedName("notifications_url")
    private String notificationsUrl;

    @SerializedName("dismiss_all_notifications_url")
    private String dismissAllNotificationsUrl;

    @SerializedName("tasks_url")
    private String tasksUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDealsUrl() {
        return dealsUrl;
    }

    public void setDealsUrl(String dealsUrl) {
        this.dealsUrl = dealsUrl;
    }

    public String getProjectsUrl() {
        return projectsUrl;
    }

    public void setProjectsUrl(String projectsUrl) {
        this.projectsUrl = projectsUrl;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    public String getDismissAllNotificationsUrl() {
        return dismissAllNotificationsUrl;
    }

    public void setDismissAllNotificationsUrl(String dismissAllNotificationsUrl) {
        this.dismissAllNotificationsUrl = dismissAllNotificationsUrl;
    }

    public String getTasksUrl() {
        return tasksUrl;
    }

    public void setTasksUrl(String tasksUrl) {
        this.tasksUrl = tasksUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
