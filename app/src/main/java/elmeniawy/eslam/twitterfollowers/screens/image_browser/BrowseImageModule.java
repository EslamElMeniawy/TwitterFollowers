package elmeniawy.eslam.twitterfollowers.screens.image_browser;

import dagger.Module;
import dagger.Provides;

/**
 * BrowseImageModule
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

@Module
public class BrowseImageModule {
    @Provides
    BrowseImageMVP.Presenter providedBrowseImagePresenter() {
        return new BrowseImagePresenter();
    }
}
