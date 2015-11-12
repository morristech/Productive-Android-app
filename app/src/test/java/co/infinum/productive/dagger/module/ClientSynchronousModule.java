package co.infinum.productive.dagger.module;

import com.squareup.okhttp.Dispatcher;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import co.infinum.productive.network.RequestInterceptor;
import dagger.Module;
import dagger.Provides;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 12/11/15
 */
@Module
public class ClientSynchronousModule {

    @Provides
    @Singleton
    public OkHttpClient provideClient(ExecutorService service) {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new RequestInterceptor());
        client.setDispatcher(new Dispatcher(service));
        return client;
    }
}
