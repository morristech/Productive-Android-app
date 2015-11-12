package co.infinum.productive.dagger.module;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import co.infinum.productive.network.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.Retrofit;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 12/11/15
 */
@Module
public class MockApiModule {

    @Provides
    @Singleton
    public ApiService provideApiService(String baseUrl, OkHttpClient okHttpClient, Converter.Factory converterFactory,
            Executor callbackExecutor) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .callbackExecutor(callbackExecutor)
                .addConverterFactory(converterFactory);

        return builder.build().create(ApiService.class);
    }
}
