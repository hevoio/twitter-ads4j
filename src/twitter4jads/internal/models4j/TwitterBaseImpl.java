package twitter4jads.internal.models4j;

import twitter4jads.auth.*;
import twitter4jads.internal.http.*;
import twitter4jads.conf.Configuration;

import twitter4jads.internal.json.z_T4JInternalJSONImplFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import static twitter4jads.internal.http.HttpResponseCode.ENHANCE_YOUR_CLAIM;
import static twitter4jads.internal.http.HttpResponseCode.SERVICE_UNAVAILABLE;

/**
 * Base class of Twitter / AsyncTwitter / TwitterStream supports OAuth.
 *
 *
 */
abstract class TwitterBaseImpl implements TwitterBase, Serializable, OAuthSupport, HttpResponseListener {
    protected Configuration conf;
    protected transient String screenName = null;
    protected transient long id = 0;

    protected transient HttpClientWrapper http;
    private List<RateLimitStatusListener> rateLimitStatusListeners = new ArrayList<RateLimitStatusListener>(0);

    protected Authorization auth;
    private static final long serialVersionUID = -3812176145960812140L;

    /*package*/ TwitterBaseImpl(Configuration conf, Authorization auth) {
        this.conf = conf;
        this.auth = auth;
        init();
    }

    private void init() {
        if (null == auth) {
            // try to populate OAuthAuthorization if available in the configuration
            String consumerKey = conf.getOAuthConsumerKey();
            String consumerSecret = conf.getOAuthConsumerSecret();
            // try to find oauth tokens in the configuration
            if (consumerKey != null && consumerSecret != null) {
                OAuthAuthorization oauth = new OAuthAuthorization(conf);
                String accessToken = conf.getOAuthAccessToken();
                String accessTokenSecret = conf.getOAuthAccessTokenSecret();
                if (accessToken != null && accessTokenSecret != null) {
                    oauth.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
                }
                this.auth = oauth;
            } else {
                this.auth = NullAuthorization.getInstance();
            }
        }
        http = new HttpClientWrapper(conf);
        http.setHttpResponseListener(this);
    }



    @Override
    public void httpResponseReceived(HttpResponseEvent event) {
        if (rateLimitStatusListeners.size() != 0) {
            HttpResponse res = event.getResponse();
            TwitterException te = event.getTwitterException();
            RateLimitStatus rateLimitStatus;
            int statusCode;
            if (te != null) {
                rateLimitStatus = te.getRateLimitStatus();
                statusCode = te.getStatusCode();
            } else {
                rateLimitStatus = z_T4JInternalJSONImplFactory.createRateLimitStatusFromResponseHeader(res);
                statusCode = res.getStatusCode();
            }
            if (rateLimitStatus != null) {
                RateLimitStatusEvent statusEvent = new RateLimitStatusEvent(this, rateLimitStatus, event.isAuthenticated());
                if (statusCode == ENHANCE_YOUR_CLAIM || statusCode == SERVICE_UNAVAILABLE) {
                    // EXCEEDED_RATE_LIMIT_QUOTA is returned by Rest API
                    // SERVICE_UNAVAILABLE is returned by Search API
                    for (RateLimitStatusListener listener : rateLimitStatusListeners) {
                        listener.onRateLimitStatus(statusEvent);
                        listener.onRateLimitReached(statusEvent);
                    }
                } else {
                    for (RateLimitStatusListener listener : rateLimitStatusListeners) {
                        listener.onRateLimitStatus(statusEvent);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Authorization getAuthorization() {
        return auth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration getConfiguration() {
        return this.conf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {
        if (http != null) {
            http.shutdown();
        }
    }

    protected final void ensureAuthorizationEnabled() {
        if (!auth.isEnabled()) {
            throw new IllegalStateException("Authentication credentials are missing. See http://twitter4jads.org/configuration.html for the detail.");
        }
    }

    // methods declared in OAuthSupport interface

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setOAuthConsumer(String consumerKey, String consumerSecret) {
        if (null == consumerKey) {
            throw new NullPointerException("consumer key is null");
        }
        if (null == consumerSecret) {
            throw new NullPointerException("consumer secret is null");
        }
        if (auth instanceof NullAuthorization) {
            OAuthAuthorization oauth = new OAuthAuthorization(conf);
            oauth.setOAuthConsumer(consumerKey, consumerSecret);
            auth = oauth;
        } else if (auth instanceof BasicAuthorization) {
            XAuthAuthorization xauth = new XAuthAuthorization((BasicAuthorization) auth);
            xauth.setOAuthConsumer(consumerKey, consumerSecret);
            auth = xauth;
        } else if (auth instanceof OAuthAuthorization) {
            throw new IllegalStateException("consumer key/secret pair already set.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestToken getOAuthRequestToken() throws TwitterException {
        return getOAuthRequestToken(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestToken getOAuthRequestToken(String callbackUrl) throws TwitterException {
        return getOAuth().getOAuthRequestToken(callbackUrl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestToken getOAuthRequestToken(String callbackUrl, String xAuthAccessType) throws TwitterException {
        return getOAuth().getOAuthRequestToken(callbackUrl, xAuthAccessType);
    }

    /**
     * {@inheritDoc}
     * Basic authenticated instance of this class will try acquiring an AccessToken using xAuth.<br>
     * In order to get access acquire AccessToken using xAuth, you must apply by sending an email to <a href="mailto:api@twitter.com">api@twitter.com</a> all other applications will receive an HTTP 401 error.  Web-based applications will not be granted access, except on a temporary basis for when they are converting from basic-authentication support to full OAuth support.<br>
     * Storage of Twitter usernames and passwords is forbidden. By using xAuth, you are required to store only access tokens and access token secrets. If the access token expires or is expunged by a user, you must ask for their login and password again before exchanging the credentials for an access token.
     *
     * @throws TwitterException When Twitter service or network is unavailable, when the user has not authorized, or when the client application is not permitted to use xAuth
     * @see <a href="https://dev.twitter.com/docs/oauth/xauth">xAuth | Twitter Developers</a>
     */
    @Override
    public synchronized AccessToken getOAuthAccessToken() throws TwitterException {
        Authorization auth = getAuthorization();
        AccessToken oauthAccessToken;
        if (auth instanceof BasicAuthorization) {
            BasicAuthorization basicAuth = (BasicAuthorization) auth;
            auth = AuthorizationFactory.getInstance(conf);
            if (auth instanceof OAuthAuthorization) {
                this.auth = auth;
                OAuthAuthorization oauthAuth = (OAuthAuthorization) auth;
                oauthAccessToken = oauthAuth.getOAuthAccessToken(basicAuth.getUserId(), basicAuth.getPassword());
            } else {
                throw new IllegalStateException("consumer key / secret combination not supplied.");
            }
        } else {
            if (auth instanceof XAuthAuthorization) {
                XAuthAuthorization xauth = (XAuthAuthorization) auth;
                this.auth = xauth;
                OAuthAuthorization oauthAuth = new OAuthAuthorization(conf);
                oauthAuth.setOAuthConsumer(xauth.getConsumerKey(), xauth.getConsumerSecret());
                oauthAccessToken = oauthAuth.getOAuthAccessToken(xauth.getUserId(), xauth.getPassword());
            } else {
                oauthAccessToken = getOAuth().getOAuthAccessToken();
            }
        }
        screenName = oauthAccessToken.getScreenName();
        id = oauthAccessToken.getUserId();
        return oauthAccessToken;
    }


    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when AccessToken has already been retrieved or set
     */
    @Override
    public synchronized AccessToken getOAuthAccessToken(String oauthVerifier) throws TwitterException {
        AccessToken oauthAccessToken = getOAuth().getOAuthAccessToken(oauthVerifier);
        screenName = oauthAccessToken.getScreenName();
        return oauthAccessToken;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when AccessToken has already been retrieved or set
     */
    @Override
    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken) throws TwitterException {
        OAuthSupport oauth = getOAuth();
        AccessToken oauthAccessToken = oauth.getOAuthAccessToken(requestToken);
        screenName = oauthAccessToken.getScreenName();
        return oauthAccessToken;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when AccessToken has already been retrieved or set
     */
    @Override
    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken, String oauthVerifier) throws TwitterException {
        return getOAuth().getOAuthAccessToken(requestToken, oauthVerifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setOAuthAccessToken(AccessToken accessToken) {
        getOAuth().setOAuthAccessToken(accessToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized AccessToken getOAuthAccessToken(String screenName, String password) throws TwitterException {
        return getOAuth().getOAuthAccessToken(screenName, password);
    }

    /* OAuth support methods */

    private OAuthSupport getOAuth() {
        if (!(auth instanceof OAuthSupport)) {
            throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
        }
        return (OAuthSupport) auth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TwitterBaseImpl)) {
            return false;
        }

        TwitterBaseImpl that = (TwitterBaseImpl) o;

        if (auth != null ? !auth.equals(that.auth) : that.auth != null) {
            return false;
        }
        if (!conf.equals(that.conf)) {
            return false;
        }
        if (http != null ? !http.equals(that.http) : that.http != null) {
            return false;
        }
        if (!rateLimitStatusListeners.equals(that.rateLimitStatusListeners)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = conf.hashCode();
        result = 31 * result + (http != null ? http.hashCode() : 0);
        result = 31 * result + rateLimitStatusListeners.hashCode();
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        return result;
    }

    protected HttpResponse get(String url) throws TwitterException {
        ensureAuthorizationEnabled();
        if (url.contains("?")) {
            url = url + "&" + getImplicitParamsStr();
        } else {
            url = url + "?" + getImplicitParamsStr();
        }
        if (!conf.isMBeanEnabled()) {
            return http.get(url, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                TwitterAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    protected HttpResponse get(String url, HttpParameter[] params) throws TwitterException {
        ensureAuthorizationEnabled();
        if (!conf.isMBeanEnabled()) {
            return http.get(url, mergeImplicitParams(params), auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url, mergeImplicitParams(params), auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                TwitterAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    protected HttpResponse post(String url, HttpParameter[] params) throws TwitterException {
        ensureAuthorizationEnabled();
        if (!conf.isMBeanEnabled()) {
            return http.post(url, mergeImplicitParams(params), auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.post(url, mergeImplicitParams(params), auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                TwitterAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    protected HttpParameter[] mergeParameters(HttpParameter[] params1, HttpParameter[] params2) {
        if (params1 != null && params2 != null) {
            HttpParameter[] params = new HttpParameter[params1.length + params2.length];
            System.arraycopy(params1, 0, params, 0, params1.length);
            System.arraycopy(params2, 0, params, params1.length, params2.length);
            return params;
        }
        if (null == params1 && null == params2) {
            return new HttpParameter[0];
        }
        if (params1 != null) {
            return params1;
        } else {
            return params2;
        }
    }

    protected HttpParameter[] mergeImplicitParams(HttpParameter[] params) {
        return mergeParameters(params, getImplicitParams());
    }

    private boolean isOk(HttpResponse response) {
        return response != null && response.getStatusCode() < 300;
    }

    abstract String getImplicitParamsStr();

    abstract HttpParameter[] getImplicitParams();
}
