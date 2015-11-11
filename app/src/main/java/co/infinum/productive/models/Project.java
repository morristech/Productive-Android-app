package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by mjurinic on 11.11.15..
 */
public class Project implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("updated_at")
    private DateTime updatedAt;

    @SerializedName("project_url")
    private String projectUrl;

    @SerializedName("tasks_url")
    private String tasksUrl;

    @SerializedName("task_lists_url")
    private String taskListsUrl;

    @SerializedName("people_url")
    private String peopleUrl;

    @SerializedName("subscribers_url")
    private String subscribersUrl;

    @SerializedName("tags_url")
    private String tagsUrl;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("deleted")
    private boolean deleted;

    @SerializedName("client")
    private Client client;

    @SerializedName("project_manager")
    private ProjectManager projectManager;

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

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getTasksUrl() {
        return tasksUrl;
    }

    public void setTasksUrl(String tasksUrl) {
        this.tasksUrl = tasksUrl;
    }

    public String getTaskListsUrl() {
        return taskListsUrl;
    }

    public void setTaskListsUrl(String taskListsUrl) {
        this.taskListsUrl = taskListsUrl;
    }

    public String getPeopleUrl() {
        return peopleUrl;
    }

    public void setPeopleUrl(String peopleUrl) {
        this.peopleUrl = peopleUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }
}
