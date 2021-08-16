package com.hevodata.twitterads4j.auth;

import com.hevodata.twitterads4j.internal.http.HttpRequest;

/**
 * An interface represents credentials.
 *
 *
 */
public interface Authorization extends java.io.Serializable {
    String getAuthorizationHeader(HttpRequest req);

    /**
     * Returns true if authorization credentials are set.
     *
     * @return true if authorization credentials are set
     */
    boolean isEnabled();
}