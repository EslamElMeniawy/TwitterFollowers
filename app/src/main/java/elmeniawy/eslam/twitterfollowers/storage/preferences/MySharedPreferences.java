package elmeniawy.eslam.twitterfollowers.storage.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * MySharedPreferences
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class MySharedPreferences {
    private SharedPreferences mSharedPreferences;

    @Inject
    public MySharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putBoolean(String key, boolean data) {
        mSharedPreferences.edit().putBoolean(key, data).apply();
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void putString(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }
}
