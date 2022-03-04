package twitter4jads.internal.json;

import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.models4j.TwitterResponse;

/**
 * A tiny parse utility class.
 *
 *
 */
public final class z_T4JInternalParseUtil {
    private z_T4JInternalParseUtil() {
        // should never be instantiated
        throw new AssertionError();
    }

    public static String getRawString(String name, JSONObject json) {
        try {
            if (json.isNull(name)) {
                return null;
            } else {
                return json.getString(name);
            }
        } catch (JSONException jsone) {
            return null;
        }
    }

    public static int getInt(String name, JSONObject json) {
        return getInt(getRawString(name, json));
    }

    public static int getInt(String str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return -1;
        } else {
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException nfe) {
                // workaround for the API side issue http://twitter4jads.org/jira/browse/TFJ-484
                return -1;
            }
        }
    }

    public static int toAccessLevel(HttpResponse res) {
        if (null == res) {
            return -1;
        }
        String xAccessLevel = res.getResponseHeader("X-Access-Level");
        int accessLevel;
        if (null == xAccessLevel) {
            accessLevel = TwitterResponse.NONE;
        } else {
            // https://dev.twitter.com/pages/application-permission-model-faq#how-do-we-know-what-the-access-level-of-a-user-token-is
            switch (xAccessLevel.length()) {
                // \u0093read\u0094 (Read-only)
                case 4:
                    accessLevel = TwitterResponse.READ;
                    break;
                case 10:
                    // \u0093read-write\u0094 (Read & Write)
                    accessLevel = TwitterResponse.READ_WRITE;
                    break;
                case 25:
                    // \u0093read-write-directmessages\u0094 (Read, Write, & Direct Message)
                    accessLevel = TwitterResponse.READ_WRITE_DIRECTMESSAGES;
                    break;
                case 26:
                    // \u0093read-write-privatemessages\u0094 (Read, Write, & Direct Message)
                    accessLevel = TwitterResponse.READ_WRITE_DIRECTMESSAGES;
                    break;
                default:
                    accessLevel = TwitterResponse.NONE;
                    // unknown access level;
            }
        }
        return accessLevel;
    }
}
