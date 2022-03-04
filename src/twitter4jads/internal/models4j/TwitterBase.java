package twitter4jads.internal.models4j;

import twitter4jads.auth.Authorization;
import twitter4jads.conf.Configuration;

/**
 *
 * @since Twitter4J 2.2.0
 */
public interface TwitterBase {

    /**
     * Returns the authorization scheme for this instance.<br>
     * The returned type will be either of BasicAuthorization, OAuthAuthorization, or NullAuthorization
     *
     * @return the authorization scheme for this instance
     */
    Authorization getAuthorization();

    /**
     * Returns the configuration associated with this instance
     *
     * @return configuration associated with this instance
     * @since Twitter4J 2.1.8
     */
    Configuration getConfiguration();

    /**
     * Shuts down this instance and releases allocated resources.
     */
    void shutdown();
}
