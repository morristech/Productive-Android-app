package co.infinum.productive.dagger.component;

import javax.inject.Singleton;

import co.infinum.productive.dagger.components.AppComponent;
import co.infinum.productive.dagger.module.MockHostModule;
import co.infinum.productive.dagger.module.SynchronousExecutorsModule;
import co.infinum.productive.dagger.modules.ApiModule;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.CacheInstanceModule;
import co.infinum.productive.dagger.modules.ClientModule;
import co.infinum.productive.dagger.modules.GsonConverterModule;
import co.infinum.productive.dagger.modules.HostModule;
import dagger.Component;

/**
 * Created by noxqs on 10.11.15..
 */
 @Component(modules = {
         AppContextModule.class,
         MockHostModule.class,
         GsonConverterModule.class,
         ClientModule.class,
         ApiModule.class,
         CacheInstanceModule.class,
         SynchronousExecutorsModule.class
 })
 @Singleton
public interface AppTestComponent extends AppComponent {

}
