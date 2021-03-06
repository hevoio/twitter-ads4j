package twitter4jads.api;

import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsResponse;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.Granularity;
import twitter4jads.models.TwitterSegmentationType;
import twitter4jads.models.ads.*;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * Date: 4/5/16
 * Time: 11:31 AM
 */
public interface TwitterAdsStatApi {

    /**
     * @param accountId            The identifier for the leveraged account.
     * @param twitterEntityType        The enum of entity type (e.g. LINE_ITEM, PROMOTED_TWEET).
     * @param ids                  A collection of ids to retrieve stats for.
     * @param startTime            The time to retrieve stats from.
     * @param endTime              The time to retrieve stats until.
     * @param withDeleted          Whether or not to include deleted items in the results. Defaults to false.
     * @param granularity          The granularity as enum such as DAY or HOUR.
     * @param placement            The placement of entity to retrieve stats for.
     * @param twitterSegmentationType  (optional) Break down analytics data via a supported segmentation type.
     * @return job details for async job to fetch analytics data for the given paramters
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/1/post/stats/jobs/accounts/%3Aaccount_id">https://dev.twitter.com/ads/reference/1/post/stats/jobs/accounts/%3Aaccount_id</a>
     */
    BaseAdsResponse<JobDetails> createAsyncJob(String accountId, TwitterEntityType twitterEntityType, Collection<String> ids, String startTime,
                                               String endTime, boolean withDeleted, Granularity granularity, Placement placement,
                                               Optional<TwitterSegmentationType> twitterSegmentationType,
                                               String country,
                                               String platform) throws TwitterException;

    /**
     * @param accountId            The identifier for the leveraged account.
     * @param jobIds               A collection of job IDs to retrieve status of.
     * @return job execution details for given job IDs
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/1/get/stats/jobs/accounts/%3Aaccount_id">https://dev.twitter.com/ads/reference/1/get/stats/jobs/accounts/%3Aaccount_id</a>
     */
    BaseAdsListResponseIterable<JobDetails> getJobExecutionDetails(String accountId, Collection<String> jobIds) throws TwitterException;

    /**
     * @param dataUrl   The path given as output via a completed async job.
     * @return analytics data extracted from the finished output path of a async job
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/1/get/stats/jobs/accounts/%3Aaccount_id">https://dev.twitter.com/ads/reference/1/get/stats/jobs/accounts/%3Aaccount_id</a>
     */
    BaseAdsListResponse<TwitterEntityStatistics> fetchJobDataAsync(String dataUrl) throws TwitterException;

    BaseAdsResponse<JobDetails> deleteJob(String accountId, String jobId) throws TwitterException;

    BaseAdsListResponseIterable<TwitterActiveEntity> fetchActiveEntities(String accountId, TwitterEntityType twitterEntity, Collection<String> fundingInstrumentIds, Collection<String> campaignIds, Collection<String> lineItemIds, String startTime, String endTime) throws TwitterException;

    BaseAdsListResponseIterable<JobDetails> getCurrJobsMetrics(String accountId) throws TwitterException;

}