package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.Location;
import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.ResponseList;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

/**
 *
 */
public final class LocationJSONImpl extends TwitterResponseImpl implements Location {
    private final int woeid;
    private final String countryName;
    private final String countryCode;
    private final String placeName;
    private final int placeCode;
    private final String name;
    private final String url;
    private static final long serialVersionUID = 7095092358530897222L;

    public LocationJSONImpl(JSONObject location) throws TwitterException {
        super(location);
        try {
            woeid = z_T4JInternalParseUtil.getInt("woeid", location);
            countryName = z_T4JInternalParseUtil.getUnescapedString("country", location);
            countryCode = z_T4JInternalParseUtil.getRawString("countryCode", location);
            if (!location.isNull("placeType")) {
                JSONObject placeJSON = location.getJSONObject("placeType");
                placeName = z_T4JInternalParseUtil.getUnescapedString("name", placeJSON);
                placeCode = z_T4JInternalParseUtil.getInt("code", placeJSON);
            } else {
                placeName = null;
                placeCode = -1;
            }
            name = z_T4JInternalParseUtil.getUnescapedString("name", location);
            url = z_T4JInternalParseUtil.getUnescapedString("url", location);
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    /*package*/
    static ResponseList<Location> createLocationList(HttpResponse res, Configuration conf) throws TwitterException {
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        return createLocationList(res.asJSONArray(), conf.isJSONStoreEnabled());
    }

    /*package*/
    static ResponseList<Location> createLocationList(JSONArray list, boolean storeJSON) throws TwitterException {
        try {
            int size = list.length();
            ResponseList<Location> locations =
                    new ResponseListImpl<Location>(size, null);
            for (int i = 0; i < size; i++) {
                JSONObject json = list.getJSONObject(i);
                Location location = new LocationJSONImpl(json);
                locations.add(location);
                if (storeJSON) {
                    DataObjectFactoryUtil.registerJSONObject(location, json);
                }
            }
            if (storeJSON) {
                DataObjectFactoryUtil.registerJSONObject(locations, list);
            }
            return locations;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        } catch (TwitterException te) {
            throw te;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWoeid() {
        return woeid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCountryName() {
        return countryName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlaceName() {
        return placeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlaceCode() {
        return placeCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getURL() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationJSONImpl)) return false;

        LocationJSONImpl that = (LocationJSONImpl) o;

        if (woeid != that.woeid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return woeid;
    }

    @Override
    public String toString() {
        return "LocationJSONImpl{" +
                "woeid=" + woeid +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
