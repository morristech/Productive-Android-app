package co.infinum.productive.dagger.modules;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import co.infinum.productive.network.RequestInterceptor;
import dagger.Module;
import dagger.Provides;

@Module
public class ClientModule {

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new RequestInterceptor());

        return client;
    }
}