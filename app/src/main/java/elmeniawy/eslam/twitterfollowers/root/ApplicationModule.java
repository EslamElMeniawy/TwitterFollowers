package elmeniawy.eslam.twitterfollowers.root;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ApplicationModule
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

@Module
class ApplicationModule {
    private MyApplication application;

    ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }
}
