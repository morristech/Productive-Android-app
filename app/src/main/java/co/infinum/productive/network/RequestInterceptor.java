package co.infinum.productive.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import co.infinum.productive.helpers.SharedPrefsHelper;

/**
 * Created by mjurinic on 22.10.15..
 */
public class RequestInterceptor implements Interceptor {

    private static final String LOGIN_URL = "https://productive.io/api/v1/users/login";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request changedRequest;

        if (!originalRequest.urlString().equals(LOGIN_URL)) {
            Request.Builder builder = originalRequest.newBuilder();

            builder.url(originalRequest.urlString() + "?token=" + SharedPrefsHelper.getToken());
            changedRequest = builder.build();

            return chain.proceed(changedRequest);
        }

        return chain.proceed(originalRequest);
    }
}
