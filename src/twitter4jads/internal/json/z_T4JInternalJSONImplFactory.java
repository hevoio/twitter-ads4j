package twitter4jads.internal.json;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.*;


/**
 *
 * @since Twitter4J 2.2.4
 */
public class z_T4JInternalJSONImplFactory {
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