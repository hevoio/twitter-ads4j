package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.GeoLocation;
import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.Place;
import com.hevodata.twitterads4j.internal.models4j.ResponseList;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

import java.util.Arrays;

final class PlaceJSONImpl extends TwitterResponseImpl implements Place, java.io.Serializable {
    private String name;
    private String streetAddress;
    private String countryCode;
    private String id;
    private String country;
    private String placeType;
    private String url;
    private String fullName;
    private String boundingBoxType;
    private GeoLocation[][] boundingBoxCoordinates;
    private String geometryType;
    private GeoLocation[][] geometryCoordinates;
    private Place[] containedWithIn;
    private static final long serialVersionUID = -2873364341474633812L;

    /*package*/ PlaceJSONImpl(HttpResponse res, Configuration conf) throws TwitterException {
        super(res, res.asJSONObject());
        init(getJSONObject());
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, getJSONObject());
        }
    }

    PlaceJSONImpl(JSONObject json, HttpResponse res) throws TwitterException {
        super(res, json);
        init(json);
    }

    PlaceJSONImpl(JSONObject json) throws TwitterException {
        super(json);
        init(json);
    }

    /* For serialization purposes only. */
    PlaceJSONImpl() {

    }

    private void init(JSONObject json) throws TwitterException {
        try {
            name = z_T4JInternalParseUtil.getUnescapedString("name", json);
            streetAddress = z_T4JInternalParseUtil.getUnescapedString("street_address", json);
            countryCode = z_T4JInternalParseUtil.getRawString("country_code", json);
            id = z_T4JInternalParseUtil.getRawString("id", json);
            country = z_T4JInternalParseUtil.getRawString("country", json);
            if (!json.isNull("place_type")) {
                placeType = z_T4JInternalParseUtil.getRawString("place_type", json);
            } else {
                placeType = z_T4JInternalParseUtil.getRawString("type", json);
            }
            url = z_T4JInternalParseUtil.getRawString("url", json);
            fullName = z_T4JInternalParseUtil.getRawString("full_name", json);
            if (!json.isNull("bounding_box")) {
                JSONObject boundingBoxJSON = json.getJSONObject("bounding_box");
                boundingBoxType = z_T4JInternalParseUtil.getRawString("type", boundingBoxJSON);
                JSONArray array = boundingBoxJSON.getJSONArray("coordinates");
                boundingBoxCoordinates = z_T4JInternalJSONImplFactory.coordinatesAsGeoLocationArray(array);
            } else {
                boundingBoxType = null;
                boundingBoxCoordinates = null;
            }

            if (!json.isNull("geometry")) {
                JSONObject geometryJSON = json.getJSONObject("geometry");
                geometryType = z_T4JInternalParseUtil.getRawString("type", geometryJSON);
                JSONArray array = geometryJSON.getJSONArray("coordinates");
                if (geometryType.equals("Point")) {
                    geometryCoordinates = new GeoLocation[1][1];
                    geometryCoordinates[0][0] = new GeoLocation(array.getDouble(0), array.getDouble(1));
                } else if (geometryType.equals("Polygon")) {
                    geometryCoordinates = z_T4JInternalJSONImplFactory.coordinatesAsGeoLocationArray(array);
                } else {
                    // MultiPolygon currently unsupported.
                    geometryType = null;
                    geometryCoordinates = null;
                }
            } else {
                geometryType = null;
                geometryCoordinates = null;
            }

            if (!json.isNull("contained_within")) {
                JSONArray containedWithInJSON = json.getJSONArray("contained_within");
                containedWithIn = new Place[containedWithInJSON.length()];
                for (int i = 0; i < containedWithInJSON.length(); i++) {
                    containedWithIn[i] = new PlaceJSONImpl(containedWithInJSON.getJSONObject(i));
                }
            } else {
                containedWithIn = null;
            }
        } catch (JSONException jsone) {
            throw new TwitterException(jsone.getMessage() + ":" + json.toString(), jsone);
        }
    }

    @Override
    public int compareTo(Place that) {
        return this.id.compareTo(that.getId());
    }

    /*package*/
    static ResponseList<Place> createPlaceList(HttpResponse res, Configuration conf) throws TwitterException {
        JSONObject json = null;
        try {
            json = res.asJSONObject();
            return createPlaceList(json.getJSONObject("result").getJSONArray("places"), res, conf);
        } catch (JSONException jsone) {
            throw new TwitterException(jsone.getMessage() + ":" + json.toString(), jsone);
        }
    }

    /*package*/
    static ResponseList<Place> createPlaceList(JSONArray list, HttpResponse res
            , Configuration conf) throws TwitterException {
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        try {
            int size = list.length();
            ResponseList<Place> places =
                    new ResponseListImpl<Place>(size, res);
            for (int i = 0; i < size; i++) {
                JSONObject json = list.getJSONObject(i);
                Place place = new PlaceJSONImpl(json);
                places.add(place);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(place, json);
                }
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(places, list);
            }
            return places;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        } catch (TwitterException te) {
            throw te;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getPlaceType() {
        return placeType;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getBoundingBoxType() {
        return boundingBoxType;
    }

    @Override
    public GeoLocation[][] getBoundingBoxCoordinates() {
        return boundingBoxCoordinates;
    }

    @Override
    public String getGeometryType() {
        return geometryType;
    }

    @Override
    public GeoLocation[][] getGeometryCoordinates() {
        return geometryCoordinates;
    }

    @Override
    public Place[] getContainedWithIn() {
        return containedWithIn;
    }


    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj instanceof Place && ((Place) obj).getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "PlaceJSONImpl{" +
                "name='" + name + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", placeType='" + placeType + '\'' +
                ", url='" + url + '\'' +
                ", fullName='" + fullName + '\'' +
                ", boundingBoxType='" + boundingBoxType + '\'' +
                ", boundingBoxCoordinates=" + (boundingBoxCoordinates == null ? null : Arrays.asList(boundingBoxCoordinates)) +
                ", geometryType='" + geometryType + '\'' +
                ", geometryCoordinates=" + (geometryCoordinates == null ? null : Arrays.asList(geometryCoordinates)) +
                ", containedWithIn=" + (containedWithIn == null ? null : Arrays.asList(containedWithIn)) +
                '}';
    }
}
