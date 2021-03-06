package twitter4jads.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import twitter4jads.*;
import twitter4jads.api.TwitterAdsStatApi;
import twitter4jads.models.ads.*;
import org.apache.commons.lang3.StringUtils;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsResponse;
import twitter4jads.TwitterAdsClient;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.Granularity;
import twitter4jads.models.TwitterSegmentationType;
import twitter4jads.util.TwitterAdUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

/**
 *
 * Date: 4/5/16
 * Time: 11:35 AM
 */
public class TwitterAdsStatApiImpl implements TwitterAdsStatApi {

    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsStatApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsResponse<JobDetails> createAsyncJob(String accountId, TwitterEntityType twitterEntityType, Collection<String> ids, String startTime,
                                                      String endTime, boolean withDeleted, Granularity granularity, Placement placement,
                                                      Optional<TwitterSegmentationType> twitterSegmentationType,
                                                      String country,
                                                      String platform) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(startTime, "startTime");
        TwitterAdUtil.ensureNotNull(ids, "entityIds");
        TwitterAdUtil.ensureNotNull(placement, "placement");

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_STATS_JOB_ACCOUNTS_URI + accountId;

        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.GRANULARITY, granularity.toString()));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_START_TIME, startTime));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_ENTITY_TYPE, twitterEntityType.name()));

        if (TwitterAdUtil.isNotNullOrEmpty(endTime)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_END_TIME, endTime));
        }

        TwitterSegmentationType segmentationType = null;
        if (twitterSegmentationType != null && twitterSegmentationType.isPresent()) {
            segmentationType = twitterSegmentationType.get();
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SEGMENTATION_TYPE, twitterSegmentationType.get().name()));
            if (TwitterAdsConstants.COUNTRY_SEGMENTS.contains(twitterSegmentationType.get())) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNTRY, country));
            } else if (TwitterAdsConstants.PLATFORM_SEGMENTS.contains(twitterSegmentationType.get())) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_PLATFORM, platform));
            }
        }


        String metrics = StringUtils.join(getMetrics(twitterEntityType, segmentationType), ",");
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_METRICS_GROUPS, metrics));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, withDeleted));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_ENTITY_IDS, TwitterAdUtil.getCsv(ids)));
        params.add(new HttpParameter(TwitterAdsConstants.PARAMS_PLACEMENT, placement.name()));

        final Type type = new TypeToken<BaseAdsResponse<JobDetails>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, params.toArray(new HttpParameter[params.size()]), type, HttpVerb.POST);
    }

    @Override
    public BaseAdsListResponseIterable<JobDetails> getJobExecutionDetails(String accountId, Collection<String> jobIds) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_JOB_IDS, TwitterAdUtil.getCsv(jobIds)));

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_STATS_JOB_ACCOUNTS_URI + accountId;
        final Type type = new TypeToken<BaseAdsListResponse<JobDetails>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @Override
    public BaseAdsListResponse<TwitterEntityStatistics> fetchJobDataAsync(String dataUrl) throws TwitterException {
        // TODO: Use executeHttpListRequest once the bug from twitter is resolved (encoding in headers)
        final String responseAsString = getResponseFromGZipStream(dataUrl);
        final Type type = new TypeToken<BaseAdsListResponse<TwitterEntityStatistics>>() {
        }.getType();
        return new Gson().fromJson(responseAsString, type);
    }

    @Override
    public BaseAdsResponse<JobDetails> deleteJob(String accountId, String jobId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(jobId, "jobId");

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_STATS_JOB_ACCOUNTS_URI + accountId + "/" + jobId;
        final Type type = new TypeToken<BaseAdsResponse<JobDetails>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, null, type, HttpVerb.DELETE);
    }

    // ----------------------------------------- Private Methods -------------------------------------------------

    // ------------------------------------------------------------------ PRIVATE METHODS -----------------------------------------------------------

    private String getResponseFromGZipStream(String urlString) {
        try {
            final URL url = new URL(urlString);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept-Encoding", "gzip");

            final BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream()), "utf-8"));
            final StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            return builder.toString();
        } catch (Exception e) {
            // Throw Exception
        }

        return null;
    }

    private String getMetrics(TwitterEntityType twitterEntity, TwitterSegmentationType twitterSegmentationType) {
        String metrics;
        switch (twitterEntity) {
            case ORGANIC_TWEET:
                metrics = StringUtils.join(TwitterEntityStatisticsMetrics.ORGANIC_METRIC_GROUPS, ",");
                break;
            case ACCOUNT:
                metrics = StringUtils.join(TwitterEntityStatisticsMetrics.ENGAGEMENT_METRIC_GROUPS, ",");
                break;
            default:
                metrics = StringUtils.join(TwitterEntityStatisticsMetrics.getMetricGroups(twitterSegmentationType), ",");
        }

        return metrics;
    }

    @Override
    public BaseAdsListResponseIterable<TwitterActiveEntity> fetchActiveEntities(String accountId, TwitterEntityType twitterEntity, Collection<String> fundingInstrumentIds, Collection<String> campaignIds, Collection<String> lineItemIds, String startTime, String endTime) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        TwitterAdUtil.ensureNotNull(startTime, "startTime");
        TwitterAdUtil.ensureNotNull(endTime, "endTime");
        String baseUrl = this.twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_STATS_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_ACTIVE_ENTITIES;
        List<HttpParameter> params = new ArrayList();
        params.add(new HttpParameter("start_time", startTime));
        params.add(new HttpParameter("end_time", endTime));
        params.add(new HttpParameter("entity", twitterEntity.name()));

        if (null != fundingInstrumentIds && !fundingInstrumentIds.isEmpty()) {
            params.add(new HttpParameter("funding_instrument_ids", TwitterAdUtil.getCsv(fundingInstrumentIds)));
        }
        if (null != campaignIds && !campaignIds.isEmpty()) {
            params.add(new HttpParameter("campaign_ids", TwitterAdUtil.getCsv(campaignIds)));
        }
        if (null != lineItemIds && !lineItemIds.isEmpty()) {
            params.add(new HttpParameter("line_item_ids", TwitterAdUtil.getCsv(lineItemIds)));
        }
        Type type = (new TypeToken<BaseAdsListResponse<TwitterActiveEntity>>() {
        }).getType();

        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @Override
    public BaseAdsListResponseIterable<JobDetails> getCurrJobsMetrics(String accountId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_STATS_JOB_ACCOUNTS_URI + accountId;

        Type type = new TypeToken<BaseAdsListResponse<JobDetails>>() {
        }.getType();

        return twitterAdsClient.executeHttpListRequest(baseUrl , null,type);
    }
}
