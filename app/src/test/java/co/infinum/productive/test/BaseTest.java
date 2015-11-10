package co.infinum.productive.test;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Before;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import co.infinum.productive.ProductiveTestApp;
import co.infinum.productive.helpers.ResourceUtils;

/**
 * Created by noxqs on 10.11.15..
 */
public class BaseTest {

    private MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception {
        mockWebServer = ProductiveTestApp.getMockWebServer();
    }

    protected Gson getGson(){
        return ProductiveTestApp.getGson();
    }

    protected void enqueueResponse(String filename) {
        String body = ResourceUtils.readFromFile(filename);
        MockResponse mockResponse = new MockResponse().setBody(body).setResponseCode(HttpURLConnection.HTTP_OK);
        mockWebServer.enqueue(mockResponse);
    }

    protected void enqueueResponse(String filename, int statusCode) {
        String body = ResourceUtils.readFromFile(filename);
        MockResponse mockResponse = new MockResponse().setBody(body).setResponseCode(statusCode);
        mockWebServer.enqueue(mockResponse);
    }

    protected void enqueueEmptyResponse(int statusCode) {
        MockResponse mockResponse = new MockResponse().setBody("").setResponseCode(statusCode);
        mockWebServer.enqueue(mockResponse);
    }

    protected RecordedRequest takeLastRequest() throws InterruptedException {
        int requestCount = mockWebServer.getRequestCount();
        while (requestCount > 1) {
            mockWebServer.takeRequest(10, TimeUnit.SECONDS);
            requestCount--;
        }

        return mockWebServer.takeRequest(10, TimeUnit.SECONDS);
    }
}
