package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.HashtagEntity;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

/**
 * A data class representing one single Hashtag entity.
 *
 *
 */
/*package*/ class HashtagEntityJSONImpl extends EntityIndex implements HashtagEntity {
    private static final long serialVersionUID = 4068992372784813200L;
    private String text;


    /* package */ HashtagEntityJSONImpl(JSONObject json) throws TwitterException {
        super();
        init(json);
    }

    /* package */ HashtagEntityJSONImpl(int start, int end, String text) {
        super();
        setStart(start);
        setEnd(end);
        this.text = text;
    }

    /* For serialization purposes only. */
    /* package */ HashtagEntityJSONImpl() {

    }

    private void init(JSONObject json) throws TwitterException {
        try {
            JSONArray indicesArray = json.getJSONArray("indices");
            setStart(indicesArray.getInt(0));
            setEnd(indicesArray.getInt(1));

            if (!json.isNull("text")) {
                this.text = json.getString("text");
            }
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStart() {
        return super.getStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEnd() {
        return super.getEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashtagEntityJSONImpl that = (HashtagEntityJSONImpl) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HashtagEntityJSONImpl{" +
                "text='" + text + '\'' +
                '}';
    }
}