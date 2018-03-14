package elmeniawy.eslam.twitterfollowers.screens.splash;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * SplashModel
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class SplashModel implements SplashMVP.Model {
    private Repository repository;

    SplashModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean getRunBefore(MySharedPreferences sharedPreferences) {
        return repository.getRunBefore(sharedPreferences);
    }

    @Override
    public boolean getLoggedIn(MySharedPreferences sharedPreferences) {
        return repository.getLoggedIn(sharedPreferences);
    }
}
