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
    private Integer retweet;
    private Integer favorite;

    //
    // Setters and getters.
    //

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public String getTweet() {
        return tweet;
    }

    public Integer getRetweet() {
        return retweet;
    }

    public Integer getFavorite() {
        return favorite;
    }
}
