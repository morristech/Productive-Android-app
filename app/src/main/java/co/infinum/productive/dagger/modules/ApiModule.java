package co.infinum.productive.dagger.modules;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import co.infinum.productive.network.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public ApiService provideApiService(String baseUrl, OkHttpClient okHttpClient, Converter.Factory converterFactory) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converterFactory);

        return builder.build().create(ApiService.class);
    }
}
