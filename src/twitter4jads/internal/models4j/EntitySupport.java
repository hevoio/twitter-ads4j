package twitter4jads.internal.models4j;

/**
 *
 * @since Twitter4J 2.2.5
 */
public interface EntitySupport {
    /**
     * Returns an array of user mentions in the tweet, or null if no users were mentioned.
     *
     * @return An array of user mention entities in the tweet.
     * @since Twitter4J 2.1.9
     */
    UserMentionEntity[] getUserMentionEntities();

    /**
     * Returns an array if URLEntity mentioned in the tweet, or null if no URLs were mentioned.
     *
     * @return An array of URLEntity mentioned in the tweet.
     * @since Twitter4J 2.1.9
     */
    URLEntity[] getURLEntities();

    /**
     * Returns an array if hashtag mentioned in the tweet, or null if no hashtag were mentioned.
     *
     * @return An array of Hashtag mentioned in the tweet.
     * @since Twitter4J 2.1.9
     */
    HashtagEntity[] getHashtagEntities();

    /**
     * Returns an array of MediaEntities if medias are available in the tweet, or null if no media is included in the tweet.
     *
     * @return an array of MediaEntities.
     * @since Twitter4J 2.2.3
     */
    MediaEntity[] getMediaEntities();
    MediaEntity[] getExtendedMediaEntities();
}
