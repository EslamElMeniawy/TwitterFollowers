package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FollowerViewModel
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public class FollowerViewModel implements Parcelable {
    //
    // Attributes.
    //

    private Long id;
    private String name;
    private String description;
    private String profileBanner;
    private String profileImage;

    FollowerViewModel() {
    }

    private FollowerViewModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }

        name = in.readString();
        description = in.readString();
        profileBanner = in.readString();
        profileImage = in.readString();
    }

    public static final Creator<FollowerViewModel> CREATOR = new Creator<FollowerViewModel>() {
        @Override
        public FollowerViewModel createFromParcel(Parcel in) {
            return new FollowerViewModel(in);
        }

        @Override
        public FollowerViewModel[] newArray(int size) {
            return new FollowerViewModel[size];
        }
    };

    //
    // Setters and getters.
    //

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setProfileBanner(String profileBackgroundImage) {
        this.profileBanner = profileBackgroundImage;
    }

    void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public String getProfileImage() {
        return profileImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }

        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(profileBanner);
        dest.writeString(profileImage);
    }
}
