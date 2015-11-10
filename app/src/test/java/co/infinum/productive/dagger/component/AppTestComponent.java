package co.infinum.productive.dagger.component;

import javax.inject.Singleton;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.dagger.module.MockHostModule;
import co.infinum.productive.dagger.module.SynchronousExecutorsModule;
import co.infinum.productive.dagger.modules.ApiModule;
import dagger.Component;

/**
 * Created by noxqs on 10.11.15..
 */
 @Component(modules = {
         MockHostModule.class,
         SynchronousExecutorsModule.class,
         ApiModule.class
 })
 @Singleton
public interface AppTestComponent {

    void inject (ProductiveApp app);
}
