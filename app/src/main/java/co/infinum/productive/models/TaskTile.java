package co.infinum.productive.models;

import java.io.Serializable;

/**
 * Created by noxqs on 03.12.15..
 */
public class TaskTile implements Serializable{
    private String taskName;
    private String avatarUrl;
    private String projectName;
    private String elapsedTime;
    private String updatedBy;

    public TaskTile() {
    }

    public TaskTile(String taskName, String avatarUrl, String projectName, String elapsedTime, String updatedBy) {
        this.taskName = taskName;
        this.avatarUrl = avatarUrl;
        this.projectName = projectName;
        this.elapsedTime = elapsedTime;
        this.updatedBy = updatedBy;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String projectName) {
        this.taskName = projectName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
