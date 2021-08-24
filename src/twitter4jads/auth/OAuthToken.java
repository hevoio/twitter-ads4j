package twitter4jads.auth;

import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.util.z_T4JInternalStringUtil;

import javax.crypto.spec.SecretKeySpec;

abstract class OAuthToken implements java.io.Serializable {

    private static final long serialVersionUID = 3891133932519746686L;
    private String token;
    private String tokenSecret;

    private transient SecretKeySpec secretKeySpec;
    String[] responseStr = null;

    public OAuthToken(String token, String tokenSecret) {
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    OAuthToken(HttpResponse response) throws TwitterException {
        this(response.asString());
    }

    OAuthToken(String string) {
        responseStr = z_T4JInternalStringUtil.split(string, "&");
        tokenSecret = getParameter("oauth_token_secret");
        token = getParameter("oauth_token");
    }

    public String getToken() {
        return token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    /*package*/ void setSecretKeySpec(SecretKeySpec secretKeySpec) {
        this.secretKeySpec = secretKeySpec;
    }

    /*package*/ SecretKeySpec getSecretKeySpec() {
        return secretKeySpec;
    }

    public String getParameter(String parameter) {
        String value = null;
        for (String str : responseStr) {
            if (str.startsWith(parameter + '=')) {
                value = z_T4JInternalStringUtil.split(str, "=")[1].trim();
                break;
            }
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuthToken)) return false;

        OAuthToken that = (OAuthToken) o;

        if (!token.equals(that.token)) return false;
        if (!tokenSecret.equals(that.tokenSecret)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + tokenSecret.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OAuthToken{" +
                "token='" + token + '\'' +
                ", tokenSecret='" + tokenSecret + '\'' +
                ", secretKeySpec=" + secretKeySpec +
                '}';
    }
}
