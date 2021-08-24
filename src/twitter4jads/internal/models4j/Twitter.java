package twitter4jads.internal.models4j;

import twitter4jads.api.internal.TweetsResources;
import twitter4jads.api.internal.UndocumentedResources;
import twitter4jads.api.internal.UsersResources;
import twitter4jads.auth.OAuthSupport;

/**
 *
 * @since Twitter4J 2.2.0
 */
public interface Twitter extends java.io.Serializable,
        OAuthSupport,
        TwitterBase,
        TweetsResources,
        UsersResources,
        UndocumentedResources {
}
