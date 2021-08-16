package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.logging.Logger;
import com.hevodata.twitterads4j.internal.models4j.GeoLocation;
import com.hevodata.twitterads4j.internal.models4j.Status;
import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import com.hevodata.twitterads4j.internal.models4j.HashtagEntity;
import com.hevodata.twitterads4j.internal.models4j.JSONResponse;
import com.hevodata.twitterads4j.internal.models4j.MediaEntity;
import com.hevodata.twitterads4j.internal.models4j.Place;
import com.hevodata.twitterads4j.internal.models4j.ResponseList;
import com.hevodata.twitterads4j.internal.models4j.TweetScope;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.internal.models4j.URLEntity;
import com.hevodata.twitterads4j.internal.models4j.User;
import com.hevodata.twitterads4j.internal.models4j.UserMentionEntity;

import java.util.Arrays;
import java.util.Date;

import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getDate;
import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getLong;

/**
 * A data class representing one single status of a user.
 *
 *
 */
public final class StatusJSONImpl extends TwitterResponseImpl implements Status, java.io.Serializable, JSONResponse {
    private static final Logger logger = Logger.getLogger(StatusJSONImpl.class);
    private static final long serialVersionUID = 7548618898682727465L;

    private Date createdAt;
    private long id;
    private String idStr;
    private String inReplyToUserIdStr;
    private String inReplyToStatusIdStr;
    private String text;
    private String actualText;
    private String fullText;
    private String[] displayTextRange;
    private String source;
    private boolean isTruncated;
    private long inReplyToStatusId;
    private long inReplyToUserId;
    private boolean isFavorited;
    private String inReplyToScreenName;
    private GeoLocation geoLocation = null;
    private Place place = null;
    private long retweetCount;
    private long favoriteCount;
    private boolean isPossiblySensitive;

    private long[] contributorsIDs;

    private Status retweetedStatus;
    private Status quotedStatus;
    private Status extendedTweet;
    private long quotedStatusId;
    private String quotedStatusIdStr;
    private UserMentionEntity[] userMentionEntities;
    private URLEntity[] urlEntities;
    private HashtagEntity[] hashtagEntities;
    private MediaEntity[] mediaEntities;
    private MediaEntity[] extendedMediaEntities;
    private long currentUserRetweetId = -1L;
    private String[] placeIds;
    private TweetScope scopes;
    private String language;
    private String cardUri;

    private Boolean hierarchicalMessage = false;
    private String hierarchicalMessageId;

    /*package*/StatusJSONImpl(HttpResponse res, Configuration conf) throws TwitterException {
        super(res);
        init(getJSONObject());
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, getJSONObject());
        }
    }

    /*package*/StatusJSONImpl(JSONObject json, Configuration conf) throws TwitterException {
        super(json);
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    public StatusJSONImpl(JSONObject json) throws TwitterException {
        super(json);
        init(json);
    }

    /* Only for serialization purposes. */
    /*package*/
    @SuppressWarnings("unused")
    StatusJSONImpl() {

    }

    public static ResponseList<Status> createStatusList(HttpResponse res, Configuration conf) throws TwitterException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }

            JSONArray list = res.asJSONArray();
            int size = list.length();
            ResponseList<Status> statuses = new ResponseListImpl<>(size, res);

            //noinspection Duplicates
            for (int i = 0; i < size; i++) {
                JSONObject json = list.getJSONObject(i);
                Status status = new StatusJSONImpl(json);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(status, json);
                }
                statuses.add(status);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(statuses, list);
            }
            return statuses;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    public static ResponseList<Status> parseStatuses(Configuration conf, JSONArray list) throws JSONException, TwitterException {
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }

        int size = list.length();
        ResponseList<Status> statuses = new ResponseListImpl<>(size, null);
        //noinspection Duplicates
        for (int i = 0; i < size; i++) {
            JSONObject json = list.getJSONObject(i);
            Status status = new StatusJSONImpl(json);
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(status, json);
            }
            statuses.add(status);
        }
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.registerJSONObject(statuses, list);
        }
        return statuses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getFullText() {
        return fullText;
    }

    @Override
    public String[] getDisplayTextRange() {
        return displayTextRange;
    }

    @Override
    public Status getExtendedTweet() {
        return extendedTweet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return this.actualText;
    }

    @Override
    public String getUnEscapedText() {
        return this.text;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() {
        return this.source;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTruncated() {
        return isTruncated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getInReplyToUserId() {
        return inReplyToUserId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place getPlace() {
        return place;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getContributors() {
        return contributorsIDs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFavorited() {
        return isFavorited;
    }


    private User user = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() {
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRetweet() {
        return retweetedStatus != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getRetweetedStatus() {
        return retweetedStatus;
    }

    @Override
    public Status getQuotedStatus() {
        return quotedStatus;
    }

    @Override
    public long getQuotedStatusId() {
        return quotedStatusId;
    }

    @Override
    public String getQuotedStatusIdStr() {
        return quotedStatusIdStr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getRetweetCount() {
        return retweetCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getFavoriteCount() {
        return favoriteCount;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRetweetedByMe() {
        return currentUserRetweetId != -1L;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getCurrentUserRetweetId() {
        return currentUserRetweetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPossiblySensitive() {
        return isPossiblySensitive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserMentionEntity[] getUserMentionEntities() {
        return userMentionEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URLEntity[] getURLEntities() {
        return urlEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashtagEntity[] getHashtagEntities() {
        return hashtagEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaEntity[] getMediaEntities() {
        return mediaEntities;
    }

    @Override
    public MediaEntity[] getExtendedMediaEntities() {
        return extendedMediaEntities;
    }

    public String[] getPlaceIds() {
        return placeIds;
    }

    @SuppressWarnings("Duplicates")
    private void init(JSONObject json) throws TwitterException {
        id = z_T4JInternalParseUtil.getLong("id", json);
        idStr = z_T4JInternalParseUtil.getRawString("id_str", json);
        source = z_T4JInternalParseUtil.getUnescapedString("source", json);
        createdAt = z_T4JInternalParseUtil.getDate("created_at", json);
        isTruncated = z_T4JInternalParseUtil.getBoolean("truncated", json);
        inReplyToStatusId = z_T4JInternalParseUtil.getLong("in_reply_to_status_id", json);
        inReplyToStatusIdStr = z_T4JInternalParseUtil.getRawString("in_reply_to_status_id_str", json);
        quotedStatusId = z_T4JInternalParseUtil.getLong("quoted_status_id", json);
        quotedStatusIdStr = z_T4JInternalParseUtil.getRawString("quoted_status_id_str", json);
        inReplyToUserId = z_T4JInternalParseUtil.getLong("in_reply_to_user_id", json);
        inReplyToUserIdStr = z_T4JInternalParseUtil.getRawString("in_reply_to_user_id_str", json);
        isFavorited = z_T4JInternalParseUtil.getBoolean("favorited", json);
        inReplyToScreenName = z_T4JInternalParseUtil.getUnescapedString("in_reply_to_screen_name", json);
        retweetCount = z_T4JInternalParseUtil.getLong("retweet_count", json);
        favoriteCount = z_T4JInternalParseUtil.getLong("favorite_count", json);
        isPossiblySensitive = z_T4JInternalParseUtil.getBoolean("possibly_sensitive", json);
        try {
            if (!json.isNull("user")) {
                user = new UserJSONImpl(json.getJSONObject("user"));
            }
            geoLocation = z_T4JInternalJSONImplFactory.createGeoLocation(json);
            if (!json.isNull("place")) {
                place = new PlaceJSONImpl(json.getJSONObject("place"));
            }

            if (!json.isNull("retweeted_status")) {
                retweetedStatus = new StatusJSONImpl(json.getJSONObject("retweeted_status"));
            }

            if (!json.isNull("quoted_status")) {
                quotedStatus = new StatusJSONImpl(json.getJSONObject("quoted_status"));
            }

            if (!json.isNull("contributors")) {
                JSONArray contributorsArray = json.getJSONArray("contributors");
                contributorsIDs = new long[contributorsArray.length()];
                for (int i = 0; i < contributorsArray.length(); i++) {
                    contributorsIDs[i] = Long.parseLong(contributorsArray.getString(i));
                }
            } else {
                contributorsIDs = new long[0];
            }
            language = z_T4JInternalParseUtil.getRawString("lang", json);
            if (!json.isNull("scopes")) {
                JSONObject scopes = json.getJSONObject("scopes");
                if (!scopes.isNull("place_ids")) {
                    JSONArray placeIdArray = scopes.getJSONArray("place_ids");
                    int len = placeIdArray.length();
                    placeIds = new String[len];
                    for (int i = 0; i < len; i++) {
                        placeIds[i] = placeIdArray.getString(i);
                    }
                }
            }
            if (!json.isNull("entities")) {
                JSONObject entities = json.getJSONObject("entities");
                int len;
                if (!entities.isNull("user_mentions")) {
                    JSONArray userMentionsArray = entities.getJSONArray("user_mentions");
                    len = userMentionsArray.length();
                    userMentionEntities = new UserMentionEntity[len];
                    for (int i = 0; i < len; i++) {
                        userMentionEntities[i] = new UserMentionEntityJSONImpl(userMentionsArray.getJSONObject(i));
                    }
                }
                if (!entities.isNull("urls")) {
                    JSONArray urlsArray = entities.getJSONArray("urls");
                    len = urlsArray.length();
                    urlEntities = new URLEntity[len];
                    for (int i = 0; i < len; i++) {
                        urlEntities[i] = new URLEntityJSONImpl(urlsArray.getJSONObject(i));
                    }
                }

                if (!entities.isNull("hashtags")) {
                    JSONArray hashtagsArray = entities.getJSONArray("hashtags");
                    len = hashtagsArray.length();
                    hashtagEntities = new HashtagEntity[len];
                    for (int i = 0; i < len; i++) {
                        hashtagEntities[i] = new HashtagEntityJSONImpl(hashtagsArray.getJSONObject(i));
                    }
                }

                if (!entities.isNull("media")) {
                    JSONArray mediaArray = entities.getJSONArray("media");
                    len = mediaArray.length();
                    mediaEntities = new MediaEntity[len];
                    for (int i = 0; i < len; i++) {
                        mediaEntities[i] = new MediaEntityJSONImpl(mediaArray.getJSONObject(i));
                    }
                }
            }

            if (!json.isNull("extended_entities")) {
                JSONObject entities = json.getJSONObject("extended_entities");
                int len;
                if (!entities.isNull("media")) {
                    JSONArray mediaArray = entities.getJSONArray("media");
                    len = mediaArray.length();
                    extendedMediaEntities = new MediaEntity[len];
                    for (int i = 0; i < len; i++) {
                        extendedMediaEntities[i] = new MediaEntityJSONImpl(mediaArray.getJSONObject(i));
                    }
                }
            }

            if (!json.isNull("display_text_range")) {
                JSONArray displayTextRangeArray = json.getJSONArray("display_text_range");
                int len = displayTextRangeArray.length();
                displayTextRange = new String[len];
                for (int i = 0; i < len; i++) {
                    displayTextRange[i] = displayTextRangeArray.getString(i);
                }
            }

            userMentionEntities = userMentionEntities == null ? new UserMentionEntity[0] : userMentionEntities;
            urlEntities = urlEntities == null ? new URLEntity[0] : urlEntities;
            hashtagEntities = hashtagEntities == null ? new HashtagEntity[0] : hashtagEntities;
            mediaEntities = mediaEntities == null ? new MediaEntity[0] : mediaEntities;
            extendedMediaEntities = extendedMediaEntities == null ? mediaEntities : extendedMediaEntities;

            fullText = z_T4JInternalParseUtil.getRawString("full_text", json);
            actualText = z_T4JInternalParseUtil.getRawString("text", json);

            if (fullText != null) {
                actualText = fullText;
            }

            if (!json.isNull("current_user_retweet")) {
                currentUserRetweetId = json.getJSONObject("current_user_retweet").getLong("id");
            }

            if (!json.isNull("extended_tweet")) {
                extendedTweet = new StatusJSONImpl(json.getJSONObject("extended_tweet"));
                String extendedTweetText = StringUtils.isNotEmpty(extendedTweet.getFullText()) ? extendedTweet.getFullText() : extendedTweet.getText();
                if (extendedTweet != null && StringUtils.isNotEmpty(extendedTweetText)) {
                    actualText = extendedTweet.getFullText();
                    userMentionEntities = extendedTweet.getUserMentionEntities();
                    urlEntities = extendedTweet.getURLEntities();
                    hashtagEntities = extendedTweet.getHashtagEntities();
                    mediaEntities = extendedTweet.getMediaEntities();
                    extendedMediaEntities = extendedTweet.getExtendedMediaEntities();
                }

            }

            text = HTMLEntity.unescapeAndSlideEntityIncdices(actualText, userMentionEntities, urlEntities, hashtagEntities, mediaEntities);

            if (!json.isNull("card_uri")) {
                cardUri = json.getString("card_uri");
            }

        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(Status that) {
        long delta = this.id - that.getId();
        if (delta < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (delta > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) delta;
    }

    @SuppressWarnings("unused")
    public void setPlaceIds(String[] placeIds) {
        this.placeIds = placeIds;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj instanceof Status && ((Status) obj).getId() == this.id;
    }

    @Override
    public String getIdStr() {
        return idStr;
    }

    @Override
    public String getInReplyToUserIdStr() {
        return inReplyToUserIdStr;
    }

    @Override
    public String getInReplyToStatusIdStr() {
        return inReplyToStatusIdStr;
    }

    public TweetScope getScopes() {
        return scopes;
    }

    public void setScopes(TweetScope scopes) {
        this.scopes = scopes;
    }

    @Override
    public Boolean getHierarchicalMessage() {
        return hierarchicalMessage;
    }

    @Override
    public void setHierarchicalMessage(Boolean hierarchicalMessage) {
        this.hierarchicalMessage = hierarchicalMessage;
    }

    @Override
    public String getHierarchicalMessageId() {
        return hierarchicalMessageId;
    }

    @Override
    public void setHierarchicalMessageId(String statusId) {
        this.hierarchicalMessageId = statusId;
    }

    @Override
    public String getCardUri() {
        return cardUri;
    }

    public void setCardUri(String cardUri) {
        this.cardUri = cardUri;
    }

    @Override
    public String toString() {
        return "StatusJSONImpl{" +
               "createdAt=" + createdAt +
               ", id=" + id +
               ", idStr='" + idStr + '\'' +
               ", inReplyToUserIdStr='" + inReplyToUserIdStr + '\'' +
               ", inReplyToStatusIdStr='" + inReplyToStatusIdStr + '\'' +
               ", text='" + text + '\'' +
               ", actualText='" + actualText + '\'' +
               ", fullText='" + fullText + '\'' +
               ", displayTextRange=" + Arrays.toString(displayTextRange) +
               ", source='" + source + '\'' +
               ", isTruncated=" + isTruncated +
               ", inReplyToStatusId=" + inReplyToStatusId +
               ", inReplyToUserId=" + inReplyToUserId +
               ", isFavorited=" + isFavorited +
               ", inReplyToScreenName='" + inReplyToScreenName + '\'' +
               ", geoLocation=" + geoLocation +
               ", place=" + place +
               ", retweetCount=" + retweetCount +
               ", favoriteCount=" + favoriteCount +
               ", isPossiblySensitive=" + isPossiblySensitive +
               ", contributorsIDs=" + Arrays.toString(contributorsIDs) +
               ", retweetedStatus=" + retweetedStatus +
               ", quotedStatus=" + quotedStatus +
               ", extendedTweet=" + extendedTweet +
               ", quotedStatusId=" + quotedStatusId +
               ", quotedStatusIdStr='" + quotedStatusIdStr + '\'' +
               ", userMentionEntities=" + Arrays.toString(userMentionEntities) +
               ", urlEntities=" + Arrays.toString(urlEntities) +
               ", hashtagEntities=" + Arrays.toString(hashtagEntities) +
               ", mediaEntities=" + Arrays.toString(mediaEntities) +
               ", extendedMediaEntities=" + Arrays.toString(extendedMediaEntities) +
               ", currentUserRetweetId=" + currentUserRetweetId +
               ", placeIds=" + Arrays.toString(placeIds) +
               ", scopes=" + scopes +
               ", language='" + language + '\'' +
                ", cardUri='" + cardUri + '\'' +
               ", hierarchicalMessage=" + hierarchicalMessage +
                ", hierarchicalMessageId='" + hierarchicalMessageId + '\'' +
               ", user=" + user +
               '}';
    }
}
