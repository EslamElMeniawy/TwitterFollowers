package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.storage.database.ApplicationDatabase;
import elmeniawy.eslam.twitterfollowers.storage.database.entities.FollowerEntity;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import io.reactivex.Maybe;
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

    Call<FollowersResponse> getFollowers(long userId, long cursor);

    void saveFollowers(ApplicationDatabase database, List<FollowerEntity> followers);

    Maybe<List<FollowerEntity>> getFollowersOffline(ApplicationDatabase database);

    void rxUnsubscribe();
}
