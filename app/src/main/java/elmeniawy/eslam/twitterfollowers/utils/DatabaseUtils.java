package elmeniawy.eslam.twitterfollowers.utils;

/**
 * DatabaseUtils
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

public class DatabaseUtils {
    //
    // Database name & version.
    //

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "twitter_followers_db";

    //
    // Followers table.
    //

    public static final String TABLE_FOLLOWERS = "movies";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PROFILE_BANNER = "profile_banner_url";
    public static final String COLUMN_PROFILE_IMAGE = "profile_image_url_https";
}
