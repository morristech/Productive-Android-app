package co.infinum.productive.dagger.components;

import co.infinum.productive.dagger.modules.LoginModule;
import co.infinum.productive.dagger.modules.NetworkModule;
import dagger.Component;

/**
 * Created by dino on 12/10/15.
 */
@Component(modules = {
        NetworkModule.class,
        LoginModule.class
})
public interface LoginComponent {

}
