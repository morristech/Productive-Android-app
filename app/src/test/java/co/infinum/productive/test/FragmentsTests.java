package co.infinum.productive.test;


import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;
import org.robolectric.util.FragmentTestUtil;


import co.infinum.productive.R;
import co.infinum.productive.activities.MainActivity;
import co.infinum.productive.activities.TasksListActivity;
import co.infinum.productive.fragments.MoreFragment;
import co.infinum.productive.fragments.ProjectsFragment;
import co.infinum.productive.fragments.TaskActivityFragment;
import co.infinum.productive.fragments.TaskDetailsFragment;
import co.infinum.productive.fragments.TasksFragment;
import co.infinum.productive.helpers.CustomRobolectricGradleTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;
import co.infinum.productive.models.Project;
import co.infinum.productive.mvp.interactors.CacheInteractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hEAT- on 5.1.2016..
 */
@RunWith(CustomRobolectricGradleTestRunner.class)
public class FragmentsTests extends BaseTest {

    @Test
    public void TasksNotNull() throws Exception{
        TasksFragment fragment = new TasksFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        assertNotNull(fragment.getView());
    }

    @Test
    public void TaskActivityNotNull() throws Exception{
        TaskActivityFragment fragment = new TaskActivityFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        assertNotNull(fragment.getView());
    }
    /*
    @Test
     public void TaskDetailsNotNull() throws Exception{
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        assertNotNull(fragment.getView());
    }*/

    @Test
    public void ProjectsNotNull() throws Exception{
        ProjectsFragment fragment = new ProjectsFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        assertNotNull(fragment.getView());
    }

    @Test
    public void MoreFragmentNotNull() throws Exception{
        MoreFragment fragment = new MoreFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        assertNotNull(fragment.getView());
    }
}

    /*

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
}*/
