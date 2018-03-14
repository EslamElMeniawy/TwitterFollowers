package elmeniawy.eslam.twitterfollowers.screens.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.screens.followers_list.ActivityFollowersList;
import elmeniawy.eslam.twitterfollowers.screens.login.ActivityLogin;
import elmeniawy.eslam.twitterfollowers.screens.welcome.ActivityWelcome;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

public class ActivitySplash extends AppCompatActivity implements SplashMVP.View {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Inject
    SplashMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.startWait();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.rxUnsubscribe();
    }

    @Override
    public MySharedPreferences getSharedPreferences() {
        return mySharedPreferences;
    }

    @Override
    public void openWelcome() {
        startActivity(new Intent(ActivitySplash.this, ActivityWelcome.class));
        overridePendingTransition(R.anim.bottom_up, R.anim.fadeout);
    }

    @Override
    public void openLogin() {
        startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
        overridePendingTransition(R.anim.bottom_up, R.anim.fadeout);
    }

    @Override
    public void openFollowersList() {
        startActivity(new Intent(ActivitySplash.this, ActivityFollowersList.class));
        overridePendingTransition(R.anim.bottom_up, R.anim.fadeout);
    }

    @Override
    public void closeActivity() {
        ActivitySplash.this.finish();
    }
}
