package twitter4jads.auth;

import twitter4jads.internal.http.BASE64Encoder;
import twitter4jads.internal.http.HttpRequest;

/**
 * An authentication implementation implements Basic authentication
 *
 *
 */
public class BasicAuthorization implements Authorization, java.io.Serializable {

    private String userId;

    private String password;
    private String basic;
    private static final long serialVersionUID = -5861104407848415060L;

    public BasicAuthorization(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.basic = encodeBasicAuthenticationString();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    private String encodeBasicAuthenticationString() {
        if (userId != null && password != null) {
            return "Basic " + BASE64Encoder.encode((userId + ":" + password).getBytes());
        }
        return null;
    }

    public String getAuthorizationHeader(HttpRequest req) {
        return basic;
    }

    /**
     * #{inheritDoc}
     */
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicAuthorization)) return false;

        BasicAuthorization that = (BasicAuthorization) o;

        return basic.equals(that.basic);

    }

    @Override
    public int hashCode() {
        return basic.hashCode();
    }

    @Override
    public String toString() {
        return "BasicAuthorization{" +
                "userId='" + userId + '\'' +
                ", password='**********'\'" +
                '}';
    }

}
