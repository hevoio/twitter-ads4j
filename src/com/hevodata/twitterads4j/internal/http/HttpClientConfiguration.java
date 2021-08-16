package com.hevodata.twitterads4j.internal.http;

/**
 *
 */
public interface HttpClientConfiguration {

    String getHttpProxyHost();

    int getHttpProxyPort();

    String getHttpProxyUser();

    String getHttpProxyPassword();

    int getHttpConnectionTimeout();

    int getHttpReadTimeout();

    int getHttpRetryCount();

    int getHttpRetryIntervalSeconds();

    int getHttpMaxTotalConnections();

    int getHttpDefaultMaxPerRoute();

    boolean isPrettyDebugEnabled();

    boolean isGZIPEnabled();
}
