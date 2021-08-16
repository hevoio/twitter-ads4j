package com.hevodata.twitterads4j.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.hevodata.twitterads4j.TwitterAdsClient;
import com.hevodata.twitterads4j.TwitterAdsConstants;
import com.hevodata.twitterads4j.api.TwitterAdsMediaUploadApi;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import com.hevodata.twitterads4j.internal.http.HttpParameter;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.internal.models4j.TwitterInvalidParameterException;
import com.hevodata.twitterads4j.internal.models4j.TwitterRuntimeException;
import com.hevodata.twitterads4j.models.ads.HttpVerb;
import com.hevodata.twitterads4j.models.media.TwitterMediaType;
import com.hevodata.twitterads4j.models.video.UploadMediaObjectResponse;
import com.hevodata.twitterads4j.util.TwitterAdUtil;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 *
 * Date: 4/5/16
 * Time: 10:41 AM
 */
public class TwitterAdsMediaUploadApiImpl implements TwitterAdsMediaUploadApi {

    private static final Map<Long, Long> VIDEO_SIZE_PROCESSING_WAIT_TIME_MAP;

    static {
        final Map<Long, Long> videoSizeProcessingWaitTimeMap = Maps.newHashMap();
        videoSizeProcessingWaitTimeMap.put(TwitterAdsConstants.FIFTY_MIB, TwitterAdsConstants.MAX_WAIT_INTERVAL_FIFTY_MIB);
        videoSizeProcessingWaitTimeMap.put(TwitterAdsConstants.ONE_HUNDRED_FIFTY_MIB, TwitterAdsConstants.MAX_WAIT_INTERVAL_ONE_HUNDRED_FIFTY_MIB);
        videoSizeProcessingWaitTimeMap.put(TwitterAdsConstants.FIVE_HUNDRED_MIB, TwitterAdsConstants.MAX_WAIT_INTERVAL_FIVE_HUNDRED_MIB);

        VIDEO_SIZE_PROCESSING_WAIT_TIME_MAP = Collections.unmodifiableMap(videoSizeProcessingWaitTimeMap);
    }

    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsMediaUploadApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public String uploadMediaAndGetMediaKey(String mediaUrl, Set<String> accountUserIds, TwitterMediaType twitterMediaType, String name)
            throws TwitterException {
        final UploadMediaObjectResponse responseFromFinalize = uploadAndGetMediaId(mediaUrl, accountUserIds, twitterMediaType, name);
        final String mediaIdString = responseFromFinalize.getMediaIdString();
        final String mediaKey = responseFromFinalize.getMediaKey();
        final Long videoSize = responseFromFinalize.getSize();

        //as per documentation if media process info is null then the video is ready
        if (responseFromFinalize.getUploadMediaProcessingInfo() == null) {
            return mediaKey;
        }

        if (responseFromFinalize.getUploadMediaProcessingInfo().getUploadErrorInfo() != null) {
            throw new TwitterException(responseFromFinalize.getUploadMediaProcessingInfo().getUploadErrorInfo().getMessage());
        }

        final String state = responseFromFinalize.getUploadMediaProcessingInfo().getState();
        final Integer progressPercentage = responseFromFinalize.getUploadMediaProcessingInfo().getProgressPercentage();
        if ((TwitterAdUtil.isNotNullOrEmpty(state) && state.equalsIgnoreCase("succeeded")) ||
                (progressPercentage != null && progressPercentage == 100)) {
            return mediaKey;
        }

        return waitForVideoProcessingAndReturnMediaKey(mediaKey, mediaIdString, responseFromFinalize, videoSize);
    }

    // ------------------------------------------------------------------- PRIVATE METHODS ----------------------------------------------------------

    private UploadMediaObjectResponse uploadAndGetMediaId(String mediaUrl, Set<String> accountUserIds, TwitterMediaType twitterMediaType, String name)
            throws TwitterException {
        try {
            String mediaSizeInBytes = getMediaSizeInBytes(mediaUrl);
            String mediaId = initiateMediaUpload(mediaSizeInBytes, accountUserIds, twitterMediaType, name);
            uploadMedia(mediaUrl, mediaId, mediaSizeInBytes);
            return finalizeMediaUpload(mediaId);
        } catch (Exception e) {
            if (e instanceof TwitterException) {
                throw (TwitterException) e;
            }
            throw new TwitterException("Error Occurred while uploading Media", e);
        }
    }

    private String getMediaSizeInBytes(String mediaUrl) throws TwitterException, IOException {
        try {
            URLConnection urlConnection = new URL(mediaUrl).openConnection();
            return urlConnection.getHeaderField("Content-Length");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid media url");
        }
    }

    private String initiateMediaUpload(String mediaSizeInBytes, Set<String> accountUserIds, TwitterMediaType twitterMediaType, String name)
            throws TwitterException {
        if (StringUtils.isBlank(mediaSizeInBytes)) {
            throw new TwitterInvalidParameterException("Media could not be uploaded as connection could not be established");
        }

        Long mediaSizeInBytesLong;
        try {
            mediaSizeInBytesLong = Long.valueOf(mediaSizeInBytes);
        } catch (NumberFormatException eX) {
            throw new TwitterException("Media could not be uploaded as connection could not be established");
        }

        if (twitterMediaType == TwitterMediaType.IMAGE && mediaSizeInBytesLong > TwitterAdsConstants.MAX_IMAGE_SIZE_FOR_TWITTER_IN_BYTES) {
            throw new TwitterInvalidParameterException("Image should be less than 5 MB in size");
        }
        if (twitterMediaType == TwitterMediaType.VIDEO && mediaSizeInBytesLong > TwitterAdsConstants.MAX_VIDEO_SIZE_IN_BYTES) {
            throw new TwitterInvalidParameterException("Video should be less than 500 MB in size");
        }

        final String url = twitterAdsClient.getMediaUploadBaseUrl() + TwitterAdsConstants.UPLOAD_MEDIA_URL + TwitterAdsConstants.UPLOAD_JSON;
        final HttpParameter[] parameters = createInitiateMediaUploadParams(mediaSizeInBytes, accountUserIds, twitterMediaType);
        return twitterAdsClient.mediaUploadInitOrFinalize(url, parameters).getMediaIdString();
    }

    private UploadMediaObjectResponse finalizeMediaUpload(String mediaId) throws TwitterException {
        final String url = twitterAdsClient.getMediaUploadBaseUrl() + TwitterAdsConstants.UPLOAD_MEDIA_URL + TwitterAdsConstants.UPLOAD_JSON;
        final HttpParameter[] parameters = createFinalizeMediaUploadParams(mediaId);
        final Type type = new TypeToken<UploadMediaObjectResponse>() {
        }.getType();

        return twitterAdsClient.executeRequest(url, parameters, type, HttpVerb.POST);
    }

    private HttpParameter[] createInitiateMediaUploadParams(String mediaSizeInBytes, Set<String> accountUserIds, TwitterMediaType twitterMediaType) {
        if (StringUtils.isBlank(mediaSizeInBytes)) {
            throw new TwitterRuntimeException(null, new TwitterException("mediaSizeInBytes cannot be blank or null."));
        }

        final List<HttpParameter> params = Lists.newArrayList();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_COMMAND, "INIT"));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_TOTAL_BYTES, mediaSizeInBytes));

        switch (twitterMediaType) {
            case VIDEO:
                /*
                Only one attributable user can be specified per video.
                Only the attributable user will be able to use the video in a Tweet.
                If additional owners are also listed, they will be ignored.
                */
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_TYPE, "video/mp4"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_CATEGORY, "amplify_video"));
                if (TwitterAdUtil.isNotEmpty(accountUserIds) && accountUserIds.size() == 1) {
                    final String accountUserId = accountUserIds.iterator().next();
                    params.add(new HttpParameter(TwitterAdsConstants.PARAM_ATTRIBUTABLE_USER_ID, accountUserId));
                }
                break;
            case IMAGE:
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_TYPE, "image/jpeg"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_CATEGORY, "tweet_image"));
                break;
            case DM_IMAGE:
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_TYPE, "image/jpeg"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_CATEGORY, "dm_image"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_SHARED, true));
                break;
            case DM_VIDEO:
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_TYPE, "video/mp4"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_CATEGORY, "dm_video"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_SHARED, true));
                break;
            case DM_GIF:
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_TYPE, "video/gif")); //check this
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_CATEGORY, "dm_image"));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_SHARED, true));
                break;
            default:
                break;
        }

        if (TwitterAdUtil.isNotEmpty(accountUserIds)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_ADDITIONAL_OWNERS, TwitterAdUtil.getCsv(accountUserIds)));
        }

        return params.toArray(new HttpParameter[params.size()]);
    }

    private void uploadMedia(String mediaUrl, String mediaId, String mediaSizeInBytes) throws TwitterException, IOException {
        int segmentIndex = 0;
        Long bytesLeftToUpload = Long.valueOf(mediaSizeInBytes);
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        try {
            inputStream = new URL(mediaUrl).openStream();
            bis = new BufferedInputStream(inputStream);

            while (bytesLeftToUpload > 0) {
                int totalBytesRead = 0;
                byte[] chunkToUpload = new byte[2 * TwitterAdsConstants.TWO_MIB];

                while (totalBytesRead < TwitterAdsConstants.TWO_MIB) {
                    byte[] chunk = new byte[TwitterAdsConstants.TWO_MIB];
                    int bytesRead = bis.read(chunk);
                    if (bytesRead == -1) {
                        break;
                    } else {
                        chunk = Arrays.copyOf(chunk, bytesRead);
                        for (int i = 0; i < bytesRead; i++) {
                            chunkToUpload[totalBytesRead++] = chunk[i];
                        }
                    }
                }

                if (totalBytesRead > 0) {
                    chunkToUpload = Arrays.copyOf(chunkToUpload, totalBytesRead);
                    String base64Encoding = DatatypeConverter.printBase64Binary(chunkToUpload);
                    appendChunk(mediaId, base64Encoding, segmentIndex);
                    bytesLeftToUpload -= totalBytesRead;
                    segmentIndex += 1;
                } else {
                    break;
                }
            }
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(bis);
            }
        }
    }

    private void appendChunk(String mediaId, String chunk, int segmentIndex) throws TwitterException {
        String url = twitterAdsClient.getMediaUploadBaseUrl() + "media/upload.json";

        List<HttpParameter> params = createAppendChunkParams(mediaId, chunk, segmentIndex);
        HttpParameter[] parameters = params.toArray(new HttpParameter[params.size()]);

        HttpResponse response = twitterAdsClient.postRequest(url, parameters);
        int responseCode = response.getStatusCode();
        if (responseCode < TwitterAdsConstants.SUCCESSFULL_CALL_BEGIN_CODE || responseCode > TwitterAdsConstants.SUCCESSFULL_CALL_END_CODE) {
            throw new TwitterException(response.asString());
        }

    }

    private List<HttpParameter> createAppendChunkParams(String mediaId, String chunk, int segment_index) {
        List<HttpParameter> params = Lists.newArrayList();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_COMMAND, "APPEND"));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_ID, mediaId));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_DATA, chunk));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_SEGMENT_INDEX, segment_index));

        return params;
    }

    private HttpParameter[] createFinalizeMediaUploadParams(String mediaId) {
        final List<HttpParameter> params = Lists.newArrayList();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_COMMAND, "FINALIZE"));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_ID, mediaId));

        return params.toArray(new HttpParameter[params.size()]);
    }

    private String waitForVideoProcessingAndReturnMediaKey(String mediaKey, String mediaIdString, UploadMediaObjectResponse statusResponse, Long videoSize)
            throws TwitterException {
        if (statusResponse == null) {
            statusResponse = getUploadStatus(mediaIdString);
        }

        Long timeToWait = 0L;
        Long checkAfterSeconds = statusResponse.getUploadMediaProcessingInfo().getCheckAfterSeconds();
        Long maxWaitTime = decideMaxWaitTime(videoSize);
        while (timeToWait < maxWaitTime) {
            TwitterAdUtil.reallySleep(checkAfterSeconds * 1000);
            timeToWait = timeToWait + checkAfterSeconds;

            statusResponse = getUploadStatus(mediaIdString);
            if (statusResponse == null) {
                throw new TwitterException("Could not upload Video successfully, please select a different video");
            }
            //as per documentation if media process info is null then the video is ready
            if (statusResponse.getUploadMediaProcessingInfo() == null) {
                return mediaKey;
            }
            if (statusResponse.getUploadMediaProcessingInfo().getUploadErrorInfo() != null) {
                throw new TwitterException(statusResponse.getUploadMediaProcessingInfo().getUploadErrorInfo().getMessage());
            }

            String state = statusResponse.getUploadMediaProcessingInfo().getState();
            Integer progressPercentage = statusResponse.getUploadMediaProcessingInfo().getProgressPercentage();
            if ((TwitterAdUtil.isNotNullOrEmpty(state) && state.equalsIgnoreCase("succeeded")) ||
                    (progressPercentage != null && progressPercentage == 100)) {
                return mediaKey;
            }
        }

        if (statusResponse.getUploadMediaProcessingInfo().getProgressPercentage() != null &&
                statusResponse.getUploadMediaProcessingInfo().getProgressPercentage() < 100 &&
                statusResponse.getUploadMediaProcessingInfo().getState() != null &&
                statusResponse.getUploadMediaProcessingInfo().getState().equalsIgnoreCase("in_progress")) {
            throw new TwitterException(
                    "Please retry playing the ad, or upload a new video, there is problem at Twitter's end in processing the " + "video");
        }
        throw new TwitterException(statusResponse.getUploadMediaProcessingInfo().getUploadErrorInfo().getMessage());
    }

    private Long decideMaxWaitTime(Long videoSize) {
        Long maxWaitTime = VIDEO_SIZE_PROCESSING_WAIT_TIME_MAP.get(getVideoSizeGroup(videoSize));
        if (maxWaitTime == null) {
            maxWaitTime = TwitterAdsConstants.MAX_WAIT_INTERVAL_ONE_HUNDRED_FIFTY_MIB;
        }

        return maxWaitTime;
    }

    private Long getVideoSizeGroup(Long videoSize) {
        if (videoSize == null) {
            return TwitterAdsConstants.ONE_HUNDRED_FIFTY_MIB;
        }

        if (videoSize <= TwitterAdsConstants.FIFTY_MIB) {
            return TwitterAdsConstants.FIFTY_MIB;
        } else if (videoSize <= TwitterAdsConstants.ONE_HUNDRED_FIFTY_MIB) {
            return TwitterAdsConstants.ONE_HUNDRED_FIFTY_MIB;
        } else {
            return TwitterAdsConstants.FIVE_HUNDRED_MIB;
        }
    }

    private UploadMediaObjectResponse getUploadStatus(String mediaId) throws TwitterException {
        final String url = twitterAdsClient.getMediaUploadBaseUrl() + "media/upload.json";

        final List<HttpParameter> params = Lists.newArrayList();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_COMMAND, "STATUS"));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_ID, mediaId));

        Type type = new TypeToken<UploadMediaObjectResponse>() {
        }.getType();

        return twitterAdsClient.executeRequest(url, params.toArray(new HttpParameter[params.size()]), type, HttpVerb.GET);
    }
}
