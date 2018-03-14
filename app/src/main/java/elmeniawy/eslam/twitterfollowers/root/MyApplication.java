package elmeniawy.eslam.twitterfollowers.root;

import android.app.Activity;
import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import elmeniawy.eslam.twitterfollowers.BuildConfig;
import elmeniawy.eslam.twitterfollowers.screens.login.LoginModule;
import elmeniawy.eslam.twitterfollowers.screens.splash.SplashModule;
import elmeniawy.eslam.twitterfollowers.screens.welcome.WelcomeModule;
import elmeniawy.eslam.twitterfollowers.storage.preferences.SharedPreferencesModule;
import timber.log.Timber;

/**
 * MyApplication
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class MyApplication extends Application implements HasActivityInjector {
    private ApplicationComponent component;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        //
        // Initialize application component.
        //

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule())
                .splashModule(new SplashModule())
                .welcomeModule(new WelcomeModule())
                .loginModule(new LoginModule())
                .build();

        //
        // Initialize timber.
        //

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        //
        // Initialize twitter kit.
        //

        Twitter.initialize(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
