package com.hevodata.twitterads4j.internal.models4j;

import com.hevodata.twitterads4j.internal.org.json.JSONObject;

/**
 * Super interface of JSON Response data interfaces
 */
public interface JSONResponse extends java.io.Serializable {
    /**
     * Returns the underlying JSON response object
     *
     * @return underlying JSON response
     * @since Twitter4J 3.0.3
     */
    JSONObject getJSONObject();

    /**
     * Json string representation of underlying object.
     *
     * @return json string representation of underlying object.
     */
    String getRawJSON();
}
