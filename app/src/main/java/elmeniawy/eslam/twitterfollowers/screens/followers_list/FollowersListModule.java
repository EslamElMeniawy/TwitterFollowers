package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import dagger.Module;
import dagger.Provides;

/**
 * FollowersListModule
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

@Module
public class FollowersListModule {
    @Provides
    FollowersListMVP.Presenter providedFollowersListPresenter(FollowersListMVP.Model model) {
        return new FollowersListPresenter(model);
    }

    @Provides
    FollowersListMVP.Model provideFollowersListModel(Repository repository) {
        return new FollowersListModel(repository);
    }

    @Provides
    Repository provideFollowersListRepository() {
        return new FollowersListRepository();
    }
}
