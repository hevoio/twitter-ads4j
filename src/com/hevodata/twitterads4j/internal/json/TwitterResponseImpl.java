package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.JSONResponseImpl;
import com.hevodata.twitterads4j.internal.models4j.RateLimitStatus;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.internal.models4j.TwitterResponse;


/**
 * Super interface of Twitter Response data interfaces which indicates that rate limit status is available.
 *
 *
 */
/*package*/ abstract class TwitterResponseImpl extends JSONResponseImpl implements TwitterResponse, java.io.Serializable {

    private transient RateLimitStatus rateLimitStatus = null;
    private static final long serialVersionUID = -7284708239736552059L;
    private transient int accessLevel;

    public TwitterResponseImpl() {
        accessLevel = NONE;
    }

    public TwitterResponseImpl(HttpResponse res) throws TwitterException {
        super(res);
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

    public TwitterResponseImpl(HttpResponse res, JSONObject json) {
        super(json);
        this.rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(res);
    }

    public TwitterResponseImpl(JSONObject json) {
        super(json);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAccessLevel() {
        return accessLevel;
    }
}
