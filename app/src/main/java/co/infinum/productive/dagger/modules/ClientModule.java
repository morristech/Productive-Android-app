package co.infinum.productive.dagger.modules;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ClientModule {

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        return new OkHttpClient();
    }
}