package co.infinum.productive.dagger.modules;

import javax.inject.Singleton;

import co.infinum.productive.BuildConfig;
import dagger.Module;
import dagger.Provides;

@Module
public class HostModule {

    @Provides
    @Singleton
    public String provideHost() {
        return BuildConfig.API_URL;
    }
}
