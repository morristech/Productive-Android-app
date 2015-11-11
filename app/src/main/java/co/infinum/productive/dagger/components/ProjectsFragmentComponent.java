package co.infinum.productive.dagger.components;

import co.infinum.productive.dagger.modules.CacheModule;
import co.infinum.productive.dagger.modules.NetworkModule;
import co.infinum.productive.dagger.modules.ProjectsFragmentModule;
import co.infinum.productive.fragments.ProjectsFragment;
import dagger.Component;

/**
 * Created by mjurinic on 11.11.15..
 */
@Component(modules = {
        CacheModule.class,
        NetworkModule.class,
        ProjectsFragmentModule.class,
})
public interface ProjectsFragmentComponent {

    void inject(ProjectsFragment fragmet);
}
