package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.api.model.User;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import retrofit2.Call;

/**
 * FollowersListModel
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public class FollowersListModel implements FollowersListMVP.Model {
    private Repository repository;

    FollowersListModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String getLang(MySharedPreferences sharedPreferences) {
        return repository.getLang(sharedPreferences);
    }

    @Override
    public void saveLang(MySharedPreferences sharedPreferences, String lang) {
        repository.saveLang(sharedPreferences, lang);
    }

    @Override
    public long getUserId(MySharedPreferences sharedPreferences) {
        return repository.getUserId(sharedPreferences);
    }

    @Override
    public Call<FollowersResponse> getFollowers(long userId, long cursor) {
        return repository.getFollowers(userId, cursor);
    }

    @Override
    public void saveFollowers(List<User> followers) {
        repository.saveFollowers(followers);
    }
}
