package elmeniawy.eslam.twitterfollowers.screens.splash;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * SplashMVP
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public interface SplashMVP {
    interface View {
        MySharedPreferences getSharedPreferences();

        void openWelcome();

        void openLogin();

        void openFollowersList();

        void addOpenAnimation();

        void closeActivity();
    }

    interface Presenter {
        void setView(SplashMVP.View view);

        void startWait();

        void rxUnsubscribe();
    }

    interface Model {
        boolean getRunBefore(MySharedPreferences sharedPreferences);

        boolean getLoggedIn(MySharedPreferences sharedPreferences);
    }
}
