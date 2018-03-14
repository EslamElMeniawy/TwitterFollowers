package elmeniawy.eslam.twitterfollowers.screens.welcome;

import android.support.annotation.Nullable;

import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;

/**
 * WelcomePresenter
 * <p>
 * Created by Eslam El-Meniawy on 14-Mar-18.
 */

public class WelcomePresenter implements WelcomeMVP.Presenter {
    @Nullable
    private WelcomeMVP.View view;

    private WelcomeMVP.Model model;

    WelcomePresenter(WelcomeMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(@Nullable WelcomeMVP.View view) {
        this.view = view;
    }

    @Override
    public void langCheckChanged(boolean checked) {
        if (checked) {
            if (view != null) {
                view.hideSelectionError();
            }
        }
    }

    @Override
    public void nextClicked() {
        if (view != null) {
            if (!view.getArChecked() && !view.getEnChecked()) {
                view.showSelectionError();
            } else {
                if (view.getArChecked()) {
                    model.saveLanguage(view.getSharedPreferences(), ConstantUtils.LANG_AR);
                } else {
                    model.saveLanguage(view.getSharedPreferences(), ConstantUtils.LANG_EN);
                }

                model.saveRunBefore(view.getSharedPreferences());
                view.openLogin();
                view.addOpenAnimation();
                view.closeActivity();
            }
        }
    }
}
