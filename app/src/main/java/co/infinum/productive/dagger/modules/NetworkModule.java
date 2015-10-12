package co.infinum.productive.dagger.modules;


import co.infinum.productive.ProductiveApp;
import co.infinum.productive.network.ApiService;
import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    public ApiService provideApiService() {
        return ProductiveApp.getInstance().getApiService();
    }
}
