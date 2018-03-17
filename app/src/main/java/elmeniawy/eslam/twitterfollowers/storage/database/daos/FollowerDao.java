package elmeniawy.eslam.twitterfollowers.storage.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.storage.database.entities.FollowerEntity;
import elmeniawy.eslam.twitterfollowers.utils.DatabaseUtils;
import io.reactivex.Maybe;

/**
 * FollowerDao
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

@Dao
public interface FollowerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertData(List<FollowerEntity> followersList);

    @Query("SELECT * FROM " + DatabaseUtils.TABLE_FOLLOWERS)
    Maybe<List<FollowerEntity>> getData();

    @Delete
    int deleteData(List<FollowerEntity> followersList);
}
