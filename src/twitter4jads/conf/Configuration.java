package twitter4jads.conf;

import twitter4jads.auth.AuthorizationConfiguration;
import twitter4jads.internal.http.HttpClientConfiguration;
import twitter4jads.internal.http.HttpClientWrapperConfiguration;

import java.util.Map;
import java.util.Properties;


public interface Configuration extends HttpClientConfiguration, HttpClientWrapperConfiguration, AuthorizationConfiguration, java.io.Serializable {

    boolean isDalvik();

    boolean isGAE();

    boolean isDebugEnabled();

    boolean isSandboxEnabledForAds();

    String getUserAgent();

    String getUser();

    String getPassword();

    Map<String, String> getRequestHeaders();

    // methods for HttpClientConfiguration

    String getHttpProxyHost();

    String getHttpProxyUser();

    String getHttpProxyPassword();

    int getHttpProxyPort();

    int getHttpConnectionTimeout();

    int getHttpReadTimeout();

    int getHttpStreamingReadTimeout();

    int getHttpRetryCount();

    int getHttpRetryIntervalSeconds();

    int getHttpMaxTotalConnections();

    int getHttpDefaultMaxPerRoute();

    // oauth related setter/getters

    String getOAuthConsumerKey();

    String getOAuthConsumerSecret();

    String getOAuthAccessToken();

    String getOAuthAccessTokenSecret();

    String getClientVersion();

    String getClientURL();

    String getRestBaseURL();

    String getAdsAPIURL();

    String getEngagementApiUrl();

    String getMediaUploadBaseUrl();

    String getScopedTimeLineBaseUrl();

    String getStreamBaseURL();

    String getOAuthRequestTokenURL();

    String getOAuthAuthorizationURL();

    String getOAuthAccessTokenURL();

    String getOAuthAuthenticationURL();

    String getUserStreamBaseURL();

    String getSiteStreamBaseURL();

    String getVideoBaseUrl();

    String getVideoImageBaseUrl();

    boolean isIncludeMyRetweetEnabled();

    String getTweetMode();

    boolean isJSONStoreEnabled();

    boolean isMBeanEnabled();

    boolean isUserStreamRepliesAllEnabled();

    boolean isStallWarningsEnabled();

    String getMediaProvider();

    String getMediaProviderAPIKey();

    String getTwitterTonBaseUrl();

    Properties getMediaProviderParameters();

    String getVideoTweetBaseUrl();

    int getAsyncNumThreads();

    long getContributingTo();

    String getDispatcherImpl();

    String getLoggerFactory();

    boolean isIncludeRTsEnabled();

    boolean isIncludeEntitiesEnabled();
}
