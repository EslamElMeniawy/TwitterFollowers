package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.api.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * FollowersListPresenter
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public class FollowersListPresenter implements FollowersListMVP.Presenter {
    private Gson gson = new Gson();

    @Nullable
    private FollowersListMVP.View view;

    private FollowersListMVP.Model model;

    FollowersListPresenter(FollowersListMVP.Model model) {
        this.model = model;
        Timber.tag(FollowersListPresenter.class.getSimpleName());
    }

    @Override
    public void setView(@Nullable FollowersListMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadFollowers() {
        loadFirstTime();
    }

    @Override
    public void refreshFollowers() {
        loadFirstTime();
    }

    private void loadFirstTime() {
        if (view != null) {
            view.showLoading();

            Call<FollowersResponse> call = model
                    .getFollowers(model.getUserId(view.getSharedPreferences()));

            call.enqueue(new Callback<FollowersResponse>() {
                @Override
                public void onResponse(@NonNull Call<FollowersResponse> call,
                                       @NonNull Response<FollowersResponse> response) {
                    Timber.i("Response: %s.", response.toString());

                    if (response.body() != null) {
                        Timber.i("Response body: %s.", gson.toJson(response.body()));

                        if (response.body().getUsers() != null &&
                                response.body().getUsers().size() > 0) {
                            view.clearFollowers();
                            handleResults(response.body().getUsers());
                        } else {
                            view.setNoFollowers();
                            view.hideLoading();
                            view.hideList();
                            view.showError();
                        }
                    } else {
                        showGetError();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<FollowersResponse> call, @NonNull Throwable t) {
                    Timber.e(t);

                    if (t instanceof ConnectException
                            || t instanceof SocketTimeoutException
                            || t instanceof UnknownHostException
                            || t instanceof TimeoutException) {
                        view.setInternetError();
                        view.hideLoading();
                        view.hideList();
                        view.showError();
                    } else {
                        showGetError();
                    }
                }
            });
        }
    }

    private void showGetError() {
        if (view != null) {
            view.setGetError();
            view.hideLoading();
            view.hideList();
            view.showError();
        }
    }

    private void handleResults(List<User> followers) {
        if (view != null) {
            //
            // Map followers to view model
            //

            List<FollowerViewModel> followerViewModels = new ArrayList<>();

            for (User follower :
                    followers) {
                Timber.i("Converting: %s.", gson.toJson(follower));
                FollowerViewModel followerViewModel = new FollowerViewModel();
                followerViewModel.setId(follower.getId());
                followerViewModel.setName(follower.getName());
                followerViewModel.setDescription(follower.getDescription());

                followerViewModel
                        .setProfileBackgroundImage(follower.getProfileBackgroundImageUrlHttps());

                followerViewModel.setProfileImage(follower.getProfileImageUrlHttps());
                followerViewModels.add(followerViewModel);
            }

            view.addFollowers(followerViewModels);
            view.hideLoading();
        }
    }
}
