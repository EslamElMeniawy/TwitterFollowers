package elmeniawy.eslam.twitterfollowers.screens.welcome;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * Repository
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public interface Repository {
    void saveLanguage(MySharedPreferences sharedPreferences, String lang);

    void saveRunBefore(MySharedPreferences sharedPreferences);
}
