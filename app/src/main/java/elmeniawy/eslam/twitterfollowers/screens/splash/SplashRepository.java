package elmeniawy.eslam.twitterfollowers.screens.splash;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;

/**
 * SplashRepository
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class SplashRepository implements Repository {
    @Override
    public boolean getRunBefore(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(PreferencesUtils.KEY_RUN_BEFORE);
    }

    @Override
    public boolean getLoggedIn(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(PreferencesUtils.KEY_LOGGED_IN);
    }
}
