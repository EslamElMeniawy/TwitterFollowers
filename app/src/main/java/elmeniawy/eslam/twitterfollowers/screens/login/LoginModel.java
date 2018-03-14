package elmeniawy.eslam.twitterfollowers.screens.login;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * LoginModel
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class LoginModel implements LoginMVP.Model {
    private Repository repository;

    LoginModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void saveLoggedIn(MySharedPreferences sharedPreferences) {
        repository.saveLoggedIn(sharedPreferences);
    }

    @Override
    public void saveToken(MySharedPreferences sharedPreferences, String token) {
        repository.saveToken(sharedPreferences, token);
    }

    @Override
    public void saveSecret(MySharedPreferences sharedPreferences, String secret) {
        repository.saveSecret(sharedPreferences, secret);
    }

    @Override
    public void saveUserName(MySharedPreferences sharedPreferences, String userName) {
        repository.saveUserName(sharedPreferences, userName);
    }

    @Override
    public void saveUserId(MySharedPreferences sharedPreferences, long userId) {
        repository.saveUserId(sharedPreferences, userId);
    }
}
