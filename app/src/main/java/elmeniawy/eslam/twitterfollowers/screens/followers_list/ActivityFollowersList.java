package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.helpers.BaseActivity;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.screens.follower_info.ActivityFollowerInfo;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;
import timber.log.Timber;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ActivityFollowersList extends BaseActivity implements FollowersListMVP.View {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Inject
    FollowersListMVP.Presenter presenter;

    //
    // Bind views.
    //

    @BindView(R.id.srl_followers)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_followers)
    RecyclerView recyclerFollowers;

    @BindView(R.id.tv_error)
    AppCompatTextView tvError;

    //
    // Bind strings.
    //

    @BindString(R.string.app_name)
    String title;

    @BindString(R.string.no_internet)
    String noInternet;

    @BindString(R.string.error_get_followers)
    String errorGetFollowers;

    @BindString(R.string.no_followers)
    String noFollowers;

    @BindString(R.string.ar)
    String arabic;

    @BindString(R.string.en)
    String english;

    private GridLayoutManager gridLayoutManager;
    private FollowersListAdapter followersListAdapter;
    private List<FollowerViewModel> followersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_list);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);

        //
        // Initialize butter knife.
        //

        ButterKnife.bind(this);

        //
        // Set activity title.
        //

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        //
        // Set swipe refresh layout refresh listener.
        //

        swipeRefreshLayout.setOnRefreshListener(() -> {
            Timber.i("OnRefreshListener");
            presenter.refreshFollowers();
        });

        //
        // Set recycler view animator.
        //

        recyclerFollowers.setItemAnimator(new DefaultItemAnimator());

        //
        // Set recycler view layout manager.
        //

        int spanCount = 1;

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            spanCount = 2;
        }

        gridLayoutManager = new GridLayoutManager(ActivityFollowersList.this, spanCount);
        recyclerFollowers.setLayoutManager(gridLayoutManager);

        //
        // Set recycler view adapter.
        //

        followersListAdapter = new FollowersListAdapter(followersList, ActivityFollowersList.this);
        recyclerFollowers.setAdapter(followersListAdapter);

        //
        // Set recycler view scroll detector.
        //

        recyclerFollowers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Timber.i("Recycler view onScrolled.");

                presenter.recyclerScrolled(recyclerView.getChildCount(),
                        gridLayoutManager.getItemCount(),
                        gridLayoutManager.findFirstVisibleItemPosition());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadFollowers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_followers_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_lang) {
            presenter.langChangeClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public MySharedPreferences getSharedPreferences() {
        return mySharedPreferences;
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideList() {
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void setInternetError() {
        tvError.setText(noInternet);
    }

    @Override
    public void setGetError() {
        tvError.setText(errorGetFollowers);
    }

    @Override
    public void setNoFollowers() {
        tvError.setText(noFollowers);
    }

    @Override
    public void showError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInternetError() {
        showToast(noInternet);
    }

    @Override
    public void showGetError() {
        showToast(errorGetFollowers);
    }

    @Override
    public int getFollowersListSize() {
        return followersList.size();
    }

    @Override
    public void clearFollowers() {
        followersList.clear();
        followersListAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFollowers(List<FollowerViewModel> followers) {
        followersList.addAll(followers);
        followersListAdapter.notifyDataSetChanged();
    }

    @Override
    public void openFollowerInfo(FollowerViewModel follower) {
        startActivity(new Intent(ActivityFollowersList.this,
                ActivityFollowerInfo.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra(ConstantUtils.INTENT_KEY_FOLLOWER, follower));
    }

    @Override
    public void addOpenAnimation() {
        overridePendingTransition(R.anim.bottom_up, R.anim.fadeout);
    }

    @Override
    public String getArabicString() {
        return arabic;
    }

    @Override
    public String getEnglishString() {
        return english;
    }

    @Override
    public void showLangDialog(String[] languageList, int checkedItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setTitle(R.string.change_lang)
                .setSingleChoiceItems(languageList, checkedItem, (dialog, which) -> {
                    presenter.langSelected(checkedItem, which);
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    @Override
    public void reCreateActivity() {
        recreate();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
