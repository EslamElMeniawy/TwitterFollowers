package elmeniawy.eslam.twitterfollowers.screens.login;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;

/**
 * LoginRepository
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class LoginRepository implements Repository {
    @Override
    public void saveLoggedIn(MySharedPreferences sharedPreferences) {
        sharedPreferences.putBoolean(PreferencesUtils.KEY_LOGGED_IN, true);
    }

    @Override
    public void saveToken(MySharedPreferences sharedPreferences, String token) {
        sharedPreferences.putString(PreferencesUtils.KEY_TOKEN, token);
    }

    @Override
    public void saveSecret(MySharedPreferences sharedPreferences, String secret) {
        sharedPreferences.putString(PreferencesUtils.KEY_SECRET, secret);
    }

    @Override
    public void saveUserName(MySharedPreferences sharedPreferences, String userName) {
        sharedPreferences.putString(PreferencesUtils.KEY_USER_NAME, userName);
    }

    @Override
    public void saveUserId(MySharedPreferences sharedPreferences, long userId) {
        sharedPreferences.putLong(PreferencesUtils.KEY_USER_ID, userId);
    }
}
