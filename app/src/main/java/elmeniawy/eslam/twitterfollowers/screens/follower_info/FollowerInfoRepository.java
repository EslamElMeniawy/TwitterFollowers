package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;
import retrofit2.Call;

/**
 * FollowerInfoRepository
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public class FollowerInfoRepository implements Repository {
    @Override
    public String getLang(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getString(PreferencesUtils.KEY_LANG);
    }

    @Override
    public Call<List<Tweet>> getTweets(long userId) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        return statusesService
                .userTimeline(userId, null, 10, null, null,
                        null, null, null, null);
    }
}
