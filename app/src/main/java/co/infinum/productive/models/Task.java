package co.infinum.productive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mjurinic on 14.11.15..
 */
public class Task implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("project_id")
    @Expose
    private int projectId;

    @SerializedName("project_name")
    @Expose
    private String projectName;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("due_date")
    private LocalDate dueDate;

    @SerializedName("private")
    private boolean isPrivate;

    @SerializedName("updated_at")
    private DateTime updatedAt;

    @SerializedName("labels")
    private ArrayList<Label> labels;

    @SerializedName("closed")
    private boolean isClosed;

    @SerializedName("position")
    private int position;

    @SerializedName("project_url")
    private String projectUrl;

    @SerializedName("tasks_url")
    private String tasksUrl;

    @SerializedName("subscribers_url")
    private String subscribersUrl;

    @SerializedName("activities_url")
    private String activitiesUrl;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("project")
    private Project project;

    @SerializedName("task_list")
    private TaskList taskList;

    @SerializedName("assignee")
    private Assignee assignee;

    @SerializedName("updater")
    private Assignee updater;

    public Assignee getUpdater() {
        return updater;
    }

    public void setUpdater(Assignee updater) {
        this.updater = updater;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Label> labels) {
        this.labels = labels;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getActivitiesUrl() {
        return activitiesUrl;
    }

    public void setActivitiesUrl(String activitiesUrl) {
        this.activitiesUrl = activitiesUrl;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }
}
