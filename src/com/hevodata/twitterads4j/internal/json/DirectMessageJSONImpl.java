package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.*;
import com.hevodata.twitterads4j.internal.org.json.JSONArray;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;

/**
 * A data class representing sent/received direct message.
 *
 *
 */
public final class DirectMessageJSONImpl extends TwitterResponseImpl implements DirectMessage, java.io.Serializable {
    private static final long serialVersionUID = -7104233663827757577L;
    private long id;
    private String idStr;
    private String actualText;
    private String text;
    private long senderId;
    private long recipientId;
    private Date createdAt;
    private String senderScreenName;
    private String recipientScreenName;
    private UserMentionEntity[] userMentionEntities;
    private URLEntity[] urlEntities;
    private HashtagEntity[] hashtagEntities;
    private MediaEntity[] mediaEntities;
    private MediaEntity[] extendedMediaEntities;

    /*package*/DirectMessageJSONImpl(HttpResponse res, Configuration conf) throws TwitterException {
        super(res);
        init(getJSONObject());
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, getJSONObject());
        }
    }

    /* Only for serialization purposes. */
    /*package*/ DirectMessageJSONImpl() {
    }

    public DirectMessageJSONImpl(JSONObject json) throws TwitterException {
        super(json);
        init(json);
    }

    private void init(JSONObject json) throws TwitterException {
        id = z_T4JInternalParseUtil.getLong("id", json);
        idStr = z_T4JInternalParseUtil.getRawString("id_str", json);
        senderId = z_T4JInternalParseUtil.getLong("sender_id", json);
        recipientId = z_T4JInternalParseUtil.getLong("recipient_id", json);
        createdAt = z_T4JInternalParseUtil.getDate("created_at", json);
        senderScreenName = z_T4JInternalParseUtil.getUnescapedString("sender_screen_name", json);
        recipientScreenName = z_T4JInternalParseUtil.getUnescapedString("recipient_screen_name", json);
        try {
            sender = new UserJSONImpl(json.getJSONObject("sender"));
            recipient = new UserJSONImpl(json.getJSONObject("recipient"));
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

            userMentionEntities = userMentionEntities == null ? new UserMentionEntity[0] : userMentionEntities;
            urlEntities = urlEntities == null ? new URLEntity[0] : urlEntities;
            hashtagEntities = hashtagEntities == null ? new HashtagEntity[0] : hashtagEntities;
            mediaEntities = mediaEntities == null ? new MediaEntity[0] : mediaEntities;
            extendedMediaEntities = extendedMediaEntities == null ? mediaEntities : extendedMediaEntities;
            actualText = json.getString("text");
            text = HTMLEntity.unescapeAndSlideEntityIncdices(actualText, userMentionEntities, urlEntities, hashtagEntities, mediaEntities);
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
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
    public String getText() {
        return this.actualText;
    }

    @Override
    public String getUnEscapedText() {
        return this.text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getSenderId() {
        return senderId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getRecipientId() {
        return recipientId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSenderScreenName() {
        return senderScreenName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRecipientScreenName() {
        return recipientScreenName;
    }

    private User sender;

    /**
     * {@inheritDoc}
     */
    @Override
    public User getSender() {
        return sender;
    }

    private User recipient;

    /**
     * {@inheritDoc}
     */
    @Override
    public User getRecipient() {
        return recipient;
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

    /*package*/
    static ResponseList<DirectMessage> createDirectMessageList(HttpResponse res, Configuration conf) throws TwitterException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray list = res.asJSONArray();
            int size = list.length();
            ResponseList<DirectMessage> directMessages = new ResponseListImpl<DirectMessage>(size, res);
            for (int i = 0; i < size; i++) {
                JSONObject json = list.getJSONObject(i);
                DirectMessage directMessage = new DirectMessageJSONImpl(json);
                directMessages.add(directMessage);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(directMessage, json);
                }
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(directMessages, list);
            }
            return directMessages;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        } catch (TwitterException te) {
            throw te;
        }
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
        return obj instanceof DirectMessage && ((DirectMessage) obj).getId() == this.id;
    }

    @Override
    public String getIdStr() {
        return idStr;
    }

    @Override
    public String toString() {
        return "DirectMessageJSONImpl{" +
               "id=" + id +
               ", idStr='" + idStr + '\'' +
               ", actualText='" + actualText + '\'' +
               ", text='" + text + '\'' +
               ", senderId=" + senderId +
               ", recipientId=" + recipientId +
               ", createdAt=" + createdAt +
               ", senderScreenName='" + senderScreenName + '\'' +
               ", recipientScreenName='" + recipientScreenName + '\'' +
               ", userMentionEntities=" + Arrays.toString(userMentionEntities) +
               ", urlEntities=" + Arrays.toString(urlEntities) +
               ", hashtagEntities=" + Arrays.toString(hashtagEntities) +
               ", mediaEntities=" + Arrays.toString(mediaEntities) +
               ", sender=" + sender +
               ", recipient=" + recipient +
               '}';
    }
}
