package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.internal.models4j.UserMentionEntity;

/**
 * A data interface representing one single user mention entity.
 *
 *
 * @since Twitter4J 2.1.9
 */
/*package*/
public class UserMentionEntityJSONImpl extends EntityIndex implements UserMentionEntity {
    private static final long serialVersionUID = 6580431141350059702L;
    private String name;
    private String screenName;
    private long id;

    /* package */ UserMentionEntityJSONImpl(JSONObject json) throws TwitterException {
        super();
        init(json);
    }

    /* package */ UserMentionEntityJSONImpl(int start, int end, String name, String screenName, long id) {
        super();
        setStart(start);
        setEnd(end);
        this.name = name;
        this.screenName = screenName;
        this.id = id;
    }

    /* For serialization purposes only. */
    /* package */ UserMentionEntityJSONImpl() {

    }

    private void init(JSONObject json) throws TwitterException {
        try {
            JSONArray indicesArray = json.getJSONArray("indices");
            setStart(indicesArray.getInt(0));
            setEnd(indicesArray.getInt(1));

            if (!json.isNull("name")) {
                this.name = json.getString("name");
            }
            if (!json.isNull("screen_name")) {
                this.screenName = json.getString("screen_name");
            }
            id = z_T4JInternalParseUtil.getLong("id", json);
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
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
    public String getScreenName() {
        return screenName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() {
        return id;
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

        UserMentionEntityJSONImpl that = (UserMentionEntityJSONImpl) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (screenName != null ? !screenName.equals(that.screenName) : that.screenName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserMentionEntityJSONImpl{" +
                "name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", id=" + id +
                '}';
    }
}
