package elmeniawy.eslam.twitterfollowers.helpers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;
import timber.log.Timber;

/**
 * BaseActivity
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);

        //
        // Set timber tag.
        //

        Timber.tag(BaseActivity.class.getSimpleName());

        //
        // Set app language.
        //

        String lang = mySharedPreferences.getString(PreferencesUtils.KEY_LANG);
        Timber.i("Call set local with lang: %s.", lang);
        LocalHelper.setLocale(this, lang);
    }
}
