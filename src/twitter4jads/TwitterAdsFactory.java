package twitter4jads;

import twitter4jads.auth.AccessToken;
import twitter4jads.auth.Authorization;
import twitter4jads.auth.AuthorizationFactory;
import twitter4jads.auth.OAuthAuthorization;
import twitter4jads.conf.Configuration;
import twitter4jads.conf.ConfigurationContext;
import twitter4jads.internal.models4j.Twitter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * Date: 3/16/16
 * Time: 1:10 PM
 */
public class TwitterAdsFactory {
    private final Configuration conf;

    /**
     * Creates a TwitterFactory with the given configuration.
     *
     * @param conf the configuration to use
     * @since Twitter4J 2.1.1
     */
    public TwitterAdsFactory(Configuration conf) {
        if (conf == null) {
            throw new NullPointerException("configuration cannot be null");
        }
        this.conf = conf;
    }

    public TwitterAds getAdsInstance() {
        return new TwitterAdsImpl(conf, AuthorizationFactory.getInstance(conf));
    }
}
