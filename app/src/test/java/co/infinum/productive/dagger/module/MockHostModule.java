package co.infinum.productive.dagger.module;

import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noxqs on 10.11.15..
 */

@Module
public class MockHostModule {

    public static final int NETWORK_TIMEOUT_SECONDS = 1;

    private MockWebServer mockWebServer;

    public MockHostModule() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start mockWebServer");
        }
    }

    @Provides
    @Singleton
    public String provideEndpoint() {
        return mockWebServer.url("/").toString();
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    @Provides
    @Singleton
    public Integer provideNetworkTimeout() {
        return NETWORK_TIMEOUT_SECONDS;
    }
}
