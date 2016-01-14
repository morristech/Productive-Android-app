package co.infinum.productive.test;


import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;


import java.util.ArrayList;

import co.infinum.productive.R;
import co.infinum.productive.activities.MainActivity;
import co.infinum.productive.helpers.CustomRobolectricGradleTestRunner;
import co.infinum.productive.models.Project;
import co.infinum.productive.mvp.interactors.CacheInteractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by hEAT- on 5.1.2016..
 */
@RunWith(CustomRobolectricGradleTestRunner.class)
public class ProjectsTest  extends BaseTest{

    CacheInteractor cacheInteractor;
    /*
    @Test
    public void fetchProjectList(){
        ActivityController<MainActivity> mainActivityActivityController = Robolectric.buildActivity(MainActivity.class);

        MainActivity mainActivity = mainActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();



        cacheInteractor.cacheProjects(ArrayList < Project > projects);

        ArrayList<Project> getProjects();

        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void clickOnEachProject(){
        ActivityController<MainActivity> mainActivityActivityController = Robolectric.buildActivity(MainActivity.class);

        MainActivity mainActivity = mainActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        RecyclerView recyclerView = (RecyclerView) mainActivity.findViewById(R.id.recycler_view);

        recyclerView.measure(0,0);
        recyclerView.layout(0,0,100, 1000);
        int brojItema = recyclerView.getChildCount();

        for (int i=0; i<brojItema; i++)
        {
            recyclerView.getChildAt(i).performClick();
            mainActivity.onBackPressed();
        }


        try {
            RecordedRequest recordedRequest = takeLastRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/x-www-form-urlencoded"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
