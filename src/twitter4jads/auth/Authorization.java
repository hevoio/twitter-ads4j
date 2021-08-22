package twitter4jads.auth;

import twitter4jads.internal.http.HttpRequest;

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