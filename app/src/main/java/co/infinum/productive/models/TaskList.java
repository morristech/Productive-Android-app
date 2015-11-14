package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mjurinic on 14.11.15..
 */
public class TaskList implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("project_id")
    private int projectId;

    @SerializedName("name")
    private String name;

    @SerializedName("active")
    private boolean isActive;

    @SerializedName("position")
    private int position;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("deleted")
    private boolean isDeleted;

    public TaskList(int id, int projectId, String name, boolean isActive, int position, int accountId, boolean isDeleted) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.isActive = isActive;
        this.position = position;
        this.accountId = accountId;
        this.isDeleted = isDeleted;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
