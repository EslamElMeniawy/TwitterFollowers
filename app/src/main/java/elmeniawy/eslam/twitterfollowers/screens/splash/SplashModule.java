package elmeniawy.eslam.twitterfollowers.screens.splash;

import dagger.Module;
import dagger.Provides;

/**
 * SplashModule
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

@Module
public class SplashModule {
    @Provides
    SplashMVP.Presenter providedSplashPresenter(SplashMVP.Model model) {
        return new SplashPresenter(model);
    }

    @Provides
    SplashMVP.Model provideSplashModel(Repository repository) {
        return new SplashModel(repository);
    }

    @Provides
    Repository provideSplashRepository() {
        return new SplashRepository();
    }
}
