package co.infinum.productive.dagger.components;

import co.infinum.productive.activities.SplashActivity;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.SplashModule;
import dagger.Component;

/**
 * Created by noxqs on 03.12.15..
 */
@Component(modules = {
        SplashModule.class,
        AppContextModule.class
})
public interface SplashComponent {
    void inject(SplashActivity activity);
}
