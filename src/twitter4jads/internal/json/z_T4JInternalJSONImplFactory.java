package twitter4jads.internal.json;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.*;
import twitter4jads.internal.org.json.JSONArray;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.util.z_T4JInternalStringUtil;

import java.util.Map;

/**
 *
 * @since Twitter4J 2.2.4
 */
public class z_T4JInternalJSONImplFactory implements z_T4JInternalFactory {
    private static final long serialVersionUID = 5217622295050444866L;
    private Configuration conf;

    public z_T4JInternalJSONImplFactory(Configuration conf) {
        this.conf = conf;
    }

    /**
     * returns a GeoLocation instance if a "geo" element is found.
     *
     * @param json JSONObject to be parsed
     * @return GeoLocation instance
     * @throws TwitterException when coordinates is not included in geo element (should be an API side issue)
     */
    /*package*/
    static GeoLocation createGeoLocation(JSONObject json) throws TwitterException {
        try {
            if (!json.isNull("geo")) {
                String coordinates = json.getJSONObject("geo").getString("coordinates");
                coordinates = coordinates.substring(1, coordinates.length() - 1);
                String[] point = z_T4JInternalStringUtil.split(coordinates, ",");
                return new GeoLocation(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
            }
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
        return null;
    }

    /*package*/
    static GeoLocation[][] coordinatesAsGeoLocationArray(JSONArray coordinates) throws TwitterException {
        try {
            GeoLocation[][] boundingBox = new GeoLocation[coordinates.length()][];
            for (int i = 0; i < coordinates.length(); i++) {
                JSONArray array = coordinates.getJSONArray(i);
                boundingBox[i] = new GeoLocation[array.length()];
                for (int j = 0; j < array.length(); j++) {
                    JSONArray coordinate = array.getJSONArray(j);
                    boundingBox[i][j] = new GeoLocation(coordinate.getDouble(1), coordinate.getDouble(0));
                }
            }
            return boundingBox;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    public static RateLimitStatus createRateLimitStatusFromResponseHeader(HttpResponse res) {
        return RateLimitStatusJSONImpl.createFromResponseHeader(res);
    }
    @Override
    public int hashCode() {
        return conf != null ? conf.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "JSONImplFactory{" +
               "conf=" + conf +
               '}';
    }
}
