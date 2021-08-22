package twitter4jads.internal.models4j;


/**
 * A data interface that has detailed information about a relationship between two users
 *
 *  Perry Sakkaris - psakkaris at gmail.com
 * @see <a href="https://dev.twitter.com/docs/api/1.1/get/friendships/show">GET friendships/show | Twitter Developers</a>
 * @since Twitter4J 2.1.0
 */
public interface Relationship extends TwitterResponse, java.io.Serializable {
    /**
     * Returns the source user id
     *
     * @return the source user id
     */
    long getSourceUserId();

    /**
     * Returns the target user id
     *
     * @return target user id
     */
    long getTargetUserId();

    /**
     * Returns if the source user is blocking the target user
     *
     * @return if the source is blocking the target
     */
    boolean isSourceBlockingTarget();

    /**
     * Returns the source user screen name
     *
     * @return returns the source user screen name
     */
    String getSourceUserScreenName();

    /**
     * Returns the target user screen name
     *
     * @return the target user screen name
     */
    String getTargetUserScreenName();

    /**
     * Checks if source user is following target user
     *
     * @return true if source user is following target user
     */
    boolean isSourceFollowingTarget();

    /**
     * Checks if target user is following source user.<br>
     * This method is equivalent to isSourceFollowedByTarget().
     *
     * @return true if target user is following source user
     */
    boolean isTargetFollowingSource();

    /**
     * Checks if source user is being followed by target user
     *
     * @return true if source user is being followed by target user
     */
    boolean isSourceFollowedByTarget();

    /**
     * Checks if target user is being followed by source user.<br>
     * This method is equivalent to isSourceFollowingTarget().
     *
     * @return true if target user is being followed by source user
     */
    boolean isTargetFollowedBySource();

    /**
     * Checks if the source user has enabled notifications for updates of the target user
     *
     * @return true if source user enabled notifications for target user
     */
    boolean isSourceNotificationsEnabled();

    /**
     * Checks if the retweets from the target user enabled
     *
     * @return true if the retweets from the target user enabled
     * @since Twitter4J 3.0.3
     */
    boolean isSourceWantRetweets();

    boolean canSourceDM();
}
