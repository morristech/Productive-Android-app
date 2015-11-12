package co.infinum.productive.network;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import co.infinum.productive.ProductiveApp;

/**
 * Created by mjurinic on 22.10.15..
 */
public class RequestInterceptor implements Interceptor {

    public static final String LOGIN_URL = "/api/v1/users/login";

    public static final String TOKEN = "token";

    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request changedRequest;

        if (!originalRequest.uri().getPath().equals(LOGIN_URL)) {
            Request.Builder builder = originalRequest.newBuilder();
            HttpUrl changedUrl = originalRequest.httpUrl().newBuilder()
                    .addQueryParameter(TOKEN, ProductiveApp.getInstance().getCacheInteractor().getUser().getToken())
                    .build();

            builder.url(changedUrl.toString());
            changedRequest = builder.build();

            return chain.proceed(changedRequest);
        }

        return chain.proceed(originalRequest);
    }
}
