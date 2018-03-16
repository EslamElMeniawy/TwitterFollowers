package elmeniawy.eslam.twitterfollowers.screens.follower_info;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elmeniawy.eslam.twitterfollowers.R;
import timber.log.Timber;

/**
 * TweetsListAdapter
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

class TweetsListAdapter extends RecyclerView.Adapter<TweetsListAdapter.ViewHolderTweetsList> {
    private List<TweetViewModel> list;

    TweetsListAdapter(List<TweetViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderTweetsList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_tweet, parent, false);

        Timber.tag(TweetsListAdapter.class.getSimpleName());
        return new ViewHolderTweetsList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTweetsList holder, int position) {
        TweetViewModel currentTweet = list.get(position);
        Timber.i("Tweet: %s.", currentTweet.getTweet());
        holder.tvTweet.setText(currentTweet.getTweet());
        Timber.i("Retweet count: %d.", currentTweet.getRetweet());
        holder.tvRetweet.setText(String.valueOf(currentTweet.getRetweet()));
        Timber.i("Favorite count: %d.", currentTweet.getFavorite());
        holder.tvFavorite.setText(String.valueOf(currentTweet.getFavorite()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderTweetsList extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tweet)
        AppCompatTextView tvTweet;

        @BindView(R.id.tv_retweet)
        AppCompatTextView tvRetweet;

        @BindView(R.id.tv_favorite)
        AppCompatTextView tvFavorite;

        ViewHolderTweetsList(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
