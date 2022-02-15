package twitter4jads.internal.models4j;

import twitter4jads.auth.Authorization;
import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.json.TwitterUploadMediaResponseImpl;
import twitter4jads.internal.util.z_T4JInternalStringUtil;
import twitter4jads.util.TwitterAdUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * A java representation of the <a href="https://dev.twitter.com/docs/api">Twitter REST API</a><br>
 * This class is thread safe and can be cached/re-used and used concurrently.<br>
 * Currently this class is not carefully designed to be extended. It is suggested to extend this class only for mock testing purpose.<br>
 *
 */
public class TwitterImpl extends TwitterBaseImpl implements Twitter {
    private static final long serialVersionUID = -1486360080128882436L;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    //TODO: move these to a constant file along with other hardcoded strings
    public static final String COMMAND = "command";
    public static final String MEDIA_ID = "media_id";
    public static final String SEGMENT_INDEX = "segment_index";
    public static final String MEDIA = "media";
    public static final String MEDIA_TYPE = "media_type";
    public static final String MEDIA_CATEGORY = "media_category";
    public static final String PARAM_ADDITIONAL_OWNERS = "additional_owners";
    public static final String TOTAL_BYTES = "total_bytes";
    public static final String INIT = "INIT";
    public static final String APPEND = "APPEND";
    public static final String FINALIZE = "FINALIZE";
    public static final String STATUS = "STATUS";

    private final String IMPLICIT_PARAMS_STR;
    private final HttpParameter[] IMPLICIT_PARAMS;
    private final HttpParameter INCLUDE_MY_RETWEET;
    private final HttpParameter TWEET_MODE;
    long WAIT_INTERVAL_TRANSCODING = TimeUnit.SECONDS.toMillis(5);

    private static final Map<Configuration, HttpParameter[]> implicitParamsMap = new ConcurrentHashMap<>();
    private static final Map<Configuration, String> implicitParamsStrMap = new ConcurrentHashMap<>();

    /*package*/
    public TwitterImpl(Configuration conf, Authorization auth) {
        super(conf, auth);
        INCLUDE_MY_RETWEET = new HttpParameter("include_my_retweet", conf.isIncludeMyRetweetEnabled());
        TWEET_MODE = new HttpParameter("tweet_mode", conf.getTweetMode());

        HttpParameter[] implicitParams = implicitParamsMap.get(conf);
        String implicitParamsStr = implicitParamsStrMap.get(conf);
        if (implicitParams == null) {
            String includeEntities = conf.isIncludeEntitiesEnabled() ? "1" : "0";
            String includeRTs = conf.isIncludeRTsEnabled() ? "1" : "0";
            boolean contributorsEnabled = conf.getContributingTo() != -1L;
            implicitParamsStr = "include_entities=" + includeEntities + "&include_rts=" + includeRTs +
                                (contributorsEnabled ? "&contributingto=" + conf.getContributingTo() : "");
            implicitParamsStrMap.put(conf, implicitParamsStr);

            List<HttpParameter> params = new ArrayList<HttpParameter>();
            params.add(new HttpParameter("include_entities", includeEntities));
            params.add(new HttpParameter("include_rts", includeRTs));
            if (contributorsEnabled) {
                params.add(new HttpParameter("contributingto", conf.getContributingTo()));
            }
            implicitParams = params.toArray(new HttpParameter[params.size()]);
            implicitParamsMap.put(conf, implicitParams);
        }
        IMPLICIT_PARAMS = implicitParams;
        IMPLICIT_PARAMS_STR = implicitParamsStr;
    }

    /**
     * {@inheritDoc}
     */
    public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
        return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/retweets_of_me.json", new HttpParameter[]{TWEET_MODE}));
    }

    /**
     * {@inheritDoc}
     */
    public ResponseList<Status> getRetweetsOfMe(Paging paging) throws TwitterException {
        return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/retweets_of_me.json",
                                            mergeParameters(paging.asPostParameterArray(), new HttpParameter[]{TWEET_MODE})));
    }

    public ResponseList<Status> getScopedTimeLine(long userId, Paging paging) throws TwitterException {
        return factory.createStatusList(get(conf.getRestBaseURL() + "statuses/scoped_timeline.json", mergeParameters(
                mergeParameters(new HttpParameter[]{new HttpParameter("user_id", userId)}, paging.asPostParameterArray()),
                new HttpParameter[]{TWEET_MODE})));
    }

    private void addParameterToList(List<HttpParameter> colors, String paramName, String color) {
        if (color != null) {
            colors.add(new HttpParameter(paramName, color));
        }
    }

    private void checkFileValidity(File image) throws TwitterException {
        if (!image.exists()) {
            //noinspection ThrowableInstanceNeverThrown
            throw new TwitterException(new FileNotFoundException(image + " is not found."));
        }
        if (!image.isFile()) {
            //noinspection ThrowableInstanceNeverThrown
            throw new TwitterException(new IOException(image + " is not a file."));
        }
    }


    /**
     * upload status :- [pending] -> [in_progress] -> [succeeded | failed]
     *
     * @see <a href="https://dev.twitter.com/rest/reference/get/media/upload-status"> Upload Status</a>
     */
    protected void waitForVideoTranscoding(String mediaId, long maxWaitTime) throws TwitterException {
        long totalWaitTime = 0;
        String url = getMediaUploadUrl();
        HttpParameter[] parameters = createChunkedUploadStatusParams(mediaId);

        while (totalWaitTime < maxWaitTime) {
            TwitterAdUtil.reallySleep(WAIT_INTERVAL_TRANSCODING);
            totalWaitTime += WAIT_INTERVAL_TRANSCODING;
            Media media = checkVideoUploadStatus(url, parameters);
            if (media.isStatePending()) {
                continue;
            }
            if (media.isStateSucceeded()) {
                return;
            } else if (!media.isStateInProgress()) {
                throw new TwitterException("Video transcoding error. Error code: " + media.getState());
            }
        }
    }

    private HttpParameter[] createChunkedUploadStatusParams(String mediaId) {
        HttpParameter[] parameters = new HttpParameter[2];
        parameters[0] = new HttpParameter(COMMAND, STATUS);
        parameters[1] = new HttpParameter(MEDIA_ID, mediaId);
        return parameters;
    }

    private void uploadVideoInChunks(String mediaId, String filePath, Long fileSize, int chunkSize) throws TwitterException {
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            long totalBytesRead = 0l;
            for (int segmentIndex = 0; totalBytesRead < fileSize; segmentIndex++) {
                chunkSize = chunkSize < (int) (fileSize - totalBytesRead) ? chunkSize : (int) (fileSize - totalBytesRead);
                byte[] chunk = new byte[chunkSize];
                int bytesRead = inputStream.read(chunk);
                if (bytesRead == -1) {
                    throw new IOException("EOF reached..");
                }
                post(getMediaUploadUrl(), createAppendParams(mediaId, segmentIndex, chunk));
                totalBytesRead += bytesRead;
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    private HttpParameter[] createAppendParams(String mediaId, int segmentIndex, byte[] chunk) {
        HttpParameter[] parameters = new HttpParameter[4];
        parameters[0] = new HttpParameter(COMMAND, APPEND);
        parameters[1] = new HttpParameter(MEDIA_ID, mediaId);
        parameters[2] = new HttpParameter(SEGMENT_INDEX, segmentIndex);
        parameters[3] = new HttpParameter(MEDIA, "", new ByteArrayInputStream(chunk));
        return parameters;
    }

    private String initializeChunkUpload(long fileSizeInBytes, String mediaType, String mediaCategory, String accountUserId) throws TwitterException {
        List<HttpParameter> parameters = new ArrayList<>();
        parameters.add(new HttpParameter(COMMAND, INIT));
        parameters.add(new HttpParameter(MEDIA_TYPE, mediaType));
        if (StringUtils.isNotBlank(mediaCategory)) {
            parameters.add(new HttpParameter(MEDIA_CATEGORY, mediaCategory));
        }
        parameters.add(new HttpParameter(PARAM_ADDITIONAL_OWNERS, accountUserId));
        parameters.add(new HttpParameter(TOTAL_BYTES, fileSizeInBytes));
        HttpResponse response = post(getMediaUploadUrl(), parameters.toArray(new HttpParameter[parameters.size()]));
        return new TwitterUploadMediaResponseImpl(response, conf).getMediaIdString();
    }

    private void finalizeChunkUpload(String mediaId) throws TwitterException {
        List<HttpParameter> parameters = new ArrayList<>();
        parameters.add(new HttpParameter(COMMAND, FINALIZE));
        parameters.add(new HttpParameter(MEDIA_ID, mediaId));
        post(getMediaUploadUrl(), parameters.toArray(new HttpParameter[parameters.size()]));
    }

    private String getMediaUploadUrl() {return conf.getMediaUploadBaseUrl() + "media/upload.json";}

    @Override
    String getImplicitParamsStr() {
        return IMPLICIT_PARAMS_STR;
    }

    @Override
    HttpParameter[] getImplicitParams() {
        return IMPLICIT_PARAMS;
    }

    @Override
    public String toString() {
        return "TwitterImpl{" +
               "INCLUDE_MY_RETWEET=" + INCLUDE_MY_RETWEET +
               '}';
    }
}
