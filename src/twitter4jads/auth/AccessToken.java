package twitter4jads.auth;

import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.http.HttpResponse;

/**
 * Representing authorized Access Token which is passed to the service provider in order to access protected resources.<br>
 * the token and token secret can be stored into some persistent stores such as file system or RDBMS for the further accesses.
 *
 *
 */
public class AccessToken extends OAuthToken implements java.io.Serializable {
    private static final long serialVersionUID = -8344528374458826291L;
    private String screenName;
    private long userId = -1L;

    AccessToken(HttpResponse res) throws TwitterException {
        this(res.asString());
    }

    AccessToken(String str) {
        super(str);
        screenName = getParameter("screen_name");
        String sUserId = getParameter("user_id");
        if (sUserId != null) {
            userId = Long.parseLong(sUserId);
        }
    }

    public AccessToken(String token, String tokenSecret) {
        super(token, tokenSecret);
        String sUserId;
        int dashIndex = token.indexOf("-");
        if (dashIndex != -1) {
            sUserId = token.substring(0, dashIndex);
            try {
                userId = Long.parseLong(sUserId);
            } catch (NumberFormatException ignore) {
            }
        }
    }

    public AccessToken(String token, String tokenSecret, long userId) {
        super(token, tokenSecret);
        this.userId = userId;
    }

    /**
     * @return screen name
     * @since Twitter4J 2.0.4
     */

    public String getScreenName() {
        return screenName;
    }

    /**
     * @return user id
     * @since Twitter4J 2.0.4
     */

    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AccessToken that = (AccessToken) o;

        if (userId != that.userId) return false;
        if (screenName != null ? !screenName.equals(that.screenName) : that.screenName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "screenName='" + screenName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
