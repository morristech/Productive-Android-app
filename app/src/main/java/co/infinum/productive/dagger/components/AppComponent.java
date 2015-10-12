package co.infinum.productive.dagger.components;


import javax.inject.Singleton;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.dagger.modules.ApiModule;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.ClientModule;
import co.infinum.productive.dagger.modules.GsonConverterModule;
import co.infinum.productive.dagger.modules.HostModule;
import dagger.Component;

/**
 * Created by dino on 03/09/15.
 */
@Component(modules = {
        AppContextModule.class,
        HostModule.class,
        GsonConverterModule.class,
        ClientModule.class,
        ApiModule.class
})
@Singleton
public interface AppComponent {

    void inject(ProductiveApp app);
}
