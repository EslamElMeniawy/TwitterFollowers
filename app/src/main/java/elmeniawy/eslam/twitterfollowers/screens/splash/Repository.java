package elmeniawy.eslam.twitterfollowers.screens.splash;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * Repository
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public interface Repository {
    boolean getRunBefore(MySharedPreferences sharedPreferences);

    boolean getLoggedIn(MySharedPreferences sharedPreferences);
}
