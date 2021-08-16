package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.RateLimitStatus;
import com.hevodata.twitterads4j.internal.models4j.ResponseList;

import java.util.ArrayList;

class ResponseListImpl<T> extends ArrayList<T> implements ResponseList<T> {
    private transient RateLimitStatus rateLimitStatus = null;
    private transient int accessLevel;
    private static final long serialVersionUID = 5646617841989265312L;

    ResponseListImpl(HttpResponse res) {
        super();
        init(res);
    }

    ResponseListImpl(int size, HttpResponse res) {
        super(size);
        init(res);
    }

    ResponseListImpl(RateLimitStatus rateLimitStatus, int accessLevel) {
        super();
        this.rateLimitStatus = rateLimitStatus;
        this.accessLevel = accessLevel;
    }

    private void init(HttpResponse res) {
        this.rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(res);
        accessLevel = z_T4JInternalParseUtil.toAccessLevel(res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RateLimitStatus getRateLimitStatus() {
        return rateLimitStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAccessLevel() {
        return accessLevel;
    }
}
