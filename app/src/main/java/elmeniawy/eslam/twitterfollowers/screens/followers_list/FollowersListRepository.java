package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.TwitterApiServices;
import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.api.model.User;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;
import retrofit2.Call;

/**
 * FollowersListRepository
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public class FollowersListRepository implements Repository {
    @Override
    public String getLang(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getString(PreferencesUtils.KEY_LANG);
    }

    @Override
    public void saveLang(MySharedPreferences sharedPreferences, String lang) {
        sharedPreferences.putString(PreferencesUtils.KEY_LANG, lang);
    }

    @Override
    public long getUserId(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(PreferencesUtils.KEY_USER_ID);
    }

    @Override
    public Call<FollowersResponse> getFollowers(long userId, long cursor) {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterApiServices apiClients = new TwitterApiServices(session);

        return apiClients
                .getFollowersService()
                .getFollowers(userId,
                        cursor);
    }

    @Override
    public void saveFollowers(List<User> followers) {

    }
}
