package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.api.model.User;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import retrofit2.Call;

/**
 * FollowersListMVP
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public interface FollowersListMVP {
    interface View {
        MySharedPreferences getSharedPreferences();

        void showLoading();

        void hideLoading();

        void showList();

        void hideList();

        void setInternetError();

        void setGetError();

        void setNoFollowers();

        void showError();

        void hideError();

        void clearFollowers();

        void addFollowers(List<FollowerViewModel> followers);

        void openFollowerInfo(FollowerViewModel follower);

        void addOpenAnimation();
    }

    interface Presenter {
        void setView(FollowersListMVP.View view);

        void loadFollowers();

        void refreshFollowers();

        void followerClicked(FollowerViewModel follower);
    }

    interface Model {
        String getLang(MySharedPreferences sharedPreferences);

        void saveLang(MySharedPreferences sharedPreferences, String lang);

        long getUserId(MySharedPreferences sharedPreferences);

        Call<FollowersResponse> getFollowers(long userId);

        void saveFollowers(List<User> followers);
    }
}