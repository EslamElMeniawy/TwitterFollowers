package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import elmeniawy.eslam.twitterfollowers.api.TwitterApiServices;
import elmeniawy.eslam.twitterfollowers.api.model.FollowersResponse;
import elmeniawy.eslam.twitterfollowers.storage.database.ApplicationDatabase;
import elmeniawy.eslam.twitterfollowers.storage.database.entities.FollowerEntity;
import elmeniawy.eslam.twitterfollowers.storage.preferences.MySharedPreferences;
import elmeniawy.eslam.twitterfollowers.utils.PreferencesUtils;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import timber.log.Timber;

/**
 * FollowersListRepository
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

public class FollowersListRepository implements Repository {
    private Disposable disposableGetFollowers = null,
            disposableDeleteFollowers = null,
            disposableInsertFollowers = null;

    @Override
    public String getLang(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getString(PreferencesUtils.KEY_LANG);
    }

    @Override
    public void saveLang(MySharedPreferences sharedPreferences, String lang) {
        sharedPreferences.putString(PreferencesUtils.KEY_LANG, lang);
    }

    @Override
    public long getUserId(MySharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(PreferencesUtils.KEY_USER_ID);
    }

    @Override
    public Call<FollowersResponse> getFollowers(long userId, long cursor) {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterApiServices apiClients = new TwitterApiServices(session);

        return apiClients
                .getFollowersService()
                .getFollowers(userId,
                        cursor);
    }

    @Override
    public void saveFollowers(ApplicationDatabase database, List<FollowerEntity> followers) {
        disposableGetFollowers = database
                .followerDao()
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(followerEntityList -> {
                    Timber.i("Got entities: %s.", followerEntityList);

                    if (followerEntityList != null && followerEntityList.size() > 0) {
                        deleteFollowers(database, followerEntityList, followers);
                    } else {
                        insertFollowers(database, followers);
                    }
                });
    }

    @Override
    public Maybe<List<FollowerEntity>> getFollowersOffline(ApplicationDatabase database) {
        return database
                .followerDao()
                .getData();
    }

    @Override
    public void rxUnsubscribe() {
        if (disposableGetFollowers != null && disposableGetFollowers.isDisposed()) {
            disposableGetFollowers.dispose();
        }

        if (disposableDeleteFollowers != null && disposableDeleteFollowers.isDisposed()) {
            disposableDeleteFollowers.dispose();
        }

        if (disposableInsertFollowers != null && disposableInsertFollowers.isDisposed()) {
            disposableInsertFollowers.dispose();
        }
    }

    private void deleteFollowers(ApplicationDatabase database,
                                 List<FollowerEntity> followersToDelete,
                                 List<FollowerEntity> followersToInsert) {
        disposableDeleteFollowers = Completable
                .fromAction(() -> {
                    int deletedRowsCount = database
                            .followerDao()
                            .deleteData(followersToDelete);

                    Timber.i("Deleted rows count: %d.", deletedRowsCount);
                    insertFollowers(database, followersToInsert);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
    }

    private void insertFollowers(ApplicationDatabase database,
                                 List<FollowerEntity> followersToInsert) {
        disposableInsertFollowers = Completable
                .fromAction(() -> {
                    List<Long> insertedIds = database
                            .followerDao()
                            .insertData(followersToInsert);

                    Timber.i("Inserted ids: %s.", insertedIds.toString());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
    }
}
