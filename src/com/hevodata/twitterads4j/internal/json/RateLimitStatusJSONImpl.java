package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.RateLimitStatus;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getInt;

/**
 * A data class representing Twitter REST API's rate limit status
 *
 *
 * @see <a href="https://dev.twitter.com/docs/rate-limiting">Rate Limiting | Twitter Developers</a>
 */
/*package*/ final class RateLimitStatusJSONImpl implements RateLimitStatus, java.io.Serializable {

    private static final long serialVersionUID = 1625565652687304084L;
    private int remaining;
    private int limit;
    private int resetTimeInSeconds;
    private int secondsUntilReset;
    static Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse res, Configuration conf) throws TwitterException {
        JSONObject json = res.asJSONObject();
        Map<String, RateLimitStatus> map = createRateLimitStatuses(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(map, json);
        }
        return map;
    }

    static Map<String, RateLimitStatus> createRateLimitStatuses(JSONObject json) throws TwitterException {
        Map<String, RateLimitStatus> map = new HashMap<String, RateLimitStatus>();
        try {
            JSONObject resources = json.getJSONObject("resources");
            Iterator resourceKeys = resources.keys();
            while (resourceKeys.hasNext()) {
                JSONObject resource = resources.getJSONObject((String) resourceKeys.next());
                Iterator endpointKeys = resource.keys();
                while (endpointKeys.hasNext()) {
                    String endpoint = (String) endpointKeys.next();
                    JSONObject rateLimitStatusJSON = resource.getJSONObject(endpoint);
                    RateLimitStatus rateLimitStatus = new RateLimitStatusJSONImpl(rateLimitStatusJSON);
                    map.put(endpoint, rateLimitStatus);
                }
            }
            return Collections.unmodifiableMap(map);
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    private RateLimitStatusJSONImpl(int limit, int remaining, int resetTimeInSeconds) {
        this.limit = limit;
        this.remaining = remaining;
        this.resetTimeInSeconds = resetTimeInSeconds;
        this.secondsUntilReset = (int) ((resetTimeInSeconds * 1000L - System.currentTimeMillis()) / 1000);
    }

    RateLimitStatusJSONImpl(JSONObject json) throws TwitterException {
        init(json);
    }

    void init(JSONObject json) throws TwitterException {
        this.limit = z_T4JInternalParseUtil.getInt("limit", json);
        this.remaining = z_T4JInternalParseUtil.getInt("remaining", json);
        this.resetTimeInSeconds = z_T4JInternalParseUtil.getInt("reset", json);
        this.secondsUntilReset = (int) ((resetTimeInSeconds * 1000L - System.currentTimeMillis()) / 1000);
    }

    static RateLimitStatus createFromResponseHeader(HttpResponse res) {
        if (null == res) {
            return null;
        }
        int remainingHits;//"X-Rate-Limit-Remaining"
        int limit;//"X-Rate-Limit-Limit"
        int resetTimeInSeconds;//not included in the response header. Need to be calculated.

        String strLimit = res.getResponseHeader("X-Rate-Limit-Limit");
        if (strLimit != null) {
            limit = Integer.parseInt(strLimit);
        } else {
            return null;
        }
        String remaining = res.getResponseHeader("X-Rate-Limit-Remaining");
        if (remaining != null) {
            remainingHits = Integer.parseInt(remaining);
        } else {
            return null;
        }
        String reset = res.getResponseHeader("X-Rate-Limit-Reset");
        if (reset != null) {
            long longReset = Long.parseLong(reset);
            resetTimeInSeconds = (int) longReset;
        } else {
            return null;
        }
        return new RateLimitStatusJSONImpl(limit, remainingHits, resetTimeInSeconds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemaining() {
        return remaining;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemainingHits() {
        return getRemaining();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLimit() {
        return limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResetTimeInSeconds() {
        return resetTimeInSeconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSecondsUntilReset() {
        return secondsUntilReset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateLimitStatusJSONImpl that = (RateLimitStatusJSONImpl) o;

        if (limit != that.limit) return false;
        if (remaining != that.remaining) return false;
        if (resetTimeInSeconds != that.resetTimeInSeconds) return false;
        if (secondsUntilReset != that.secondsUntilReset) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = remaining;
        result = 31 * result + limit;
        result = 31 * result + resetTimeInSeconds;
        result = 31 * result + secondsUntilReset;
        return result;
    }

    @Override
    public String toString() {
        return "RateLimitStatusJSONImpl{" +
                "remaining=" + remaining +
                ", limit=" + limit +
                ", resetTimeInSeconds=" + resetTimeInSeconds +
                ", secondsUntilReset=" + secondsUntilReset +
                '}';
    }

}
