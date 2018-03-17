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
     * @param userId Get UserId after login and pass it here.
     * @param cursor The cursor of page to get.
     * @return Call object of type FOLLOWERS.
     */
    @GET("/1.1/followers/list.json?skip_status=true&include_user_entities=false")
    Call<FollowersResponse> getFollowers(@Query("user_id") Long userId,
                                         @Query("cursor") Long cursor);
}
