package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.screens.followers_list.FollowerViewModel;
import retrofit2.Call;

/**
 * FollowerInfoMVP
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public interface FollowerInfoMVP {
    interface View {
        void hideLoading();

        void showError();

        void setInternetError();

        void setGetError();

        void setNoTweets();

        void setTitle(String title);

        void loadProfileImage(String url);

        void loadBannerImage(String url);

        void showProfile();

        void hideProfile();

        void hideTweets();

        void clearTweets();

        void addTweets(List<TweetViewModel> tweets);

        void openImage(String image);

        void addOpenAnimation();

        void closeActivity();

        void addCloseAnimation();
    }

    interface Presenter {
        void setFollower(FollowerViewModel follower);

        void setView(FollowerInfoMVP.View view);

        void layoutScrolled(int offset, int range);

        void setTitle();

        void loadImages();

        void loadTweets();

        void profileClicked();

        void bannerClicked();

        void backClicked();
    }

    interface Model {
        Call<List<Tweet>> getTweets(long userId);
    }
}
