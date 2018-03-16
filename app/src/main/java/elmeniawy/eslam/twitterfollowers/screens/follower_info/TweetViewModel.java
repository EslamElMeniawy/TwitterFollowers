package elmeniawy.eslam.twitterfollowers.screens.follower_info;

/**
 * TweetViewModel
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

public class TweetViewModel {
    //
    // Attributes.
    //

    private String tweet;
    private Long retweet;
    private Long favorite;

    //
    // Setters and getters.
    //

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void setRetweet(Long retweet) {
        this.retweet = retweet;
    }

    public void setFavorite(Long favorite) {
        this.favorite = favorite;
    }

    public String getTweet() {
        return tweet;
    }

    public Long getRetweet() {
        return retweet;
    }

    public Long getFavorite() {
        return favorite;
    }
}
