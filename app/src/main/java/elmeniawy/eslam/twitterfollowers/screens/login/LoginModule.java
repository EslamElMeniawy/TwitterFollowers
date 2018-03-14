package elmeniawy.eslam.twitterfollowers.screens.login;

import dagger.Module;
import dagger.Provides;

/**
 * LoginModule
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

@Module
public class LoginModule {
    @Provides
    LoginMVP.Presenter providedLoginPresenter(LoginMVP.Model model) {
        return new LoginPresenter(model);
    }

    @Provides
    LoginMVP.Model provideLoginModel(Repository repository) {
        return new LoginModel(repository);
    }

    @Provides
    Repository provideLoginRepository() {
        return new LoginRepository();
    }
}
