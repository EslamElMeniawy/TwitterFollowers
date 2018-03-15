package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import timber.log.Timber;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ActivityFollowersList extends AppCompatActivity implements FollowersListMVP.View {
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

    @BindString(R.string.no_internet)
    String noInternet;

    @BindString(R.string.error_get_followers)
    String errorGetFollowers;

    @BindString(R.string.no_followers)
    String noFollowers;

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
    public void showList() {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
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
    public void hideError() {
        tvError.setVisibility(View.GONE);
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
}
