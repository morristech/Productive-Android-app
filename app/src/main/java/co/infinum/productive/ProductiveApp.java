package co.infinum.productive;

import android.app.Application;

import javax.inject.Inject;

import co.infinum.productive.dagger.components.DaggerAppComponent;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.network.ApiService;

public class ProductiveApp extends Application {

    protected static ProductiveApp instance;

    @Inject
    protected ApiService apiService;

    @Inject
    protected CacheInteractor cacheInteractor;

    public static ProductiveApp getInstance() {
        return instance;
    }

    public static void setInstance(ProductiveApp instance) {
        ProductiveApp.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        DaggerAppComponent.create().inject(this);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public CacheInteractor getCacheInteractor() {
        return cacheInteractor;
    }
}
