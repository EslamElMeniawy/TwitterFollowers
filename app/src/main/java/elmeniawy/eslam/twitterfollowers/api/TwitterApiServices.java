package elmeniawy.eslam.twitterfollowers.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * TwitterApiServices
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public class TwitterApiServices extends TwitterApiClient {
    public TwitterApiServices(TwitterSession session) {
        super(session);
    }

    public TwitterFollowersService getFollowersService() {
        return getService(TwitterFollowersService.class);
    }
}
