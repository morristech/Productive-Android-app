package co.infinum.productive.dagger.components;

import co.infinum.productive.activities.LoginActivity;
import co.infinum.productive.dagger.modules.LoginModule;
import co.infinum.productive.dagger.modules.NetworkModule;
import dagger.Component;


@Component(modules = {
        NetworkModule.class,
        LoginModule.class,
})
public interface LoginComponent {

    void inject(LoginActivity activity);
}
