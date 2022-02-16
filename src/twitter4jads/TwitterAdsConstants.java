package twitter4jads;

import twitter4jads.models.TwitterSegmentationType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * Date: 13/03/14
 * Time: 11:52 AM
 */
public interface TwitterAdsConstants {

    String CURRENT_VERSION = "10";

    String PREFIX_ACCOUNTS_URI = CURRENT_VERSION + "/accounts/";
    String PREFIX_STATS_ACCOUNTS_URI = CURRENT_VERSION + "/stats/accounts/";
    String PREFIX_STATS_JOB_ACCOUNTS_URI = CURRENT_VERSION + "/stats/jobs/accounts/";
    String PARAM_WITH_DELETED = "with_deleted";
    String GRANULARITY = "granularity";
    String PARAM_FUNDING_INSTRUMENT_IDS = "funding_instrument_ids";
    String PARAM_CAMPAIGN_IDS = "campaign_ids";
    String PARAM_LINE_ITEM_IDS = "line_item_ids";
    String PARAM_ACCOUNT_IDS = "account_ids";
    String PARAM_COUNT = "count";
    String PARAM_CURSOR = "cursor";
    String PARAM_SORT_BY = "sort_by";
    String PARAM_OS_TYPE = "os_type";
    String PARAM_Q = "q";
    String PARAM_COUNTRY_CODE = "country_code";
    String PARAM_LOCATION_TYPE = "location_type";
    String PARAM_ACCOUNT_ID = "account_id";
    String PARAM_TWEET_IDS = "tweet_ids";
    String PARAM_USER_ID = "user_id";
    String PARAM_END_TIME = "end_time";
    String PARAM_START_TIME = "start_time";
    String PARAM_ENTITY_TYPE = "entity";
    String PARAM_JOB_IDS = "job_ids";
    String PARAM_ENTITY_IDS = "entity_ids";
    String PARAMS_PLACEMENT = "placement";
    String PATH_FEATURES = "/features";


    /**
     * for lead generation card stat
     */
    String PARAM_METRICS_GROUPS = "metric_groups";

    String PARAM_SEGMENTATION_TYPE = "segmentation_type";
    String PATH_CAMPAIGN = "/campaigns/";
    String PATH_FUNDING_INSTRUMENTS = "/funding_instruments/";
    String PATH_PROMOTED_TWEETS = "/promoted_tweets/";
    String PATH_LINE_ITEMS = "/line_items/";
    String PATH_TARGETING_CRITERIA_LOCATION = CURRENT_VERSION + "/targeting_criteria/locations";
    String PATH_TARGETING_CRITERIA_PLATFORMS = CURRENT_VERSION + "/targeting_criteria/platforms";
    String PATH_MEDIA_CREATIVES = "/media_creatives";
    String PATH_ACTIVE_ENTITIES = "/active_entities";
    String PARAM_TWEET_TYPE = "tweet_type";

    /**
     * For Tweets endpoint
     */
    String PATH_TWEETS = "/tweets";
    String PARAM_INCLUDE_MENTIONS_AND_REPLIES = "include_mentions_and_replies";
    String PARAM_TIMELINE_TYPE = "timeline_type";
    String PARAM_TRIM_USER = "trim_user";
    String ACCOUNT_ID = "Account Id";

    String PARAM_COUNTRY = "country";
    String PARAM_PLATFORM = "platform";
    List<TwitterSegmentationType> COUNTRY_SEGMENTS = Arrays.asList(TwitterSegmentationType.CITIES,
            TwitterSegmentationType.POSTAL_CODES,
            TwitterSegmentationType.REGIONS);
    List<TwitterSegmentationType> PLATFORM_SEGMENTS = Arrays.asList(TwitterSegmentationType.DEVICES,
            TwitterSegmentationType.PLATFORM_VERSIONS);

}
