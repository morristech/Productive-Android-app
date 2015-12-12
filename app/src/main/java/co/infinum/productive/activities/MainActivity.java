package co.infinum.productive.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.adapters.ViewPagerAdapter;
import co.infinum.productive.fragments.MoreFragment;
import co.infinum.productive.fragments.NotificationsFragment;
import co.infinum.productive.fragments.ProjectsFragment;
import co.infinum.productive.fragments.TasksFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager pager;

    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initTabs();
        initFragmentList();


        final PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentArrayList);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);

        //open projects first
        pager.setCurrentItem(1);
    }

    private void initFragmentList() {
        fragmentArrayList = new ArrayList<>();

        fragmentArrayList.add(new TasksFragment());
        fragmentArrayList.add(new ProjectsFragment());
        fragmentArrayList.add(new NotificationsFragment());
        fragmentArrayList.add(new MoreFragment());
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(inflateTabItem(R.drawable.tasks_tab_selector, R.string.my_tasks_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(inflateTabItem(R.drawable.projects_tab_selector, R.string.projects_tab)));
        tabLayout.addTab(tabLayout.newTab()
                                  .setCustomView(inflateTabItem(R.drawable.notifications_tab_selector, R.string.notifications_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(inflateTabItem(R.drawable.more_tab_selector, R.string.more_tab)));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private View inflateTabItem(int iconResourceId, int textResourceId) {
        LinearLayout tabItemLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.main_activity_tab_item, null);

        ImageView tabIcon = (ImageView) tabItemLayout.findViewById(R.id.main_activity_tab_icon);
        tabIcon.setImageResource(iconResourceId);

        TextView tabText = (TextView) tabItemLayout.findViewById(R.id.main_activity_tab_text);
        tabText.setText(textResourceId);

        return tabItemLayout;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
