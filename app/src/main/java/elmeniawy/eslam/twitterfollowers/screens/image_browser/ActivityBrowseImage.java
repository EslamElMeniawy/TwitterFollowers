package elmeniawy.eslam.twitterfollowers.screens.image_browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;
import timber.log.Timber;

public class ActivityBrowseImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_image);

        //
        // Set image.
        //

        if (getIntent() != null) {
            Timber.i("Image: %s.", getIntent()
                    .getParcelableExtra(ConstantUtils.INTENT_KEY_IMAGE));

            /*presenter.setFollower(getIntent()
                    .getParcelableExtra(ConstantUtils.INTENT_KEY_IMAGE));*/
        }
    }
}
