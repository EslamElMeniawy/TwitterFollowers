package elmeniawy.eslam.twitterfollowers.storage.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Locale;

import elmeniawy.eslam.twitterfollowers.utils.DatabaseUtils;

/**
 * FollowerEntity
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

@Entity(tableName = DatabaseUtils.TABLE_FOLLOWERS)
public class FollowerEntity {
    //
    // Attributes.
    //

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = DatabaseUtils.COLUMN_NAME)
    private String name;

    @ColumnInfo(name = DatabaseUtils.COLUMN_DESCRIPTION)
    private String description;

    @ColumnInfo(name = DatabaseUtils.COLUMN_PROFILE_BANNER)
    private String profileBanner;

    @ColumnInfo(name = DatabaseUtils.COLUMN_PROFILE_IMAGE)
    private String profileImage;

    //
    // Setters and getters.
    //

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

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

    public String getProfileImage() {
        return profileImage;
    }

    //
    // Convert object to string for logging.
    //

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "id: %d, name: %s, description: %s, profileBanner: %s, profileImage: %s.",
                id, name, description, profileBanner, profileImage);
    }
}
