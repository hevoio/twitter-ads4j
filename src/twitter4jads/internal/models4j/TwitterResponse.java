package twitter4jads.internal.models4j;

/**
 * Super interface of Twitter Response data interfaces which indicates that rate limit status is avaialble.
 *
 *
 * @see twitter4jads.DirectMessage
 * @see twitter4jads.Status
 * @see twitter4jads.User
 */
public interface TwitterResponse extends java.io.Serializable {
    /**
     * Returns the current rate limit status if available.
     *
     * @return current rate limit status
     * @since Twitter4J 2.1.0
     */
    RateLimitStatus getRateLimitStatus();

    /**
     * @return application permission model
     * @see <a href="https://dev.twitter.com/pages/application-permission-model-faq#how-do-we-know-what-the-access-level-of-a-user-token-is">Application Permission Model FAQ - How do we know what the access level of a user token is?</a>
     * @since Twitter4J 2.2.3
     */
    int getAccessLevel();

    int NONE = 0;
    int READ = 1;
    int READ_WRITE = 2;
    int READ_WRITE_DIRECTMESSAGES = 3;


}
