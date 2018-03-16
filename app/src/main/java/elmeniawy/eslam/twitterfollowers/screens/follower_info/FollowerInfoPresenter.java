package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import android.support.annotation.Nullable;

import elmeniawy.eslam.twitterfollowers.screens.followers_list.FollowerViewModel;
import timber.log.Timber;

/**
 * FollowerInfoPresenter
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public class FollowerInfoPresenter implements FollowerInfoMVP.Presenter {
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
}
