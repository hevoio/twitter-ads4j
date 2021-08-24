package twitter4jads.auth;

/**
 * A static factory class for Authorization.
 *
 *
 *
 */
public interface AuthorizationConfiguration {

    String getUser();

    String getPassword();

    String getOAuthConsumerKey();

    String getOAuthConsumerSecret();

    String getOAuthAccessToken();

    String getOAuthAccessTokenSecret();
}
