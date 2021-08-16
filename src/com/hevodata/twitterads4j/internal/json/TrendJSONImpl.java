package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.JSONResponseImpl;
import com.hevodata.twitterads4j.internal.models4j.Trend;

import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getInt;
import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getRawString;

/**
 * A data class representing Trend.
 *
 *
 * @since Twitter4J 2.0.2
 */
public final class TrendJSONImpl extends JSONResponseImpl implements Trend, java.io.Serializable {
    private String name;
    private String url = null;
    private String query = null;
    private Integer tweetVolume = null;
    private static final long serialVersionUID = 1925956704460743946L;

    /*package*/ TrendJSONImpl(JSONObject json, boolean storeJSON) {
        super(json);
        this.name = getRawString("name", json);
        this.url = getRawString("url", json);
        this.query = getRawString("query", json);
        this.tweetVolume = getInt("tweet_volume", json);
        if (storeJSON) {
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/ TrendJSONImpl(JSONObject json) {
        this(json, false);
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
    public String getUrl() {
        return getURL();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getURL() {
        return url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public Integer getTweetVolume() {
        return tweetVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trend)) return false;

        Trend trend = (Trend) o;

        if (!name.equals(trend.getName())) return false;
        if (query != null ? !query.equals(trend.getQuery()) : trend.getQuery() != null)
            return false;
        if (url != null ? !url.equals(trend.getURL()) : trend.getURL() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (query != null ? query.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrendJSONImpl{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
