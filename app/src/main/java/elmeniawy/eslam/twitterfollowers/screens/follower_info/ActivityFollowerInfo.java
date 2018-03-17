package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.helpers.BaseActivity;
import elmeniawy.eslam.twitterfollowers.helpers.CustomAppBarLayout;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;
import timber.log.Timber;

public class ActivityFollowerInfo extends BaseActivity implements FollowerInfoMVP.View {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Inject
    FollowerInfoMVP.Presenter presenter;

    //
    // Bind views.
    //

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_bar)
    CustomAppBarLayout background;

    /*@BindView(R.id.fab)
    FloatingActionButton profile;*/

    @BindView(R.id.iv_profile)
    CircleImageView profile;

    @BindView(R.id.pb_loading)
    ProgressBar loading;

    @BindView(R.id.rv_tweets)
    RecyclerView recyclerTweets;

    @BindView(R.id.tv_error)
    AppCompatTextView tvError;

    //
    // Bind strings.
    //

    @BindString(R.string.no_internet)
    String noInternet;

    @BindString(R.string.error_get_tweets)
    String errorGetTweets;

    @BindString(R.string.no_tweets)
    String noTweets;

    //
    // Bind dimens.
    //

    @BindDimen(R.dimen.follower_list_image_width)
    int profileWidth;

    private TweetsListAdapter tweetsListAdapter;
    private List<TweetViewModel> tweetsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_info);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);

        //
        // Initialize butter knife.
        //

        ButterKnife.bind(this);

        //
        // Set toolbar.
        //

        setSupportActionBar(toolbar);

        //
        // Set timber tag.
        //

        Timber.tag(ActivityFollowerInfo.class.getSimpleName());

        //
        // Set follower.
        //

        if (getIntent() != null) {
            Timber.i("Follower: %s.", getIntent()
                    .getParcelableExtra(ConstantUtils.INTENT_KEY_FOLLOWER));

            presenter.setFollower(getIntent()
                    .getParcelableExtra(ConstantUtils.INTENT_KEY_FOLLOWER));
        }

        //
        // Set recycler view animator.
        //

        recyclerTweets.setItemAnimator(new DefaultItemAnimator());

        //
        // Set recycler view layout manager.
        //

        recyclerTweets.setLayoutManager(new LinearLayoutManager(this));

        //
        // Set recycler view adapter.
        //

        tweetsListAdapter = new TweetsListAdapter(tweetsList);
        recyclerTweets.setAdapter(tweetsListAdapter);

        //
        // Add background offset change listener.
        //

        background.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
                presenter.layoutScrolled(verticalOffset, appBarLayout.getTotalScrollRange()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.setTitle();
        presenter.loadImages();
        presenter.loadTweets();
    }

    @Override
    public void onBackPressed() {
        Timber.i("onBackPressed");
        presenter.backClicked();
    }

    @Override
    public MySharedPreferences getSharedPreferences() {
        return mySharedPreferences;
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        tvError.setVisibility(View.GONE);
    }

    @Override
    public void setInternetError() {
        tvError.setText(noInternet);
    }

    @Override
    public void setGetError() {
        tvError.setText(errorGetTweets);
    }

    @Override
    public void setNoTweets() {
        tvError.setText(noTweets);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void loadProfileImage(String url) {
        Picasso
                .get()
                .load(url)
                .placeholder(R.drawable.ic_twitter_logo)
                .error(R.drawable.ic_twitter_logo)
                .into(profile);
    }

    @Override
    public void loadBannerImage(String url) {
        Picasso
                .get()
                .load(url)
                .into(background);
    }

    @Override
    public void showProfile() {
        profile.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProfile() {
        profile.setVisibility(View.GONE);
    }

    @Override
    public void showTweets() {
        recyclerTweets.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTweets() {
        recyclerTweets.setVisibility(View.GONE);
    }

    @Override
    public void clearTweets() {
        tweetsList.clear();
        tweetsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void addTweets(List<TweetViewModel> tweets) {
        tweetsList.addAll(tweets);
        tweetsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void openImage(String image) {

    }

    @Override
    public void closeActivity() {
        ActivityFollowerInfo.this.finish();
    }

    @Override
    public void addCloseAnimation() {
        overridePendingTransition(R.anim.fadein, R.anim.bottom_down);
    }

    @OnClick(R.id.iv_profile)
    void profileClicked() {
        Timber.i("profileClicked");
        presenter.profileClicked();
    }

    @OnClick(R.id.app_bar)
    void backgroundClicked() {
        Timber.i("backgroundClicked");
        presenter.bannerClicked();
    }
}
