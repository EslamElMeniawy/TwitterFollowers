package elmeniawy.eslam.twitterfollowers.screens.welcome;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;

/**
 * WelcomeRepository
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class WelcomeRepository implements Repository {
    @Override
    public void saveLanguage(MySharedPreferences sharedPreferences, String lang) {
        sharedPreferences.putString(PreferencesUtils.KEY_LANG, lang);
    }

    @Override
    public void saveRunBefore(MySharedPreferences sharedPreferences) {
        sharedPreferences.putBoolean(PreferencesUtils.KEY_RUN_BEFORE, true);
    }
}
