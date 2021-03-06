package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.storage.database.ApplicationDatabase;
import elmeniawy.eslam.twitterfollowers.storage.database.entities.FollowerEntity;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import io.reactivex.Maybe;
import retrofit2.Call;

/**
 * FollowersListMVP
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public interface FollowersListMVP {
    interface View {
        MySharedPreferences getSharedPreferences();

        ApplicationDatabase getDatabase();

        void showLoading();

        void hideLoading();

        void hideList();

        void setInternetError();

        void setGetError();

        void setNoFollowers();

        void showError();

        void showInternetError();

        void showGetError();

        int getFollowersListSize();

        void clearFollowers();

        void addFollowers(List<FollowerViewModel> followers);

        void openFollowerInfo(FollowerViewModel follower);

        void addOpenAnimation();

        String getArabicString();

        String getEnglishString();

        void showLangDialog(String[] languageList, int checkedItem);

        void reCreateActivity();
    }

    interface Presenter {
        void setView(FollowersListMVP.View view);

        void loadFollowers();

        void refreshFollowers();

        void followerClicked(FollowerViewModel follower);

        void langChangeClicked();

        void langSelected(int previousItem, int newItem);

        void recyclerScrolled(int mOnScreenItems, int mTotalItemsInList, int mFirstVisibleItem);

        void rxUnsubscribe();
    }

    interface Model {
        String getLang(MySharedPreferences sharedPreferences);

        void saveLang(MySharedPreferences sharedPreferences, String lang);

        long getUserId(MySharedPreferences sharedPreferences);

        Call<FollowersResponse> getFollowers(long userId, long cursor);

        void saveFollowers(ApplicationDatabase database, List<FollowerEntity> followers);

        Maybe<List<FollowerEntity>> getFollowersOffline(ApplicationDatabase database);

        void rxUnsubscribe();
    }
}
