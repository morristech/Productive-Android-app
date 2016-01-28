package co.infinum.productive.test;

import android.support.v7.widget.RecyclerView;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;


import javax.inject.Inject;

import co.infinum.productive.R;
import co.infinum.productive.adapters.ProjectAdapter;
import co.infinum.productive.fragments.ProjectsFragment;
import co.infinum.productive.helpers.CustomRobolectricGradleTestRunner;


/**
 * Created by hEAT- on 28.1.2016..
 */

@RunWith(CustomRobolectricGradleTestRunner.class)
public class ProjectTest extends BaseTest{

    @Inject
    ProjectAdapter mockAdapter;

    @Test
    public void ClickOnProject() throws Exception{
        ProjectsFragment fragment = new ProjectsFragment();
        SupportFragmentTestUtil.startVisibleFragment(fragment);

        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.projects_recycler_view);
        //RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);

        recyclerView.performClick();
        

    }
}
