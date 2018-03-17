
package elmeniawy.eslam.twitterfollowers.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowersResponse {
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    @SerializedName("next_cursor")
    @Expose
    private Long nextCursor;

    @SerializedName("previous_cursor")
    @Expose
    private Long previousCursor;

    public List<User> getUsers() {
        return users;
    }

    public Long getNextCursor() {
        return nextCursor;
    }

    public Long getPreviousCursor() {
        return previousCursor;
    }
}
