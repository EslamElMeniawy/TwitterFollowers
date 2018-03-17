package elmeniawy.eslam.twitterfollowers.root;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

import com.twitter.sdk.android.core.Twitter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import elmeniawy.eslam.twitterfollowers.BuildConfig;
import elmeniawy.eslam.twitterfollowers.helpers.LocalHelper;
import elmeniawy.eslam.twitterfollowers.screens.follower_info.FollowerInfoModule;
import elmeniawy.eslam.twitterfollowers.screens.followers_list.FollowersListModule;
import elmeniawy.eslam.twitterfollowers.screens.image_browser.BrowseImageModule;
import elmeniawy.eslam.twitterfollowers.screens.login.LoginModule;
import elmeniawy.eslam.twitterfollowers.screens.splash.SplashModule;
import elmeniawy.eslam.twitterfollowers.screens.welcome.WelcomeModule;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.storage.preferences.SharedPreferencesModule;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;
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

    @Inject
    MySharedPreferences mySharedPreferences;

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
                .followersListModule(new FollowersListModule())
                .followerInfoModule(new FollowerInfoModule())
                .browseImageModule(new BrowseImageModule())
                .build();

        //
        // Inject application instance.
        //

        component.inject(this);

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

        //
        // Set app language.
        //

        String lang = mySharedPreferences.getString(PreferencesUtils.KEY_LANG);
        Timber.i("Call set local from on create with lang: %s.", lang);
        LocalHelper.setLocale(this, lang);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //
        // Set app language.
        //

        String lang = mySharedPreferences.getString(PreferencesUtils.KEY_LANG);
        Timber.i("Call set local from on configuration change with lang: %s.", lang);
        LocalHelper.setLocale(this, lang);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public ApplicationComponent getComponent() {
        return component;
    }

//    private void setLocale() {
//        final Resources resources = getResources();
//        final Configuration configuration = resources.getConfiguration();
//
//        final Locale locale = LocalHelper
//                .getLocale(mySharedPreferences.getString(PreferencesUtils.KEY_LANG));
//
//        if (!configuration.locale.equals(locale)) {
//            configuration.setLocale(locale);
//            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//        }
//    }
}
