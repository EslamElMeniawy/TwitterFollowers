package elmeniawy.eslam.twitterfollowers.screens.login;

import android.content.Intent;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * LoginMVP
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public interface LoginMVP {
    interface View {
        MySharedPreferences getSharedPreferences();

        void showLoginError();

        void setLoginButtonOnActivityResult(int requestCode, int resultCode, Intent data);

        void openFollowersList();

        void addOpenAnimation();

        void closeActivity();
    }

    interface Presenter {
        void setView(LoginMVP.View view);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void loginSuccess(Result<TwitterSession> result);

        void loginFail(TwitterException exception);
    }

    interface Model {
        void saveLoggedIn(MySharedPreferences sharedPreferences);

        void saveToken(MySharedPreferences sharedPreferences, String token);

        void saveSecret(MySharedPreferences sharedPreferences, String secret);

        void saveUserName(MySharedPreferences sharedPreferences, String userName);

        void saveUserId(MySharedPreferences sharedPreferences, long userId);
    }
}
