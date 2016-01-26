package co.infinum.productive.models;

import java.io.Serializable;

/**
 * Created by mjurinic on 17.11.15..
 */
public class ProjectTile implements Serializable {

    private int id;
    private String projectName;
    private String avatarUrl;
    private String clientName;
    private String elapsedTime;
    private String updatedBy;

    public ProjectTile() {
        // hello
    }

    public ProjectTile(String projectName, String avatarUrl, String clientName, String elapsedTime, String updatedBy, int id) {
        this.projectName = projectName;
        this.avatarUrl = avatarUrl;
        this.clientName = clientName;
        this.elapsedTime = elapsedTime;
        this.updatedBy = updatedBy;
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
