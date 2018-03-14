package elmeniawy.eslam.twitterfollowers.screens.welcome;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

/**
 * WelcomeMVP
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public interface WelcomeMVP {
    interface View {
        MySharedPreferences getSharedPreferences();

        boolean getArChecked();

        boolean getEnChecked();

        void showSelectionError();

        void hideSelectionError();

        void openLogin();

        void addOpenAnimation();

        void closeActivity();
    }

    interface Presenter {
        void setView(WelcomeMVP.View view);

        void langCheckChanged(boolean checked);

        void nextClicked();
    }

    interface Model {
        void saveLanguage(MySharedPreferences sharedPreferences, String lang);

        void saveRunBefore(MySharedPreferences sharedPreferences);
    }
}
