package co.infinum.productive.dagger.components;

import co.infinum.productive.activities.TasksListActivity;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.CacheModule;
import co.infinum.productive.dagger.modules.NetworkModule;
import co.infinum.productive.dagger.modules.TasksModule;
import co.infinum.productive.fragments.TaskDetailsFragment;
import co.infinum.productive.fragments.TasksFragment;
import dagger.Component;

/**
 * Created by noxqs on 15.11.15..
 */
@Component(modules = {
        CacheModule.class,
        NetworkModule.class,
        TasksModule.class,
        AppContextModule.class
})
public interface TasksComponent {

    void inject(TasksFragment fragment);
    void inject(TaskDetailsFragment fragment);
    void inject(TasksListActivity activity);
}
