package elmeniawy.eslam.twitterfollowers.screens.welcome;

import dagger.Module;
import dagger.Provides;

/**
 * WelcomeModule
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

@Module
public class WelcomeModule {
    @Provides
    WelcomeMVP.Presenter providedWelcomPresenter(WelcomeMVP.Model model) {
        return new WelcomePresenter(model);
    }

    @Provides
    WelcomeMVP.Model provideWelcomModel(Repository repository) {
        return new WelcomeModel(repository);
    }

    @Provides
    Repository provideWelcomeRepository() {
        return new WelcomeRepository();
    }
}
