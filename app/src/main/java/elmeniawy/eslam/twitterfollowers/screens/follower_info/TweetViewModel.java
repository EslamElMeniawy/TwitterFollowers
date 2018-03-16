package elmeniawy.eslam.twitterfollowers.screens.follower_info;

/**
 * TweetViewModel
 * <p>
 * Created by Eslam El-Meniawy on 16-Mar-18.
 */

class TweetViewModel {
    //
    // Attributes.
    //

    private String tweet;
    private Integer retweet;
    private Integer favorite;

    //
    // Setters and getters.
    //

    void setTweet(String tweet) {
        this.tweet = tweet;
    }

    void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }

    void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    String getTweet() {
        return tweet;
    }

    Integer getRetweet() {
        return retweet;
    }

    Integer getFavorite() {
        return favorite;
    }
}
