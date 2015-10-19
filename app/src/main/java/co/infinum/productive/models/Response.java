package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Response<T> implements Serializable {

    @SerializedName("response")
    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
