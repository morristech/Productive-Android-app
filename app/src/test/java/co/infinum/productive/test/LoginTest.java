package co.infinum.productive.test;


import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;


import java.util.ArrayList;

import co.infinum.productive.R;
import co.infinum.productive.activities.LoginActivity;
import co.infinum.productive.helpers.CustomRobolectricGradleTestRunner;
import co.infinum.productive.models.Organization;
import co.infinum.productive.mvp.Listener;
import co.infinum.productive.mvp.interactors.OrganizationInteractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by noxqs on 10.11.15..
 */
@RunWith(CustomRobolectricGradleTestRunner.class)
public class LoginTest extends BaseTest {

    OrganizationInteractor organizationInteractor;
    Listener<ArrayList<Organization>> organizationListener;

    @Test
    public void successfulLogin() {
        ActivityController<LoginActivity> loginActivityActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity loginActivity = loginActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText emailEditText = (EditText) loginActivity.findViewById(R.id.et_email);
        EditText passwordEditText = (EditText) loginActivity.findViewById(R.id.et_password);
        Button loginButton = (Button) loginActivity.findViewById(R.id.login_button);

        emailEditText.setText("skliba@foi.hr");
        passwordEditText.setText("servis");
        loginButton.performClick();
        enqueueResponse("user.json", 200);

        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wrongEmailFormatLogin(){
        ActivityController<LoginActivity> loginActivityActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity loginActivity = loginActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText emailEditText = (EditText) loginActivity.findViewById(R.id.et_email);
        EditText passwordEditText = (EditText) loginActivity.findViewById(R.id.et_password);
        Button loginButton = (Button) loginActivity.findViewById(R.id.login_button);

        emailEditText.setText("skliba@com");
        passwordEditText.setText("servis");
        loginButton.performClick();
        enqueueResponse("user.json", 200);

        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wrongPasswordFormatLogin(){
        ActivityController<LoginActivity> loginActivityActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity loginActivity = loginActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText emailEditText = (EditText) loginActivity.findViewById(R.id.et_email);
        EditText passwordEditText = (EditText) loginActivity.findViewById(R.id.et_password);
        Button loginButton = (Button) loginActivity.findViewById(R.id.login_button);

        emailEditText.setText("skliba@foi.hr");
        passwordEditText.setText("proba");
        loginButton.performClick();
        enqueueResponse("user.json", 200);

        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void togglePasswordVisability(){
        ActivityController<LoginActivity> loginActivityActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity loginActivity = loginActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText emailEditText = (EditText) loginActivity.findViewById(R.id.et_email);
        EditText passwordEditText = (EditText) loginActivity.findViewById(R.id.et_password);
        Button togglePassword = (Button)loginActivity.findViewById(R.id.toggle_password);
        Button loginButton = (Button) loginActivity.findViewById(R.id.login_button);

        emailEditText.setText("skliba@foi.hr");
        passwordEditText.setText("servis");
        togglePassword.performClick();
        loginButton.performClick();
        enqueueResponse("user.json", 200);

        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void fetchOrganizationsList(){
        ActivityController<LoginActivity> loginActivityActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity loginActivity = loginActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText emailEditText = (EditText) loginActivity.findViewById(R.id.et_email);
        EditText passwordEditText = (EditText) loginActivity.findViewById(R.id.et_password);
        Button loginButton = (Button) loginActivity.findViewById(R.id.login_button);

        emailEditText.setText("skliba@foi.hr");
        passwordEditText.setText("servis");
        loginButton.performClick();
        enqueueResponse("user.json", 200);

        organizationInteractor.fetchOrganizations(organizationListener);

        /*
        int countList = rV.getCount();
        int countAdapter = rV.getAdapter().getCount();

        assertEquals(10, countList);
        assertEquals(10, countAdapter);*/

        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
