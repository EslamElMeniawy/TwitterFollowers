package elmeniawy.eslam.twitterfollowers.screens.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.screens.login.ActivityLogin;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;

public class ActivityWelcome extends AppCompatActivity implements WelcomeMVP.View {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Inject
    WelcomeMVP.Presenter presenter;

    @BindView(R.id.rb_ar)
    AppCompatRadioButton rbAr;

    @BindView(R.id.rb_en)
    AppCompatRadioButton rbEn;

    @BindView(R.id.tv_error)
    AppCompatTextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);

        //
        // Initialize butter knife.
        //

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public MySharedPreferences getSharedPreferences() {
        return mySharedPreferences;
    }

    @Override
    public boolean getArChecked() {
        return rbAr.isChecked();
    }

    @Override
    public boolean getEnChecked() {
        return rbEn.isChecked();
    }

    @Override
    public void showSelectionError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSelectionError() {
        tvError.setVisibility(View.GONE);
    }

    @Override
    public void openLogin() {
        startActivity(new Intent(ActivityWelcome.this, ActivityLogin.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void addOpenAnimation() {
        overridePendingTransition(R.anim.bottom_up, R.anim.fadeout);
    }

    @Override
    public void closeActivity() {
        ActivityWelcome.this.finish();
    }

    @OnCheckedChanged({R.id.rb_ar, R.id.rb_en})
    void onLangSelected(boolean checked) {
        presenter.langCheckChanged(checked);
    }

    @OnClick(R.id.bt_next)
    void onNextClicked() {
        presenter.nextClicked();
    }
}
