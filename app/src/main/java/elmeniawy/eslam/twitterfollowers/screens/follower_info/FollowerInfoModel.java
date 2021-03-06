package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;

/**
 * FollowerInfoModel
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public class FollowerInfoModel implements FollowerInfoMVP.Model {
    private Repository repository;

    FollowerInfoModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Call<List<Tweet>> getTweets(long userId) {
        return repository.getTweets(userId);
    }
}
