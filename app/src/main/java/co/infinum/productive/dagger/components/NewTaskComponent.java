package co.infinum.productive.dagger.components;

import co.infinum.productive.activities.NewTaskActivity;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.NetworkModule;
import co.infinum.productive.dagger.modules.NewTaskModule;
import dagger.Component;

/**
 * Created by noxqs on 20.01.16..
 */
@Component(modules = {
        NetworkModule.class,
        AppContextModule.class,
        NewTaskModule.class
})
public interface NewTaskComponent {

    void inject(NewTaskActivity activity);
}
