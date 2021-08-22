package twitter4jads.internal.json;

import twitter4jads.internal.org.json.JSONArray;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.models4j.URLEntity;

/**
 * A data class representing one single URL entity.
 *
 *
 */
/* package */ final class URLEntityJSONImpl extends EntityIndex implements URLEntity {

    private static final long serialVersionUID = -8948472760821379376L;
    private String url;
    private String expandedURL;
    private String displayURL;

    /* package */ URLEntityJSONImpl(JSONObject json) throws TwitterException {
        super();
        init(json);
    }

    /* package */ URLEntityJSONImpl(int start, int end, String url, String expandedURL, String displayURL) {
        super();
        setStart(start);
        setEnd(end);
        this.url = url;
        this.expandedURL = expandedURL;
        this.displayURL = displayURL;
    }

    /* For serialization purposes only. */
    /* package */ URLEntityJSONImpl() {

    }

    private void init(JSONObject json) throws TwitterException {
        try {
            JSONArray indicesArray = json.getJSONArray("indices");
            setStart(indicesArray.getInt(0));
            setEnd(indicesArray.getInt(1));

            this.url = json.getString("url");
            if (!json.isNull("expanded_url")) {
                // sets expandedURL to url if expanded_url is null
                // http://jira.twitter4jads.org/browse/TFJ-704
                this.expandedURL = json.getString("expanded_url");
            }else{
                this.expandedURL = url;
            }

            if (!json.isNull("display_url")) {
                // sets displayURL to url if expanded_url is null
                // http://jira.twitter4jads.org/browse/TFJ-704
                this.displayURL = json.getString("display_url");
            }else{
                this.displayURL = url;
            }
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
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
    public String getExpandedURL() {
        return expandedURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayURL() {
        return displayURL;
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

        URLEntityJSONImpl that = (URLEntityJSONImpl) o;

        if (displayURL != null ? !displayURL.equals(that.displayURL) : that.displayURL != null) return false;
        if (expandedURL != null ? !expandedURL.equals(that.expandedURL) : that.expandedURL != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (expandedURL != null ? expandedURL.hashCode() : 0);
        result = 31 * result + (displayURL != null ? displayURL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "URLEntityJSONImpl{" +
                "url='" + url + '\'' +
                ", expandedURL='" + expandedURL + '\'' +
                ", displayURL='" + displayURL + '\'' +
                '}';
    }
}
