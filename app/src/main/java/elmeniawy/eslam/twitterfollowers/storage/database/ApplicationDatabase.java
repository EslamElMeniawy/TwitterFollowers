package elmeniawy.eslam.twitterfollowers.storage.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import elmeniawy.eslam.twitterfollowers.storage.database.daos.FollowerDao;
import elmeniawy.eslam.twitterfollowers.storage.database.entities.FollowerEntity;
import elmeniawy.eslam.twitterfollowers.utils.DatabaseUtils;

/**
 * ApplicationDatabase
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

@Database(entities = {FollowerEntity.class}, version = DatabaseUtils.DATABASE_VERSION)
public abstract class ApplicationDatabase extends RoomDatabase {
    //
    // Followers DAOs.
    //

    public abstract FollowerDao followerDao();
}
