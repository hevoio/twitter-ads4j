package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.TimeZone;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getInt;

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
