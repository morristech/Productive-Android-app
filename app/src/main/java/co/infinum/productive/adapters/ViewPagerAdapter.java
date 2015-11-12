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

    int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numb) {
        super(fm);
        this.numberOfTabs = numb;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TasksFragment();
            case 1:
                return new ProjectsFragment();
            case 2:
                return new NotificationsFragment();
            default:
                return new MoreFragment();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
