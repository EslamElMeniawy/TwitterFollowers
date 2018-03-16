package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.twitter.sdk.android.core.models.Tweet;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import elmeniawy.eslam.twitterfollowers.screens.followers_list.FollowerViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * FollowerInfoPresenter
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public class FollowerInfoPresenter implements FollowerInfoMVP.Presenter {
    private Gson gson = new Gson();

    @Nullable
    private FollowerViewModel follower;

    @Nullable
    private FollowerInfoMVP.View view;

    private FollowerInfoMVP.Model model;

    FollowerInfoPresenter(FollowerInfoMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setFollower(@Nullable FollowerViewModel follower) {
        this.follower = follower;
        Timber.i("Follower: %s.", follower);
    }

    @Override
    public void setView(@Nullable FollowerInfoMVP.View view) {
        this.view = view;
    }

    @Override
    public void layoutScrolled(int offset, int range) {
        if (view != null) {
            if (((range - Math.abs(offset)) / (double) range) <= 0.15) {
                view.hideProfile();
            } else {
                view.showProfile();
            }
        }
    }

    @Override
    public void setTitle() {
        if (view != null && follower != null) {
            view.setTitle(follower.getName());
        }
    }

    @Override
    public void loadImages() {
        if (view != null && follower != null) {
            Timber.i("Load profile image at: %s.", follower.getProfileImage());
            view.loadProfileImage(follower.getProfileImage());
            Timber.i("Load profile banner at: %s/600x200.", follower.getProfileBanner());
            view.loadBannerImage(follower.getProfileBanner() + "/600x200");
        }
    }

    @Override
    public void loadTweets() {
        if (view != null && follower != null) {
            model
                    .getTweets(follower.getId())
                    .enqueue(new Callback<List<Tweet>>() {
                        @SuppressWarnings("ConstantConditions")
                        @Override
                        public void onResponse(@NonNull Call<List<Tweet>> call,
                                               @NonNull Response<List<Tweet>> response) {
                            Timber.i("Response: %s.", response.toString());

                            if (response.body() != null) {
                                Timber.i("Response body: %s.",
                                        gson.toJson(response.body()));

                                if (response.body() != null &&
                                        response.body().size() > 0) {
                                    view.clearTweets();
                                    handleResults(response.body());
                                } else {
                                    view.setNoTweets();
                                    view.hideLoading();
                                    view.hideTweets();
                                    view.showError();
                                }
                            } else {
                                showGetError();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Tweet>> call,
                                              @NonNull Throwable t) {
                            Timber.e(t);

                            if (t instanceof ConnectException
                                    || t instanceof SocketTimeoutException
                                    || t instanceof UnknownHostException
                                    || t instanceof TimeoutException) {
                                view.setInternetError();
                                view.hideLoading();
                                view.hideTweets();
                                view.showError();
                            } else {
                                showGetError();
                            }
                        }
                    });
        }
    }

    @Override
    public void profileClicked() {
        if (view != null && follower != null) {
            view.openImage(follower.getProfileImage().replace("_bigger", ""));
        }
    }

    @Override
    public void bannerClicked() {
        if (view != null && follower != null) {
            view.openImage(follower.getProfileBanner());
        }
    }

    @Override
    public void backClicked() {
        if (view != null) {
            view.closeActivity();
            view.addCloseAnimation();
        }
    }

    private void showGetError() {
        if (view != null) {
            view.setGetError();
            view.hideLoading();
            view.hideTweets();
            view.showError();
        }
    }

    private void handleResults(List<Tweet> tweets) {
        if (view != null) {
            //
            // Map tweets to view model
            //

            List<TweetViewModel> tweetViewModels = new ArrayList<>();

            for (Tweet tweet :
                    tweets) {
                Timber.i("Converting: %s.", gson.toJson(tweet));
                TweetViewModel tweetViewModel = new TweetViewModel();
                tweetViewModel.setTweet(tweet.text);
                tweetViewModel.setRetweet(tweet.retweetCount);
                tweetViewModel.setFavorite(tweet.favoriteCount);
                tweetViewModels.add(tweetViewModel);
            }

            view.addTweets(tweetViewModels);
            view.hideLoading();
        }
    }
}
