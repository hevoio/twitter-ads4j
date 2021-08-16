package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.Image;
import com.hevodata.twitterads4j.internal.models4j.Media;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getLong;

/**
 * Created with IntelliJ IDEA.
 *
 * Date: 08/05/14
 * Time: 4:58 PM
 */
public class TwitterUploadMediaResponseImpl extends TwitterResponseImpl implements Media {
    public static final String SUCCEEDED_STATE = "succeeded";
    public static final String IN_PROGRESS_STATE = "in_progress";
    public static final String PENDING = "pending";
    private Long mediaId;
    private String mediaIdString;
    private String mediaKey;
    private Image image;
    private Long size;
    private Long expiresAfterSecs;
    private String state;

    public Long getExpiresAfterSecs() {
        return expiresAfterSecs;
    }

    public void setExpiresAfterSecs(Long expiresAfterSecs) {
        this.expiresAfterSecs = expiresAfterSecs;
    }


    public TwitterUploadMediaResponseImpl(HttpResponse res, Configuration conf) throws TwitterException {
        super(res);//todo
        init(getJSONObject());
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, getJSONObject());
        }
    }

    public Long getMediaId() {
        return mediaId;
    }

    @Override
    public String getMediaIdString() {
        return mediaIdString;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public boolean isStatePending() {
        return PENDING.equalsIgnoreCase(state);
    }

    @Override
    public boolean isStateSucceeded() {
        return SUCCEEDED_STATE.equalsIgnoreCase(state);
    }

    @Override
    public boolean isStateInProgress() {
        return IN_PROGRESS_STATE.equalsIgnoreCase(state);
    }

    public Image getImage() {
        return image;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    private void init(JSONObject json) throws TwitterException {
        try {
            mediaId = z_T4JInternalParseUtil.getLong("media_id", json);
            mediaIdString = z_T4JInternalParseUtil.getRawString("media_id", json);
            mediaKey = z_T4JInternalParseUtil.getRawString("media_key", json);
            size = z_T4JInternalParseUtil.getLong("size", json);
            if (!json.isNull("image")) {
                image = new TwitterImageJSONImpl(json);
            }
            if (!json.isNull("expires_after_secs")) {
                expiresAfterSecs = z_T4JInternalParseUtil.getLong("expires_after_secs", json);
            }
            if (!json.isNull("processing_info")) {
                JSONObject processingInfo = new JSONObject(z_T4JInternalParseUtil.getRawString("processing_info", json));
                if (!processingInfo.isNull("state")) {
                    state = z_T4JInternalParseUtil.getRawString("state", processingInfo);
                }
            }
        } catch (Exception jsone) {
            throw new TwitterException(jsone);
        }
    }

}
