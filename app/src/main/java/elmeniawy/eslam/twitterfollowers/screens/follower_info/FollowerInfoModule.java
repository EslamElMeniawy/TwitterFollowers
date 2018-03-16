package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import dagger.Module;
import dagger.Provides;

/**
 * FollowerInfoModule
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

@Module
public class FollowerInfoModule {
    @Provides
    FollowerInfoMVP.Presenter providedFollowerInfoPresenter(FollowerInfoMVP.Model model) {
        return new FollowerInfoPresenter(model);
    }

    @Provides
    FollowerInfoMVP.Model provideFollowerInfoModel(Repository repository) {
        return new FollowerInfoModel(repository);
    }

    @Provides
    Repository provideFollowerInfoRepository() {
        return new FollowerInfoRepository();
    }
}
