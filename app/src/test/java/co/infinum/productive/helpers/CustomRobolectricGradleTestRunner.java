package co.infinum.productive.helpers;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

/**
 * Created by noxqs on 10.11.15..
 */
public class CustomRobolectricGradleTestRunner extends RobolectricGradleTestRunner {

    public CustomRobolectricGradleTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        AndroidManifest appManifest =  super.getAppManifest(config);
        appManifest.setPackageName("co.infinum.productive");
        return appManifest;
    }
}
