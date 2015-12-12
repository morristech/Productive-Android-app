package co.infinum.productive.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.adapters.ViewPagerAdapter;
import co.infinum.productive.fragments.TaskActivityFragment;
import co.infinum.productive.fragments.TaskDetailsFragment;
import co.infinum.productive.models.Task;

/**
 * Created by mjurinic on 28.11.15..
 */
public class TaskContentActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    public static final String TASK = "task";

    @Bind(R.id.task_view_pager)
    ViewPager viewPager;

    @Bind(R.id.task_tab_layout)
    TabLayout tabLayout;

    private ArrayList<Fragment> fragmentList;

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_content);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        task = (Task) getIntent().getSerializableExtra(TASK);

        setTitle(task.getTitle());

        initTabs();
        initFragments();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(this);
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.task_content_tab_activity));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.task_content_tab_details));
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new TaskActivityFragment());
        fragmentList.add(new TaskDetailsFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_details_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                //TODO handle menu clicks
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
