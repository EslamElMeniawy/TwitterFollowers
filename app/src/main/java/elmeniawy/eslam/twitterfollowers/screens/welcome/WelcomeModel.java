package elmeniawy.eslam.twitterfollowers.screens.welcome;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * WelcomeModel
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class WelcomeModel implements WelcomeMVP.Model {
    private Repository repository;

    WelcomeModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void saveLanguage(MySharedPreferences sharedPreferences, String lang) {
        repository.saveLanguage(sharedPreferences, lang);
    }

    @Override
    public void saveRunBefore(MySharedPreferences sharedPreferences) {
        repository.saveRunBefore(sharedPreferences);
    }
}
