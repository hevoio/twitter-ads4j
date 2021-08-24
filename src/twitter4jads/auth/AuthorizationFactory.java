package twitter4jads.auth;

import twitter4jads.conf.Configuration;

/**
 * A static factory class for Authorization.
 *
 *
 */
public final class AuthorizationFactory {
    /**
     * @param conf configuration
     * @return authorization instance
     * @since Twitter4J 2.1.11
     */
    public static Authorization getInstance(Configuration conf) {
        Authorization auth = null;
        String consumerKey = conf.getOAuthConsumerKey();
        String consumerSecret = conf.getOAuthConsumerSecret();

        if (consumerKey != null && consumerSecret != null) {
            OAuthAuthorization oauth;
            oauth = new OAuthAuthorization(conf);
            String accessToken = conf.getOAuthAccessToken();
            String accessTokenSecret = conf.getOAuthAccessTokenSecret();
            if (accessToken != null && accessTokenSecret != null) {
                oauth.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
            }
            auth = oauth;
        } else {
            String screenName = conf.getUser();
            String password = conf.getPassword();
            if (screenName != null && password != null) {
                auth = new BasicAuthorization(screenName, password);
            }
        }
        if (null == auth) {
            auth = NullAuthorization.getInstance();
        }
        return auth;
    }
}
