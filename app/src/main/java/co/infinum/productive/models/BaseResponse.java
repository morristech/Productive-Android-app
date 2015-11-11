package co.infinum.productive.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    @SerializedName("response")
    private T response;

    @SerializedName("count")
    private int count;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
