package com.hevodata.twitterads4j.internal.models4j;

import java.util.List;


/**
 * List of TwitterResponse.
 *
 *
 */
public interface ResponseList<T> extends TwitterResponse, List<T> {

    /**
     * {@inheritDoc}
     */
    public RateLimitStatus getRateLimitStatus();
}
