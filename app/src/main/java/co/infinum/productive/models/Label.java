package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mjurinic on 14.11.15..
 */
public class Label implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("taggings_count")
    private int taggingsCount;

    public Label(int id, String name, int taggingsCount) {
        this.id = id;
        this.name = name;
        this.taggingsCount = taggingsCount;
    }

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

    public int getTaggingsCount() {
        return taggingsCount;
    }

    public void setTaggingsCount(int taggingsCount) {
        this.taggingsCount = taggingsCount;
    }
}
