
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

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public Long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(Long previousCursor) {
        this.previousCursor = previousCursor;
    }

}
