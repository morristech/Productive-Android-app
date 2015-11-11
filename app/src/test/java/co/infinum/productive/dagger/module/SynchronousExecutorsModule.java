package co.infinum.productive.dagger.module;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noxqs on 10.11.15..
 */

/*
* this should be used only for testing purposes, if run on a device it will result in NetworkOnMainThreadException
*/

@Module
public class SynchronousExecutorsModule {

    class SychronousExecutor implements Executor{

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    @Provides
    @Named("HttpExecutor")
    public Executor provideHttpExecutor(){
        return new SychronousExecutor();
    }

    @Provides
    @Named("CallbackExecutor")
    public Executor provideCallbackExecutor(){
        return new SychronousExecutor();
    }

}
