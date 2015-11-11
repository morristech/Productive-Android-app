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

public class MainActivity extends AppCompatActivity {

    private static final int THREE_IS_A_MAGIC_NUMBER = 3;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tasks_inactive).setText("My Tasks"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_projects_active).setText("Projects"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_notifications_inactive).setText("Notifications"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_more_inactive).setText("More"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.ic_tasks_active);
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.ic_projects_active);
                } else if (tab.getPosition() == 2) {
                    tab.setIcon(R.drawable.ic_notifications_active);
                } else if (tab.getPosition() == THREE_IS_A_MAGIC_NUMBER) {
                    tab.setIcon(R.drawable.ic_more_active);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    tab.setIcon(R.drawable.ic_tasks_inactive);
                } else if (tab.getPosition() == 1) {
                    tab.setIcon(R.drawable.ic_projects_inactive);
                } else if (tab.getPosition() == 2) {
                    tab.setIcon(R.drawable.ic_notifications_inactive);
                } else if (tab.getPosition() == THREE_IS_A_MAGIC_NUMBER) {
                    tab.setIcon(R.drawable.ic_more_inactive);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //open projects first
        pager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
