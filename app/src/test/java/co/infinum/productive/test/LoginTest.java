package co.infinum.productive.test;

import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import co.infinum.productive.R;
import co.infinum.productive.activities.LoginActivity;
import co.infinum.productive.helpers.CustomRobolectricGradleTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by noxqs on 10.11.15..
 */
@RunWith(CustomRobolectricGradleTestRunner.class)
public class LoginTest extends BaseTest {

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
}
