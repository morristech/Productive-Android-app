package co.infinum.productive.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initTabs();

        final PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);

        //open projects first
        pager.setCurrentItem(1);
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tasks_inactive).setText(R.string.my_tasks_tab));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_projects_active).setText(R.string.projects_tab));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_notifications_inactive).setText(R.string.notifications_tab));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_more_inactive).setText(R.string.more_tab));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.ic_tasks_active);
                break;
            case 1:
                tab.setIcon(R.drawable.ic_projects_active);
                break;
            case 2:
                tab.setIcon(R.drawable.ic_notifications_active);
                break;
            default:
                tab.setIcon(R.drawable.ic_more_active);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                tab.setIcon(R.drawable.ic_tasks_inactive);
                break;
            case 1:
                tab.setIcon(R.drawable.ic_projects_inactive);
                break;
            case 2:
                tab.setIcon(R.drawable.ic_notifications_inactive);
                break;
            default:
                tab.setIcon(R.drawable.ic_more_inactive);
                break;
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
