package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import retrofit2.Call;

/**
 * FollowerInfoRepository
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public class FollowerInfoRepository implements Repository {
    @Override
    public Call<List<Tweet>> getTweets(long userId) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        return statusesService
                .userTimeline(userId, null, 10, null, null,
                        null, null, null, null);
    }
}
