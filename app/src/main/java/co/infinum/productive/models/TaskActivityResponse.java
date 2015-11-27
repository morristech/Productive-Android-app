package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mjurinic on 17.11.15..
 */
public class TaskActivityResponse implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("body")
    private String body;

    @SerializedName("created_at")
    private DateTime createdAt;

    @SerializedName("title")
    private String title;

    @SerializedName("event")
    private String event;

    @SerializedName("deleted")
    private boolean isDeleted;

    @SerializedName("person")
    private Assignee person;

    @SerializedName("attachments")
    private List<Attachment> attachment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Assignee getPerson() {
        return person;
    }

    public void setPerson(Assignee person) {
        this.person = person;
    }

    public List<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }
}
