package co.infinum.productive.dagger.components;

import co.infinum.productive.dagger.modules.NetworkModule;
import co.infinum.productive.dagger.modules.TaskActivitiesFragmentModule;
import co.infinum.productive.fragments.TaskActivityFragment;
import dagger.Component;

/**
 * Created by mjurinic on 29.12.15..
 */
@Component(modules = {
        NetworkModule.class,
        TaskActivitiesFragmentModule.class
})
public interface TaskActivitiesComponent {
    void inject(TaskActivityFragment fragment);
}
