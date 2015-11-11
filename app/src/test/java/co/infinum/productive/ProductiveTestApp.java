package co.infinum.productive;

import com.google.gson.Gson;

import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.robolectric.TestLifecycleApplication;

import android.annotation.SuppressLint;
import android.util.Log;

import java.lang.reflect.Method;

import co.infinum.productive.dagger.component.DaggerAppTestComponent;
import co.infinum.productive.dagger.module.MockHostModule;

/**
 * Created by noxqs on 10.11.15..
 */
public class ProductiveTestApp extends ProductiveApp implements TestLifecycleApplication {

    private static MockWebServer mockWebServer;

    public static MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    public static void setMockWebServer(MockHostModule mockHostModule) {
        mockWebServer = mockHostModule.getMockWebServer();
    }

    public static Gson getGson(){
        return new Gson();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate() {
        //Can't call super, otherwise dependencies will get injected
        setInstance(this);
    }

    @Override
    public void beforeTest(Method method) {
        MockHostModule mockHostModule = new MockHostModule();
        setMockWebServer(mockHostModule);
        DaggerAppTestComponent.builder()
                .mockHostModule(mockHostModule)
                .build()
                .inject(this);
    }

    @Override
    public void prepareTest(Object test) {

    }

    @Override
    public void afterTest(Method method) {
        try {
            mockWebServer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
