package elmeniawy.eslam.twitterfollowers.root;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import elmeniawy.eslam.twitterfollowers.storage.preferences.SharedPreferencesModule;

/**
 * ApplicationComponent
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class,
        SharedPreferencesModule.class})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {
}
