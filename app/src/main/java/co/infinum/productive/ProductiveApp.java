package co.infinum.productive;

import android.app.Application;

import javax.inject.Inject;

import co.infinum.productive.dagger.components.DaggerAppComponent;
import co.infinum.productive.models.User;
import co.infinum.productive.network.ApiService;

public class ProductiveApp extends Application {

    protected static ProductiveApp instance;

    protected static User user;

    @Inject
    protected ApiService apiService;

    public static ProductiveApp getInstance() {
        return instance;
    }

    public static void setInstance(ProductiveApp instance) {
        ProductiveApp.instance = instance;
    }

    public static void setUserSession(User user) {
        ProductiveApp.user = user;
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
}
