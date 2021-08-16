package com.hevodata.twitterads4j.internal.http;

import com.hevodata.twitterads4j.auth.Authorization;
import com.hevodata.twitterads4j.auth.BasicAuthorization;

/**
 *
 * @since Twitter4J 2.1.3
 */
public class XAuthAuthorization implements Authorization, java.io.Serializable {
    private BasicAuthorization basic;

    private String consumerKey;
    private String consumerSecret;
    private static final long serialVersionUID = -6082451214083464902L;

    public XAuthAuthorization(BasicAuthorization basic) {
        this.basic = basic;
    }

    @Override
    public String getAuthorizationHeader(HttpRequest req) {
        return basic.getAuthorizationHeader(req);
    }

    public String getUserId() {
        return basic.getUserId();
    }

    public String getPassword() {
        return basic.getPassword();
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public synchronized void setOAuthConsumer(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    @Override
    public boolean isEnabled() {
        return basic.isEnabled();
    }
}
