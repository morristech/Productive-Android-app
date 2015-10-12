package co.infinum.productive.dagger.modules;

import android.content.Context;
import android.content.res.Resources;

import co.infinum.productive.ProductiveApp;
import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    @Provides
    public Context provideContext() {
        return ProductiveApp.getInstance();
    }

    @Provides
    public Resources provideResources(Context context) {
        return context.getResources();
    }
}
