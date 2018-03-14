package elmeniawy.eslam.twitterfollowers.screens.login;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * Repository
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public interface Repository {
    void saveLoggedIn(MySharedPreferences sharedPreferences);

    void saveToken(MySharedPreferences sharedPreferences, String token);

    void saveSecret(MySharedPreferences sharedPreferences, String secret);

    void saveUserName(MySharedPreferences sharedPreferences, String userName);

    void saveUserId(MySharedPreferences sharedPreferences, long userId);
}
