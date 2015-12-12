package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by noxqs on 15.11.15..
 */
public class TestShit {

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
