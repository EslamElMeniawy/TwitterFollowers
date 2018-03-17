package elmeniawy.eslam.twitterfollowers.screens.image_browser;

/**
 * BrowseImageMVP
 * <p>
 * Created by Eslam El-Meniawy on 17-Mar-18.
 */

public interface BrowseImageMVP {
    interface View {
        void loadImage(String image);

        void hideLoading();

        void hideImage();

        void showError();

        void closeActivity();

        void addCloseAnimation();
    }

    interface Presenter {
        void setImage(String image);

        void setView(BrowseImageMVP.View view);

        void loadImage();

        void imageLoadSuccess();

        void imageLoadFail();

        void backClicked();
    }

    interface Model {
    }
}
