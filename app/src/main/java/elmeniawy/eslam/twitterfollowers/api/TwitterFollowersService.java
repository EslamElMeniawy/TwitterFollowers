package elmeniawy.eslam.twitterfollowers.api;

import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * TwitterFollowersService
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public interface TwitterFollowersService {
    /**
     * This method id used to get the List of TWITTER FOLLOWERS.
     *
     * @param userId Get UserId after login and pass it here
     * @param var1   Weather to skip status accept TRUE/FALSE
     * @param var2   Weather to include Entities accept TRUE/FALSE
     * @return Call object of type FOLLOWERS.
     */
    @GET("/1.1/followers/list.json")
    Call<FollowersResponse> getFollowers(@Query("user_id") Long userId,
                                         @Query("skip_status") Boolean var1,
                                         @Query("include_user_entities") Boolean var2);
}
