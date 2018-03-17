package elmeniawy.eslam.twitterfollowers.screens.image_browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ProgressBar;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import elmeniawy.eslam.twitterfollowers.R;
import elmeniawy.eslam.twitterfollowers.root.MyApplication;
import elmeniawy.eslam.twitterfollowers.utils.ConstantUtils;
import timber.log.Timber;

public class ActivityBrowseImage extends AppCompatActivity implements BrowseImageMVP.View {
    @Inject
    BrowseImageMVP.Presenter presenter;

    //
    // Bind views.
    //

    @BindView(R.id.image_view)
    PhotoView imageView;

    @BindView(R.id.pb_loading)
    ProgressBar loading;

    @BindView(R.id.tv_error)
    AppCompatTextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_image);

        //
        // Inject activity.
        //

        ((MyApplication) getApplication()).getComponent().inject(this);

        //
        // Initialize butter knife.
        //

        ButterKnife.bind(this);

        //
        // Set image.
        //

        if (getIntent() != null) {
            Timber.i("Image: %s.", getIntent()
                    .getParcelableExtra(ConstantUtils.INTENT_KEY_IMAGE));

            presenter.setImage(getIntent()
                    .getStringExtra(ConstantUtils.INTENT_KEY_IMAGE));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadImage();
    }

    @Override
    public void onBackPressed() {
        Timber.i("onBackPressed");
        presenter.backClicked();
    }

    @Override
    public void loadImage(String image) {
        Picasso
                .get()
                .load(image)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.imageLoadSuccess();
                    }

                    @Override
                    public void onError(Exception e) {
                        presenter.imageLoadFail();
                    }
                });
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void hideImage() {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeActivity() {
        ActivityBrowseImage.this.finish();
    }

    @Override
    public void addCloseAnimation() {
        overridePendingTransition(R.anim.fadein, R.anim.bottom_down);
    }
}
