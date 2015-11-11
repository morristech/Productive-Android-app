package co.infinum.productive.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.infinum.productive.fragments.MoreFragment;
import co.infinum.productive.fragments.NotificationsFragment;
import co.infinum.productive.fragments.ProjectsFragment;
import co.infinum.productive.fragments.TasksFragment;

/**
 * Created by noxqs on 10.11.15..
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final int THREE_IS_A_MAGIC_NUMBER = 3;

    int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numb) {
        super(fm);
        this.numberOfTabs = numb;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TasksFragment tasksFragment = new TasksFragment();
                return tasksFragment;
            case 1:
                ProjectsFragment projectsFragment = new ProjectsFragment();
                return projectsFragment;
            case 2:
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return notificationsFragment;
            case THREE_IS_A_MAGIC_NUMBER:
                MoreFragment moreFragment = new MoreFragment();
                return moreFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
