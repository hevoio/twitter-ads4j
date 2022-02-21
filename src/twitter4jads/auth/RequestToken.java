package twitter4jads.auth;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.conf.ConfigurationContext;
import twitter4jads.internal.http.HttpResponse;

/**
 *
 * representing unauthorized Request Token which is passed to the service provider when acquiring the authorized Access Token
 */
public final class RequestToken extends OAuthToken implements java.io.Serializable {
    private final Configuration conf;
    private OAuthSupport oauth;
    private static final long serialVersionUID = -8214365845469757952L;

    RequestToken(HttpResponse res, OAuthSupport oauth) throws TwitterException {
        super(res);
        conf = ConfigurationContext.getInstance();
        this.oauth = oauth;
    }
}
