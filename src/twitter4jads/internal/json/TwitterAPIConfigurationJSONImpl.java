package twitter4jads.internal.json;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.org.json.JSONArray;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.models4j.MediaEntity;
import twitter4jads.internal.models4j.TwitterAPIConfiguration;
import twitter4jads.internal.models4j.TwitterException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static twitter4jads.internal.json.z_T4JInternalParseUtil.getInt;

class TwitterAPIConfigurationJSONImpl extends TwitterResponseImpl implements TwitterAPIConfiguration {
    private static final long serialVersionUID = 5786291660087491465L;
    private int photoSizeLimit;
    private int shortURLLength;
    private int shortURLLengthHttps;

    private int charactersReservedPerMedia;
    private Map<Integer, MediaEntity.Size> photoSizes;
    private String[] nonUsernamePaths;
    private int maxMediaPerUpload;

    TwitterAPIConfigurationJSONImpl(HttpResponse res, Configuration conf)
            throws TwitterException {
        super(res);
        try {
            JSONObject json = res.asJSONObject();
            photoSizeLimit = z_T4JInternalParseUtil.getInt("photo_size_limit", json);
            shortURLLength = z_T4JInternalParseUtil.getInt("short_url_length", json);
            shortURLLengthHttps = z_T4JInternalParseUtil.getInt("short_url_length_https", json);
            charactersReservedPerMedia = z_T4JInternalParseUtil.getInt("characters_reserved_per_media", json);

            JSONObject sizes = json.getJSONObject("photo_sizes");
            photoSizes = new HashMap<Integer, MediaEntity.Size>(4);
            photoSizes.put(MediaEntity.Size.LARGE, new MediaEntityJSONImpl.Size(sizes.getJSONObject("large")));
            JSONObject medium;
            // http://code.google.com/p/twitter-api/issues/detail?id=2230
            if (sizes.isNull("med")) {
                medium = sizes.getJSONObject("medium");
            } else {
                medium = sizes.getJSONObject("med");
            }
            photoSizes.put(MediaEntity.Size.MEDIUM, new MediaEntityJSONImpl.Size(medium));
            photoSizes.put(MediaEntity.Size.SMALL, new MediaEntityJSONImpl.Size(sizes.getJSONObject("small")));
            photoSizes.put(MediaEntity.Size.THUMB, new MediaEntityJSONImpl.Size(sizes.getJSONObject("thumb")));
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
                DataObjectFactoryUtil.registerJSONObject(this, res.asJSONObject());
            }
            JSONArray nonUsernamePathsJSONArray = json.getJSONArray("non_username_paths");
            nonUsernamePaths = new String[nonUsernamePathsJSONArray.length()];
            for (int i = 0; i < nonUsernamePathsJSONArray.length(); i++) {
                nonUsernamePaths[i] = nonUsernamePathsJSONArray.getString(i);
            }
            maxMediaPerUpload = z_T4JInternalParseUtil.getInt("max_media_per_upload", json);
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPhotoSizeLimit() {
        return photoSizeLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShortURLLength() {
        return shortURLLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShortURLLengthHttps() {
        return shortURLLengthHttps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCharactersReservedPerMedia() {
        return charactersReservedPerMedia;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, MediaEntity.Size> getPhotoSizes() {
        return photoSizes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getNonUsernamePaths() {
        return nonUsernamePaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxMediaPerUpload() {
        return maxMediaPerUpload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TwitterAPIConfigurationJSONImpl)) return false;

        TwitterAPIConfigurationJSONImpl that = (TwitterAPIConfigurationJSONImpl) o;

        if (charactersReservedPerMedia != that.charactersReservedPerMedia)
            return false;
        if (maxMediaPerUpload != that.maxMediaPerUpload) return false;
        if (photoSizeLimit != that.photoSizeLimit) return false;
        if (shortURLLength != that.shortURLLength) return false;
        if (shortURLLengthHttps != that.shortURLLengthHttps) return false;
        if (!Arrays.equals(nonUsernamePaths, that.nonUsernamePaths))
            return false;
        if (photoSizes != null ? !photoSizes.equals(that.photoSizes) : that.photoSizes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoSizeLimit;
        result = 31 * result + shortURLLength;
        result = 31 * result + shortURLLengthHttps;
        result = 31 * result + charactersReservedPerMedia;
        result = 31 * result + (photoSizes != null ? photoSizes.hashCode() : 0);
        result = 31 * result + (nonUsernamePaths != null ? Arrays.hashCode(nonUsernamePaths) : 0);
        result = 31 * result + maxMediaPerUpload;
        return result;
    }

    @Override
    public String toString() {
        return "TwitterAPIConfigurationJSONImpl{" +
                "photoSizeLimit=" + photoSizeLimit +
                ", shortURLLength=" + shortURLLength +
                ", shortURLLengthHttps=" + shortURLLengthHttps +
                ", charactersReservedPerMedia=" + charactersReservedPerMedia +
                ", photoSizes=" + photoSizes +
                ", nonUsernamePaths=" + (nonUsernamePaths == null ? null : Arrays.asList(nonUsernamePaths)) +
                ", maxMediaPerUpload=" + maxMediaPerUpload +
                '}';
    }
}
