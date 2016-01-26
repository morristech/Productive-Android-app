package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mjurinic on 20.01.16..
 */
public class Comment implements Serializable {

    @SerializedName("staged_comment")
    private int stagedComment;

    @SerializedName("comment_body")
    private String body;

    public Comment(int stagedComment, String body) {
        this.stagedComment = stagedComment;
        this.body = body;
    }
}
