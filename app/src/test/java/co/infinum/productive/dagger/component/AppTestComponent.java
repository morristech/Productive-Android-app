package co.infinum.productive.dagger.component;

import javax.inject.Singleton;

import co.infinum.productive.dagger.components.AppComponent;
import co.infinum.productive.dagger.module.ClientSynchronousModule;
import co.infinum.productive.dagger.module.MockApiModule;
import co.infinum.productive.dagger.module.MockHostModule;
import co.infinum.productive.dagger.module.SynchronousExecutorsModule;
import co.infinum.productive.dagger.modules.AppContextModule;
import co.infinum.productive.dagger.modules.CacheInstanceModule;
import co.infinum.productive.dagger.modules.GsonConverterModule;
import dagger.Component;

/**
 * Created by noxqs on 10.11.15..
 */
 @Component(modules = {
         AppContextModule.class,
         MockHostModule.class,
         GsonConverterModule.class,
         ClientSynchronousModule.class,
         MockApiModule.class,
         CacheInstanceModule.class,
         SynchronousExecutorsModule.class
 })
 @Singleton
public interface AppTestComponent extends AppComponent {

}
