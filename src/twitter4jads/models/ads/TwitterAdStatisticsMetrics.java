package twitter4jads.models.ads;

import com.google.api.client.util.Sets;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


/**
 *
 * Date: 2/27/16
 * Time: 10:34 AM
 */

public class TwitterAdStatisticsMetrics {

    private static final Set<String> ALL_METRICS;
    private static final Map<String, Set<String>> METRICS_BY_OBJECTIVE;

    static {
        Set<String> adLevelMetrics = Sets.newHashSet();

        for (MetricClass metricClass : MetricClass.values()) {
            adLevelMetrics.addAll(Arrays.asList(metricClass.getMetrics()));
        }

        Map<String, Set<String>> metricsByObjective = Maps.newHashMap();
        for (AdObjective adObjective : AdObjective.values()) {
            Set<String> metrics = Sets.newHashSet();
            for (MetricClass metricClass : adObjective.getMetricClasses()) {
                metrics.addAll(Arrays.asList(metricClass.getMetrics()));
            }
            metricsByObjective.put(adObjective.getObjective().toLowerCase(), metrics);
        }

        ALL_METRICS = Collections.unmodifiableSet(adLevelMetrics);
        METRICS_BY_OBJECTIVE = Collections.unmodifiableMap(metricsByObjective);
    }

    enum MetricClass {
        ENGAGEMENT(TwitterAdStatistics.BILLED_ENGAGEMENTS, TwitterAdStatistics.BILLED_FOLLOWS, TwitterAdStatistics.PROMOTED_ACCOUNT_FOLLOWS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_CARD_ENGAGEMENTS,
                TwitterAdStatistics.PROMOTED_TWEET_PROFILE_CLICKS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_ENGAGEMENTS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_FAVORITES,
                TwitterAdStatistics.PROMOTED_TWEET_PROFILE_FOLLOWS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_REPLIES, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_RETWEETS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_URL_CLICKS,
                TwitterAdStatistics.PROMOTED_TWEET_SEARCH_CARD_ENGAGEMENTS, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_CLICKS, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_ENGAGEMENTS,
                TwitterAdStatistics.PROMOTED_TWEET_SEARCH_FAVOURITES, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_FOLLOWS, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_REPLIES, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_RETWEETS,
                TwitterAdStatistics.PROMOTED_TWEET_SEARCH_URL_CLICKS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_CARD_ENGAGEMENTS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_CLICKS,
                TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_ENGAGEMENTS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_FAVOURITES, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_FOLLOWS,
                TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_REPLIES, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_RETWEETS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_URL_CLICKS,
                TwitterAdStatistics.PROMOTED_TWEET_SEARCH_QUALIFIED_IMPRESSIONS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_QUALIFIED_IMPRESSIONS),

        CONVERSION(TwitterAdStatistics.CONVERSION_CUSTOM, TwitterAdStatistics.CONVERSION_DOWNLOADS, TwitterAdStatistics.CONVERSION_ORDER_QUANTITY, TwitterAdStatistics.CONVERSION_PURCHASES, TwitterAdStatistics.CONVERSION_SALE_AMOUNT,
                TwitterAdStatistics.CONVERSION_SIGN_UPS, TwitterAdStatistics.CONVERSION_SITE_VISITS, TwitterAdStatistics.CONVERSION_APP_OPEN, TwitterAdStatistics.CONVERSION_KEY_PAGE_VIEWS),

        MEDIA(TwitterAdStatistics.PROMOTED_TWEET_PROFILE_MEDIA_VIEWS, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_MEDIA_VIEWS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_MEDIA_VIEWS,
                TwitterAdStatistics.PROMOTED_TWEET_TPN_MEDIA_VIEWS),

        MOBILE_APP_PROMOTION(TwitterAdStatistics.MOBILE_CONVERSION_ACHIEVEMENT_UNLOCKED, TwitterAdStatistics.MOBILE_CONVERSION_ACHIEVEMENT_UNLOCKED_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_ADD_TO_CART,
                TwitterAdStatistics.MOBILE_CONVERSION_ADD_TO_CART_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_ADD_TO_WISHLIST, TwitterAdStatistics.MOBILE_CONVERSION_ADD_TO_WISHLIST_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_ADDED_PAYMENT_INFOS, TwitterAdStatistics.MOBILE_CONVERSION_ADDED_PAYMENT_INFOS_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_CHECKOUT_INITIATED, TwitterAdStatistics.MOBILE_CONVERSION_CHECKOUT_INITIATED_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_CONTENT_VIEWS,
                TwitterAdStatistics.MOBILE_CONVERSION_CONTENT_VIEWS_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_INSTALLS, TwitterAdStatistics.MOBILE_CONVERSION_INSTALLS_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_INVITES, TwitterAdStatistics.MOBILE_CONVERSION_INVITES_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_LEVEL_ACHIEVED,
                TwitterAdStatistics.MOBILE_CONVERSION_LEVEL_ACHIEVED_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_LOGINS, TwitterAdStatistics.MOBILE_CONVERSION_LOGINS_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_ORDER_QUANTITY, TwitterAdStatistics.MOBILE_CONVERSION_PURCHASES, TwitterAdStatistics.MOBILE_CONVERSION_PURCHASES_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_RATED, TwitterAdStatistics.MOBILE_CONVERSION_RATED_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_RE_ENGAGES,
                TwitterAdStatistics.MOBILE_CONVERSION_RE_ENGAGES_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_RESERVATIONS, TwitterAdStatistics.MOBILE_CONVERSION_RESERVATIONS_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_SALE_AMOUNT_LOCAL_MICRO, TwitterAdStatistics.MOBILE_CONVERSION_SEARCHES, TwitterAdStatistics.MOBILE_CONVERSION_SEARCHES_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_SHARES, TwitterAdStatistics.MOBILE_CONVERSION_SHARES_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_SIGN_UPS,
                TwitterAdStatistics.MOBILE_CONVERSION_SIGN_UPS_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_SPENT_CREDITS, TwitterAdStatistics.MOBILE_CONVERSION_SPENT_CREDITS_BREAKDOWN,
                TwitterAdStatistics.MOBILE_CONVERSION_TUTORIAL_COMPLETED, TwitterAdStatistics.MOBILE_CONVERSION_TUTORIAL_COMPLETES_BREAKDOWN, TwitterAdStatistics.MOBILE_CONVERSION_UPDATES,
                TwitterAdStatistics.MOBILE_CONVERSION_UPDATES_BREAKDOWN, TwitterAdStatistics.PROMOTED_TWEET_APP_INSTALL_ATTEMPTS, TwitterAdStatistics.PROMOTED_TWEET_APP_OPEN_ATTEMPTS,
                TwitterAdStatistics.PROMOTED_TWEET_APP_INSTALL_ATTEMPTS_BREAKDOWN, TwitterAdStatistics.PROMOTED_TWEET_APP_OPEN_ATTEMPTS_BREAKDOWN),

        SPEND(TwitterAdStatistics.BILLED_CHARGE_LOCAL_MICRO),

        TWITTER_AUDIENCE_PLATFORM(TwitterAdStatistics.PROMOTED_TWEET_TPN_CARD_ENGAGEMENTS, TwitterAdStatistics.PROMOTED_TWEET_TPN_ENGAGEMENT_RATE, TwitterAdStatistics.PROMOTED_TWEET_TPN_ENGAGEMENTS,
                TwitterAdStatistics.PROMOTED_TWEET_TPN_CLICKS, TwitterAdStatistics.PROMOTED_TWEET_TPN_FAVORITES, TwitterAdStatistics.PROMOTED_TWEET_TPN_FOLLOWS, TwitterAdStatistics.PROMOTED_TWEET_TPN_IMPRESSIONS,
                TwitterAdStatistics.PROMOTED_TWEET_TPN_REPLIES, TwitterAdStatistics.PROMOTED_TWEET_TPN_RETWEETS, TwitterAdStatistics.PROMOTED_TWEET_TPN_URL_CLICKS,
                TwitterAdStatistics.PROMOTED_TWEET_TPN_QUALIFIED_IMPRESSIONS),

        VIDEO(TwitterAdStatistics.PROMOTED_VIDEO_CTA_CLICKS, TwitterAdStatistics.PROMOTED_VIDEO_REPLAYS, TwitterAdStatistics.PROMOTED_VIDEO_TOTAL_VIEWS, TwitterAdStatistics.PROMOTED_VIDEO_VIEWS_100, TwitterAdStatistics.PROMOTED_VIDEO_VIEWS_25,
                TwitterAdStatistics.PROMOTED_VIDEO_VIEWS_50, TwitterAdStatistics.PROMOTED_VIDEO_VIEWS_75),

        OTHER(TwitterAdStatistics.PROMOTED_ACCOUNT_FOLLOW_RATE, TwitterAdStatistics.PROMOTED_ACCOUNT_IMPRESSIONS, TwitterAdStatistics.PROMOTED_ACCOUNT_PROFILE_VISITS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_IMPRESSIONS,
                TwitterAdStatistics.PROMOTED_TWEET_SEARCH_ENGAGEMENT_RATE, TwitterAdStatistics.PROMOTED_TWEET_SEARCH_IMPRESSIONS, TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_ENGAGEMENT_RATE,
                TwitterAdStatistics.PROMOTED_TWEET_TIMELINE_IMPRESSIONS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_QUALIFIED_IMPRESSIONS, TwitterAdStatistics.PROMOTED_TWEET_PROFILE_ENGAGEMENT_RATE);

        private String[] metrics;

        MetricClass(String... metrics) {
            this.metrics = metrics;
        }

        public String[] getMetrics() {
            return this.metrics;
        }
    }

    enum AdObjective {
        APP_ENGAGEMENTS("app_engagements", MetricClass.ENGAGEMENT, MetricClass.MOBILE_APP_PROMOTION, MetricClass.SPEND,
                MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO, MetricClass.OTHER),

        APP_INSTALLS("app_installs", MetricClass.ENGAGEMENT, MetricClass.MOBILE_APP_PROMOTION, //MetricClass.MOBILE_LIFETIME_VALUE,
                MetricClass.OTHER, MetricClass.SPEND, MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO),

        BRAND_ENGAGEMENTS("brand_engagements", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.SPEND,
                MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO, MetricClass.OTHER),

        CUSTOM("custom", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.MOBILE_APP_PROMOTION,
                // MetricClass.MOBILE_LIFETIME_VALUE,
                MetricClass.SPEND, MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO, MetricClass.OTHER),

        FOLLOWERS("followers", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.MOBILE_APP_PROMOTION, MetricClass.SPEND,
                MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO, MetricClass.OTHER),

        LEAD_GENERATION("lead_generation", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.SPEND, MetricClass.TWITTER_AUDIENCE_PLATFORM,
                MetricClass.OTHER),

        PREROLL_VIEWS("preroll_views", MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.MOBILE_APP_PROMOTION, MetricClass.SPEND,
                MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO, MetricClass.OTHER),

        ENGAGEMENTS("engagements", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.SPEND,
                MetricClass.VIDEO, MetricClass.OTHER),

        QUALIFIED_VIEWS("qualified_views", MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.SPEND, MetricClass.VIDEO, MetricClass.OTHER),

        VIDEO_VIEWS("video_views", MetricClass.ENGAGEMENT, MetricClass.SPEND, MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.VIDEO,
                MetricClass.OTHER),

        WEBSITE_CLICKS("website_clicks", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.SPEND,
                MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.OTHER),

        WEBSITE_CONVERSIONS("website_conversions", MetricClass.CONVERSION, MetricClass.ENGAGEMENT, MetricClass.MEDIA, MetricClass.SPEND,
                MetricClass.TWITTER_AUDIENCE_PLATFORM, MetricClass.OTHER);

        private MetricClass[] metricClasses;
        private String objective;

        AdObjective(String objective, MetricClass... metricClasses) {
            this.metricClasses = metricClasses;
            this.objective = objective;
        }

        public MetricClass[] getMetricClasses() {
            return metricClasses;
        }

        public String getObjective() {
            return objective;
        }
    }
}