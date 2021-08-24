package twitter4jads.auth;

import twitter4jads.internal.http.HttpRequest;

import java.io.ObjectStreamException;

/**
 * An interface represents credentials.
 *
 *
 */
public class NullAuthorization implements Authorization, java.io.Serializable {
    private static NullAuthorization SINGLETON = new NullAuthorization();
    private static final long serialVersionUID = -8748173338942663960L;

    private NullAuthorization() {

    }

    public static NullAuthorization getInstance() {
        return SINGLETON;
    }

    public String getAuthorizationHeader(HttpRequest req) {
        return null;
    }

    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return SINGLETON == o;
    }

    @Override
    public String toString() {
        return "NullAuthentication{SINGLETON}";
    }

    private Object readResolve() throws ObjectStreamException {
        return SINGLETON;
    }

}
