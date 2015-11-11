package co.infinum.productive.dagger.components;


import javax.inject.Singleton;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.dagger.modules.ApiModule;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.CacheInstanceModule;
import co.infinum.productive.dagger.modules.ClientModule;
import co.infinum.productive.dagger.modules.GsonConverterModule;
import co.infinum.productive.dagger.modules.HostModule;
import dagger.Component;


@Component(modules = {
        AppContextModule.class,
        HostModule.class,
        GsonConverterModule.class,
        ClientModule.class,
        ApiModule.class,
        CacheInstanceModule.class,
})
@Singleton
public interface AppComponent {

    void inject(ProductiveApp app);
}
