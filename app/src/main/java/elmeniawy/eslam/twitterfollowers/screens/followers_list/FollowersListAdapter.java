package elmeniawy.eslam.twitterfollowers.screens.followers_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import elmeniawy.eslam.twitterfollowers.R;
import timber.log.Timber;

/**
 * FollowersListAdapter
 * <p>
 * Created by Eslam El-Meniawy on 15-Mar-18.
 */

class FollowersListAdapter extends
        RecyclerView.Adapter<FollowersListAdapter.ViewHolderFollowersList> {
    private List<FollowerViewModel> list;
    private Context context;

    FollowersListAdapter(List<FollowerViewModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderFollowersList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_follower, parent, false);

        Timber.tag(FollowersListAdapter.class.getSimpleName());
        return new ViewHolderFollowersList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFollowersList holder, int position) {
        FollowerViewModel currentFollower = list.get(position);
        Timber.i("Load image at: %s.", currentFollower.getProfileImage());

        Picasso
                .get()
                .load(currentFollower.getProfileImage())
                .placeholder(R.drawable.ic_twitter_logo)
                .error(R.drawable.ic_twitter_logo)
                .into(holder.ivProfileImage);

        Timber.i("Name: %s.", currentFollower.getName());
        holder.tvName.setText(currentFollower.getName());
        Timber.i("Bio: %s.", currentFollower.getDescription());
        holder.tvBio.setText(currentFollower.getDescription());

        if (currentFollower.getDescription().isEmpty()) {
            holder.tvBio.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderFollowersList extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_profile_image)
        CircleImageView ivProfileImage;

        @BindView(R.id.tv_name)
        AppCompatTextView tvName;

        @BindView(R.id.tv_bio)
        AppCompatTextView tvBio;

        ViewHolderFollowersList(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        void itemClicked() {
            Timber.i("Item clicked at position: %d.", getLayoutPosition());
        }
    }
}
