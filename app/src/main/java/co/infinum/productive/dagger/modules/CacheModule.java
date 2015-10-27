package co.infinum.productive.dagger.modules;

import co.infinum.productive.ProductiveApp;
import co.infinum.productive.mvp.interactors.CacheInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mjurinic on 27.10.15..
 */
@Module
public class CacheModule {

    @Provides
    public CacheInteractor provideCacheInteractor() {
        return ProductiveApp.getInstance().getCacheInteractor();
    }
}
