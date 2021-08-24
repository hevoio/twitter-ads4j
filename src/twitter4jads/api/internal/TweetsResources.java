package twitter4jads.api.internal;


import twitter4jads.internal.models4j.Status;
import twitter4jads.internal.models4j.VideoTweetResponse;
import twitter4jads.internal.models4j.Media;
import twitter4jads.internal.models4j.MediaUpload;
import twitter4jads.internal.models4j.ResponseList;
import twitter4jads.internal.models4j.StatusUpdate;
import twitter4jads.internal.models4j.TonUpload;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.models4j.VideoTweetRequest;

public interface TweetsResources {
    /**
     * Returns up to 100 of the first retweets of a given tweet.
     * <br>This method calls http://api.twitter.com/1.1/statuses/retweets
     *
     * @param statusId The numerical ID of the tweet you want the retweets of.
     * @return the retweets of a given tweet
     * @throws TwitterException when Twitter service or network is unavailable
     * @see <a href=\u0093https://dev.twitter.com/docs/api/1.1/get/statuses/retweets/:id\u0094>Tweets Resources \u009B statuses/retweets/:id</a>
     * @since Twitter4J 2.0.10
     */
    ResponseList<Status> getRetweets(long statusId) throws TwitterException;

    /**
     * Returns a single status, specified by the id parameter below. The status's author will be returned inline.
     * <br>This method calls http://api.twitter.com/1.1/statuses/show
     *
     * @param id the numerical ID of the status you're trying to retrieve
     * @return a single status
     * @see <a href="https://dev.twitter.com/docs/api/1.1/get/statuses/show/:id">GET statuses/show/:id | Twitter Developers</a>
     * @since Twitter4J 2.0.1
     */
    Status showStatus(long id) throws TwitterException;

    /**
     * Destroys the status specified by the required ID parameter.<br>
     * Usage note: The authenticating user must be the author of the specified status.
     * <br>This method calls http://api.twitter.com/1.1/statuses/destroy
     *
     * @param statusId The ID of the status to destroy.
     * @return the deleted status
     * @throws TwitterException when Twitter service or network is unavailable
     * @see <a href="https://dev.twitter.com/docs/api/1.1/post/statuses/destroy/:id">POST statuses/destroy/:id | Twitter Developers</a>
     * @since 1.0.5
     */
    Status destroyStatus(long statusId) throws TwitterException;

    /**
     * Updates the authenticating user's status. A status update with text identical to the authenticating user's text identical to the authenticating user's current status will be ignored to prevent duplicates.
     * <br>This method calls http://api.twitter.com/1.1/statuses/update
     *
     * @param status the text of your status update
     * @return the latest status
     * @throws TwitterException when Twitter service or network is unavailable
     * @see <a href="https://dev.twitter.com/docs/api/1.1/post/statuses/update">POST statuses/update | Twitter Developers</a>
     * @since Twitter4J 2.0.1
     */
    Status updateStatus(String status) throws TwitterException;

    /**
     * Updates the authenticating user's status. A status update with text identical to the authenticating user's text identical to the authenticating user's current status will be ignored to prevent duplicates.
     * <br>This method calls http://api.twitter.com/1.1/statuses/update or<br>
     * This method calls https://upload.twitter.com/1/statuses/update_with_media
     *
     * @param latestStatus the latest status to be updated.
     * @return the latest status
     * @throws TwitterException when Twitter service or network is unavailable
     * @see <a href="https://dev.twitter.com/docs/api/1.1/post/statuses/update">POST statuses/update | Twitter Developers</a>
     * @since Twitter4J 2.1.1
     */
    Status updateStatus(StatusUpdate latestStatus) throws TwitterException;

    /**
     * Retweets a tweet. Returns the original tweet with retweet details embedded.
     * <br>This method calls http://api.twitter.com/1.1/statuses/retweet
     *
     * @param statusId The ID of the status to retweet.
     * @return the retweeted status
     * @throws TwitterException when Twitter service or network is unavailable
     * @see <a href="https://dev.twitter.com/docs/api/1.1/post/statuses/retweet/:id">POST statuses/retweet/:id | Twitter Developers</a>
     * @since Twitter4J 2.0.10
     */
    Status retweetStatus(long statusId) throws TwitterException;

    /**
     * Returns fully-hydrated tweet objects for up to 100 tweets per request, as specified by comma-separated values passed to the id parameter.
     * This method is especially useful to get the details (hydrate) a collection of Tweet IDs.
     * <br>This method calls https://api.twitter.com/1.1/statuses/statusesLookup.json
     *
     * @param statusIds array of the statusIds to statusesLookup
     * @return list of the tweets
     * @throws TwitterException when Twitter service or network is unavailable
     * @see <a href="https://dev.twitter.com/docs/api/1.1/get/statuses/lookup">GET statuses/lookup</a>
     * @since Twitter4J 4.0.2
     */
    ResponseList<Status> lookupStatuses(long[] statusIds) throws TwitterException;

    public Media upload(MediaUpload mediaUpload) throws TwitterException;


    String uploadToTon(TonUpload tonUpload) throws TwitterException;

    String createVideo(String tonVideoLocation, String title, String description) throws TwitterException;

    String createVideoImage(String tonImageLocation, String title, String description) throws TwitterException;

    VideoTweetResponse createVideoTweet(VideoTweetRequest request) throws TwitterException;

    /**
     * @param filePath     temporary file
     * @param fileSize
     * @param mediaType
     * @param maxChunkSize Maximum chunk size allowed for twitter.  @return mediaId of the video..
     * @throws TwitterException
     */
    String uploadMediaInChunks(String filePath, long fileSize, String mediaType, String mediaCategory, int maxChunkSize, long maxWaitTimeTranscoding,
                               String accountUserId)
            throws TwitterException;

    /**
     * Creates a meta data which can be used with twitter objects.
     *
     * @param mediaMetadataRequest The metadata value to create.
     * @throws TwitterException
     */

}
