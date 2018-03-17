
package elmeniawy.eslam.twitterfollowers.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("profile_banner_url")
    @Expose
    private String profileBanner;

    @SerializedName("profile_image_url_https")
    @Expose
    private String profileImageUrlHttps;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }
}
