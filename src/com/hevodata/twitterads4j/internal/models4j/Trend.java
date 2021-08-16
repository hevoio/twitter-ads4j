package com.hevodata.twitterads4j.internal.models4j;


/**
 * A data interface representing Trend.
 *
 *
 * @since Twitter4J 2.0.2
 */
public interface Trend extends java.io.Serializable {
    String getName();

    /**
     * @deprecated use {@link #getURL()} instead
     */
    String getUrl();

    String getURL();

    String getQuery();

    Integer getTweetVolume();

}
