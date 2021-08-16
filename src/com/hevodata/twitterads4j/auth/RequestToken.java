package com.hevodata.twitterads4j.auth;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.conf.ConfigurationContext;
import com.hevodata.twitterads4j.internal.http.HttpResponse;

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

    public RequestToken(String token, String tokenSecret) {
        super(token, tokenSecret);
        conf = ConfigurationContext.getInstance();
    }

    RequestToken(String token, String tokenSecret, OAuthSupport oauth) {
        super(token, tokenSecret);
        conf = ConfigurationContext.getInstance();
        this.oauth = oauth;
    }

    /**
     * @return authorization URL
     *         since Twitter4J 2.0.0
     */
    public String getAuthorizationURL() {
        return conf.getOAuthAuthorizationURL() + "?oauth_token=" + getToken();
    }

    /**
     * @return authentication URL
     *         since Twitter4J 2.0.10
     */
    public String getAuthenticationURL() {
        return conf.getOAuthAuthenticationURL() + "?oauth_token=" + getToken();
    }

}
