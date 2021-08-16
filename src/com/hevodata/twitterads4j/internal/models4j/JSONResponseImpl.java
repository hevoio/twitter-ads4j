package com.hevodata.twitterads4j.internal.models4j;

import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;

/**
 * An abstract base class for all objects based on a JSON response.
 */
public abstract class JSONResponseImpl implements JSONResponse {

    private transient JSONObject json;
    private static final long serialVersionUID = 1608000492860584608L;

    public JSONResponseImpl() {
    }

    public JSONResponseImpl(HttpResponse res) throws TwitterException {
        if (res != null) {
            this.json = res.asJSONObject();
        }
    }

    public JSONResponseImpl(JSONObject json) {
        this.json = json;
    }

    public JSONObject getJSONObject() {
        return json;
    }

    @Override
    public String getRawJSON() {
        if (json == null) {
            return null;
        }
        return json.toString();
    }
}
