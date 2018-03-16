package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import retrofit2.Call;

/**
 * Repository
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public interface Repository {
    String getLang(MySharedPreferences sharedPreferences);

    Call<List<Tweet>> getFollowers(long userId);
}
