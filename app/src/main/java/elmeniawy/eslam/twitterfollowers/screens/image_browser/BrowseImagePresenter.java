package elmeniawy.eslam.twitterfollowers.screens.image_browser;

import android.support.annotation.Nullable;

import timber.log.Timber;

/**
 * BrowseImagePresenter
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

public class BrowseImagePresenter implements BrowseImageMVP.Presenter {
    @Nullable
    private String image;

    @Nullable
    private BrowseImageMVP.View view;

    @Override
    public void setImage(@Nullable String image) {
        this.image = image;
        Timber.i("Image: %s.", image);
    }

    @Override
    public void setView(@Nullable BrowseImageMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadImage() {
        if (view != null && image != null) {
            view.loadImage(image);
        }
    }

    @Override
    public void imageLoadSuccess() {
        if (view != null) {
            view.hideLoading();
        }
    }

    @Override
    public void imageLoadFail() {
        if (view != null) {
            view.hideImage();
            view.showError();
            view.hideLoading();
        }
    }

    @Override
    public void backClicked() {
        if (view != null) {
            view.closeActivity();
            view.addCloseAnimation();
        }
    }
}
