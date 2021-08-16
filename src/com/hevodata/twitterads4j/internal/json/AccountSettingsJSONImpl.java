package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.Location;
import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.AccountSettings;
import com.hevodata.twitterads4j.internal.models4j.TimeZone;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

/**
 *
 * @since Twitter4J 2.1.9
 */
class AccountSettingsJSONImpl extends TwitterResponseImpl implements AccountSettings, java.io.Serializable {
    private static final long serialVersionUID = 7983363611306383416L;
    private final boolean SLEEP_TIME_ENABLED;
    private final String SLEEP_START_TIME;
    private final String SLEEP_END_TIME;
    private final Location[] TREND_LOCATION;
    private final boolean GEO_ENABLED;
    private final String LANGUAGE;
    private final TimeZone TIMEZONE;
    private final boolean ALWAYS_USE_HTTPS;
    private final boolean DISCOVERABLE_BY_EMAIL;

    private AccountSettingsJSONImpl(HttpResponse res, JSONObject json) throws TwitterException {
        super(res);
        try {
            JSONObject sleepTime = json.getJSONObject("sleep_time");
            SLEEP_TIME_ENABLED = z_T4JInternalParseUtil.getBoolean("enabled", sleepTime);
            SLEEP_START_TIME = sleepTime.getString("start_time");
            SLEEP_END_TIME = sleepTime.getString("end_time");
            if (json.isNull("trend_location")) {
                TREND_LOCATION = new Location[0];
            } else {
                JSONArray locations = json.getJSONArray("trend_location");
                TREND_LOCATION = new Location[locations.length()];
                for (int i = 0; i < locations.length(); i++) {
                    TREND_LOCATION[i] = new LocationJSONImpl(locations.getJSONObject(i));
                }
            }
            GEO_ENABLED = z_T4JInternalParseUtil.getBoolean("geo_enabled", json);
            LANGUAGE = json.getString("language");
            ALWAYS_USE_HTTPS = z_T4JInternalParseUtil.getBoolean("always_use_https", json);
            DISCOVERABLE_BY_EMAIL = z_T4JInternalParseUtil.getBoolean("discoverable_by_email", json);
            TIMEZONE = new TimeZoneJSONImpl(json.getJSONObject("time_zone"));
        } catch (JSONException e) {
            throw new TwitterException(e);
        }
    }

    /*package*/ AccountSettingsJSONImpl(HttpResponse res, Configuration conf) throws TwitterException {
        this(res, res.asJSONObject());
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, res.asJSONObject());
        }
    }

    /*package*/ AccountSettingsJSONImpl(JSONObject json) throws TwitterException {
        this(null, json);
    }

    @Override
    public boolean isSleepTimeEnabled() {
        return SLEEP_TIME_ENABLED;
    }

    @Override
    public String getSleepStartTime() {
        return SLEEP_START_TIME;
    }

    @Override
    public String getSleepEndTime() {
        return SLEEP_END_TIME;
    }

    @Override
    public Location[] getTrendLocations() {
        return TREND_LOCATION;
    }

    @Override
    public boolean isGeoEnabled() {
        return GEO_ENABLED;
    }

    @Override
    public boolean isDiscoverableByEmail() {
        return DISCOVERABLE_BY_EMAIL;
    }

    @Override
    public boolean isAlwaysUseHttps() {
        return ALWAYS_USE_HTTPS;
    }

    @Override
    public String getLanguage() {
        return LANGUAGE;
    }

    @Override
    public TimeZone getTimeZone() {
        return TIMEZONE;
    }
}
