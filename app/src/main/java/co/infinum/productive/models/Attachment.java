package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mjurinic on 14.11.15..
 */
public class Attachment implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("file_type")
    private String fileType;

    @SerializedName("file_size")
    private int fileSize;

    @SerializedName("url")
    private String url;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("account_id")
    private int accountId;

    public Attachment(int id, String fileName, String fileType, int fileSize, String url, String thumb, int accountId) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.url = url;
        this.thumb = thumb;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
