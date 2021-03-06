package elmeniawy.eslam.twitterfollowers.root;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import elmeniawy.eslam.twitterfollowers.helpers.BaseActivity;
import elmeniawy.eslam.twitterfollowers.screens.follower_info.ActivityFollowerInfo;
import elmeniawy.eslam.twitterfollowers.screens.follower_info.FollowerInfoModule;
import elmeniawy.eslam.twitterfollowers.screens.followers_list.ActivityFollowersList;
import elmeniawy.eslam.twitterfollowers.screens.followers_list.FollowersListModule;
import elmeniawy.eslam.twitterfollowers.screens.image_browser.ActivityBrowseImage;
import elmeniawy.eslam.twitterfollowers.screens.image_browser.BrowseImageModule;
import elmeniawy.eslam.twitterfollowers.screens.login.ActivityLogin;
import elmeniawy.eslam.twitterfollowers.screens.login.LoginModule;
import elmeniawy.eslam.twitterfollowers.screens.splash.ActivitySplash;
import elmeniawy.eslam.twitterfollowers.screens.splash.SplashModule;
import elmeniawy.eslam.twitterfollowers.screens.welcome.ActivityWelcome;
import elmeniawy.eslam.twitterfollowers.screens.welcome.WelcomeModule;
import elmeniawy.eslam.twitterfollowers.storage.database.ApplicationDatabaseModule;
import elmeniawy.eslam.twitterfollowers.storage.preferences.SharedPreferencesModule;

/**
 * ApplicationComponent
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class,
        SharedPreferencesModule.class, ApplicationDatabaseModule.class, SplashModule.class,
        WelcomeModule.class, LoginModule.class, FollowersListModule.class, FollowerInfoModule.class,
        BrowseImageModule.class})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {
    void inject(BaseActivity target);

    void inject(ActivitySplash target);

    void inject(ActivityWelcome target);

    void inject(ActivityLogin target);

    void inject(ActivityFollowersList target);

    void inject(ActivityFollowerInfo target);

    void inject(ActivityBrowseImage target);
}
