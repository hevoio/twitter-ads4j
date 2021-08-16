package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.PagableResponseList;
import com.hevodata.twitterads4j.internal.models4j.RateLimitStatus;

/**
 *
 * @since Twitter4J 2.1.3
 */
class PagableResponseListImpl<T> extends ResponseListImpl implements PagableResponseList {
    private final long previousCursor;
    private final long nextCursor;
    private static final long serialVersionUID = 1531950333538983361L;

    PagableResponseListImpl(RateLimitStatus rateLimitStatus, int accessLevel) {
        super(rateLimitStatus, accessLevel);
        previousCursor = 0;
        nextCursor = 0;
    }

    PagableResponseListImpl(int size, JSONObject json, HttpResponse res) {
        super(size, res);
        this.previousCursor = z_T4JInternalParseUtil.getLong("previous_cursor", json);
        this.nextCursor = z_T4JInternalParseUtil.getLong("next_cursor", json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPrevious() {
        return 0 != previousCursor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getPreviousCursor() {
        return previousCursor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return 0 != nextCursor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNextCursor() {
        return nextCursor;
    }

}
