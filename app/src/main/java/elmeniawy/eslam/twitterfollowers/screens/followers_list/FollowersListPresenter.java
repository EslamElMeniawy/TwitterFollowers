package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.api.model.User;
import elmeniawy.eslam.twitterfollowers.storage.database.entities.FollowerEntity;
import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    private boolean mLoadingItems = true;
    private long cursor = -1;
    private Disposable disposable = null;

    @Nullable
    private FollowersListMVP.View view;

    private FollowersListMVP.Model model;

    FollowersListPresenter(FollowersListMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(@Nullable FollowersListMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadFollowers() {
        cursor = -1;
        getFollowers();
    }

    @Override
    public void refreshFollowers() {
        cursor = -1;
        getFollowers();
    }

    @Override
    public void followerClicked(FollowerViewModel follower) {
        if (view != null) {
            view.openFollowerInfo(follower);
        }
    }

    @Override
    public void langChangeClicked() {
        if (view != null) {
            String[] languageList = {view.getArabicString(), view.getEnglishString()};
            Timber.i("Language list: %s.", Arrays.toString(languageList));
            String currentLang = model.getLang(view.getSharedPreferences());
            Timber.i("Current user language: %s.", currentLang);
            int checkedItem = 1;

            switch (currentLang) {
                case ConstantUtils.LANG_AR:
                    checkedItem = 0;
                    break;
            }

            Timber.i("Checked item position: %d.", checkedItem);
            view.showLangDialog(languageList, checkedItem);
        }
    }

    @Override
    public void langSelected(int previousItem, int newItem) {
        Timber.i("Previous item position: %d.\nNew item position: %d.",
                previousItem, newItem);

        if (view != null && previousItem != newItem) {
            switch (newItem) {
                case 0:
                    model.saveLang(view.getSharedPreferences(), ConstantUtils.LANG_AR);
                    break;
                case 1:
                    model.saveLang(view.getSharedPreferences(), ConstantUtils.LANG_EN);
                    break;
            }

            view.reCreateActivity();
        }
    }

    @Override
    public void recyclerScrolled(int mOnScreenItems, int mTotalItemsInList, int mFirstVisibleItem) {
        if (view != null) {
            int mVisibleThreshold = 1;

            if (!mLoadingItems &&
                    (mTotalItemsInList - mOnScreenItems) <=
                            (mFirstVisibleItem + mVisibleThreshold)) {
                getFollowers();
            }
        }
    }

    @Override
    public void rxUnsubscribe() {
        model.rxUnsubscribe();

        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void getFollowers() {
        if (view != null) {
            mLoadingItems = true;
            view.showLoading();

            Call<FollowersResponse> call = model
                    .getFollowers(model.getUserId(view.getSharedPreferences()), cursor);

            call.enqueue(new Callback<FollowersResponse>() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onResponse(@NonNull Call<FollowersResponse> call,
                                       @NonNull Response<FollowersResponse> response) {
                    Timber.i("Response: %s.", response.toString());
                    mLoadingItems = false;

                    if (response.body() != null) {
                        Timber.i("Response body: %s.", gson.toJson(response.body()));

                        if (response.body().getUsers() != null &&
                                response.body().getUsers().size() > 0) {
                            handleResults(response.body().getUsers(), cursor);
                        } else {
                            if (cursor == -1) {
                                view.setNoFollowers();
                                view.hideList();
                                view.showError();
                            }

                            view.hideLoading();
                        }

                        if (response.body().getNextCursor() != null) {
                            cursor = response.body().getNextCursor();
                        }
                    } else {
                        showGetError(cursor);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<FollowersResponse> call, @NonNull Throwable t) {
                    Timber.e(t);
                    mLoadingItems = false;
                    showError(t, cursor);
                }
            });
        }
    }

    private void showError(Throwable t, long oldCursor) {
        if (view != null) {
            if (t instanceof ConnectException
                    || t instanceof SocketTimeoutException
                    || t instanceof UnknownHostException
                    || t instanceof TimeoutException) {
                showInternetError(oldCursor);
            } else {
                showGetError(oldCursor);
            }
        }
    }

    private void showInternetError(long oldCursor) {
        if (view != null) {
            if (oldCursor == -1 && view.getFollowersListSize() <= 0) {
                disposable = model
                        .getFollowersOffline(view.getDatabase())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(followerEntityList -> {
                            if (followerEntityList != null && followerEntityList.size() > 0) {
                                //
                                // Map entities to view model.
                                //

                                List<FollowerViewModel> followerViewModels = new ArrayList<>();

                                for (FollowerEntity follower :
                                        followerEntityList) {
                                    Timber.i("Converting: %s.", gson.toJson(follower));
                                    FollowerViewModel followerViewModel = new FollowerViewModel();
                                    followerViewModel.setId(follower.getId());
                                    followerViewModel.setName(follower.getName());
                                    followerViewModel.setDescription(follower.getDescription());
                                    followerViewModel.setProfileBanner(follower.getProfileBanner());
                                    followerViewModel.setProfileImage(follower.getProfileImage());
                                    followerViewModels.add(followerViewModel);
                                }

                                //
                                // Set results to view.
                                //

                                view.clearFollowers();
                                view.addFollowers(followerViewModels);
                                view.hideLoading();

                                //
                                // Show internet error through toast.
                                //

                                showInternetErrorToast();
                            } else {
                                //
                                // Show internet error through text view.
                                //
                                showInternetErrorTv();
                            }
                        });


            } else {
                //
                // Show internet error through toast.
                //

                showInternetErrorToast();
            }
        }
    }

    private void showInternetErrorTv() {
        if (view != null) {
            view.setInternetError();
            view.hideList();
            view.showError();
            view.hideLoading();
        }
    }

    private void showInternetErrorToast() {
        if (view != null) {
            view.showInternetError();
            view.hideLoading();
        }
    }

    private void showGetError(long oldCursor) {
        if (view != null) {
            if (oldCursor == -1 && view.getFollowersListSize() <= 0) {
                //
                // Show get error through text view.
                //

                view.setGetError();
                view.hideList();
                view.showError();
            } else {
                //
                // Show get error through toast.
                //

                view.showGetError();
            }

            view.hideLoading();
        }
    }

    private void handleResults(List<User> followers, long oldCursor) {
        if (view != null) {
            //
            // Map followers to view models and entities.
            //

            List<FollowerViewModel> followerViewModels = new ArrayList<>();
            List<FollowerEntity> followerEntities = new ArrayList<>();

            for (User follower :
                    followers) {
                Timber.i("Converting: %s.", gson.toJson(follower));
                FollowerViewModel followerViewModel = new FollowerViewModel();
                FollowerEntity followerEntity = new FollowerEntity();
                followerViewModel.setId(follower.getId());
                followerEntity.setId(follower.getId());
                followerViewModel.setName(follower.getName());
                followerEntity.setName(follower.getName());
                followerViewModel.setDescription(follower.getDescription());
                followerEntity.setDescription(follower.getDescription());
                followerViewModel.setProfileBanner(follower.getProfileBanner());
                followerEntity.setProfileBanner(follower.getProfileBanner());

                followerViewModel.setProfileImage(follower.getProfileImageUrlHttps()
                        .replace("_normal", "_bigger"));

                followerEntity.setProfileImage(follower.getProfileImageUrlHttps()
                        .replace("_normal", "_bigger"));

                followerViewModels.add(followerViewModel);
                followerEntities.add(followerEntity);
            }

            //
            // If first page then clear view followers and save data to database.
            //

            if (oldCursor == -1) {
                Timber.i("First page.\nClearing view followers.\nSaving data to database.");
                view.clearFollowers();
                model.saveFollowers(view.getDatabase(), followerEntities);
            }

            //
            // Set results to view.
            //

            view.addFollowers(followerViewModels);
            view.hideLoading();
        }
    }
}
