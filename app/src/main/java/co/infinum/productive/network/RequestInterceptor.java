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

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request changedRequest;

        if (!originalRequest.urlString().equals("https://productive.io/api/v1/users/login")) {
            Request.Builder builder = new Request.Builder();

            builder.url(originalRequest.urlString() + "?token=" + SharedPrefsHelper.getToken());

            if (originalRequest.body() != null) {
                builder.put(originalRequest.body());
            }

            changedRequest = builder.build();

            return chain.proceed(changedRequest);
        }

        return chain.proceed(originalRequest);
    }
}
