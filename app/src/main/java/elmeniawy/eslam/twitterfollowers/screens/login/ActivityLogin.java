package elmeniawy.eslam.twitterfollowers.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.helpers.BaseActivity;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.screens.followers_list.ActivityFollowersList;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

public class ActivityLogin extends BaseActivity implements LoginMVP.View {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Inject
    LoginMVP.Presenter presenter;

    @BindView(R.id.login_button)
    TwitterLoginButton btLogin;

    @BindView(R.id.tv_error)
    AppCompatTextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);

        //
        // Initialize butter knife.
        //

        ButterKnife.bind(this);

        //
        // Add login button callbacks.
        //

        btLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                presenter.loginSuccess(result);
            }

            @Override
            public void failure(TwitterException exception) {
                presenter.loginFail(exception);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public MySharedPreferences getSharedPreferences() {
        return mySharedPreferences;
    }

    @Override
    public void showLoginError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLoginButtonOnActivityResult(int requestCode, int resultCode, Intent data) {
        btLogin.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void openFollowersList() {
        startActivity(new Intent(ActivityLogin.this, ActivityFollowersList.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void addOpenAnimation() {
        overridePendingTransition(R.anim.bottom_up, R.anim.fadeout);
    }

    @Override
    public void closeActivity() {
        ActivityLogin.this.finish();
    }
}
