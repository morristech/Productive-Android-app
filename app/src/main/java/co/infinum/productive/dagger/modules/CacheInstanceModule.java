package co.infinum.productive.dagger.modules;

import javax.inject.Singleton;

import co.infinum.productive.mvp.interactors.CacheInteractor;
import co.infinum.productive.mvp.interactors.impl.CacheInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mjurinic on 27.10.15..
 */
@Module
public class CacheInstanceModule {

    @Provides
    @Singleton
    public CacheInteractor cacheInteractor() {
        return new CacheInteractorImpl();
    }
}
