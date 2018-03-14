package elmeniawy.eslam.twitterfollowers.screens.login;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import timber.log.Timber;

/**
 * LoginPresenter
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class LoginPresenter implements LoginMVP.Presenter {
    @Nullable
    private LoginMVP.View view;

    private LoginMVP.Model model;

    LoginPresenter(LoginMVP.Model model) {
        this.model = model;
        Timber.tag(LoginPresenter.class.getSimpleName());
    }

    @Override
    public void setView(@Nullable LoginMVP.View view) {
        this.view = view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (view != null) {
            view.setLoginButtonOnActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void loginSuccess(Result<TwitterSession> result) {
        Timber.i("Twitter result: %s.", result);
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        Timber.i("Twitter session: %s.", session);
        TwitterAuthToken authToken = session.getAuthToken();
        Timber.i("Twitter auth token: %s.", authToken);

        if (view != null) {
            //
            // Save user data.
            //

            model.saveToken(view.getSharedPreferences(), authToken.token);
            model.saveSecret(view.getSharedPreferences(), authToken.secret);
            model.saveUserName(view.getSharedPreferences(), session.getUserName());
            model.saveUserId(view.getSharedPreferences(), session.getUserId());
            model.saveLoggedIn(view.getSharedPreferences());

            //
            // Open followers list.
            //

            view.openFollowersList();
            view.addOpenAnimation();
            view.closeActivity();
        }
    }

    @Override
    public void loginFail(TwitterException exception) {
        Timber.e(exception);

        if (view != null) {
            view.showLoginError();
        }
    }
}
