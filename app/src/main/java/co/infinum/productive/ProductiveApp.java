package co.infinum.productive;

import android.app.Application;

import javax.inject.Inject;

import co.infinum.productive.network.ApiService;

public class ProductiveApp extends Application {

    protected static ProductiveApp instance;

    @Inject
    protected ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
    }

    public static ProductiveApp getInstance() {
        return instance;
    }

    public static void setInstance(ProductiveApp instance) {
        ProductiveApp.instance = instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
