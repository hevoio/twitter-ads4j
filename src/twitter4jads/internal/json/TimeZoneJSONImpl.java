package twitter4jads.internal.json;

import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.models4j.TimeZone;
import twitter4jads.internal.models4j.TwitterException;

import static twitter4jads.internal.json.z_T4JInternalParseUtil.getInt;

public class TimeZoneJSONImpl implements TimeZone {
    private final String NAME;
    private final String TZINFO_NAME;
    private final int UTC_OFFSET;

    TimeZoneJSONImpl(JSONObject jSONObject) throws TwitterException {
        try {
            UTC_OFFSET = z_T4JInternalParseUtil.getInt("utc_offset", jSONObject);
            NAME = jSONObject.getString("name");
            TZINFO_NAME = jSONObject.getString("tzinfo_name");
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String tzinfoName() {
        return TZINFO_NAME;
    }

    @Override
    public int utcOffset() {
        return UTC_OFFSET;
    }

}
