package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.api.model.User;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import retrofit2.Call;

/**
 * Repository
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public interface Repository {
    String getLang(MySharedPreferences sharedPreferences);

    void saveLang(MySharedPreferences sharedPreferences, String lang);

    long getUserId(MySharedPreferences sharedPreferences);

    Call<FollowersResponse> getFollowers(long userId);

    void saveFollowers(List<User> followers);
}
