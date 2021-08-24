package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;
import twitter4jads.models.Granularity;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * Date: 17/02/14.
 */
public class TwitterAdStatistics extends TwitterEntity {
    public static final String SEGMENT = "segment";
    public static final String GRANULARITY = "granularity";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String PLACEMENT = "placement";

    //ENGAGEMENT METRICS
    public static final String ENGAGEMENTS = "engagements";
    public static final String IMPRESSIONS = "impressions";
    public static final String RETWEETS = "retweets";
    public static final String REPLIES = "replies";
    public static final String LIKES = "likes";
    public static final String FOLLOWS = "follows";
    public static final String CARD_ENGAGEMENTS = "card_engagements";
    public static final String CLICKS = "clicks";
    public static final String APP_CLICKS = "app_clicks";
    public static final String URL_CLICKS = "url_clicks";
    public static final String QUALIFIED_IMPRESSIONS = "qualified_impressions";
    public static final String CAROUSEL_SWIPES = "carousel_swipes";


    //BILLING METRICS
    public static final String BILLED_ENGAGEMENTS = "billed_engagements";
    public static final String BILLED_CHARGE_LOCAL_MICRO = "billed_charge_local_micro";

    //VIDEO METRICS
    public static final String VIDEO_TOTAL_VIEWS = "video_total_views";
    public static final String VIDEO_VIEWS_25 = "video_views_25";
    public static final String VIDEO_VIEWS_50 = "video_views_50";
    public static final String VIDEO_VIEWS_75 = "video_views_75";
    public static final String VIDEO_VIEWS_100 = "video_views_100";
    public static final String VIDEO_CTA_CLICKS = "video_cta_clicks";
    public static final String VIDEO_CONTENT_STARTS = "video_content_starts";
    public static final String VIDEO_MRC_VIEWS = "video_mrc_views";
    public static final String VIDEO_3S_100_VIEWS = "video_3s100pct_views";

    //MEDIA
    public static final String MEDIA_VIEWS = "media_views";
    public static final String MEDIA_ENGAGEMENTS = "media_engagements";

    //WEB_CONVERSIONS
    public static final String CONVERSION_PURCHASES = "conversion_purchases";
    public static final String CONVERSION_SIGN_UPS = "conversion_sign_ups";
    public static final String CONVERSION_SITE_VISITS = "conversion_site_visits";
    public static final String CONVERSION_DOWNLOADS = "conversion_downloads";
    public static final String CONVERSION_CUSTOM = "conversion_custom";

    //MOBILE_CONVERSION
    public static final String MOBILE_CONVERSION_SPENT_CREDITS = "mobile_conversion_spent_credits";
    public static final String MOBILE_CONVERSION_INSTALLS = "mobile_conversion_installs";
    public static final String MOBILE_CONVERSION_CONTENT_VIEWS = "mobile_conversion_content_views";
    public static final String MOBILE_CONVERSION_ADD_TO_WISHLISTS = "mobile_conversion_add_to_wishlists";
    public static final String MOBILE_CONVERSION_CHECKOUTS_INITIATIED = "mobile_conversion_checkouts_initiated";
    public static final String MOBILE_CONVERSION_RESERVATIONS = "mobile_conversion_reservations";
    public static final String MOBILE_CONVERSION_TUTORIALS_COMPLETED = "mobile_conversion_tutorials_completed";
    public static final String MOBILE_CONVERSION_ACHIEVEMENTS_UNLOCKED = "mobile_conversion_achievements_unlocked";
    public static final String MOBILE_CONVERSION_SEARCHES = "mobile_conversion_searches";
    public static final String MOBILE_CONVERSION_ADD_TO_CARTS = "mobile_conversion_add_to_carts";
    public static final String MOBILE_CONVERSION_PAYMENT_INFO_ADDITIONS = "mobile_conversion_payment_info_additions";
    public static final String MOBILE_CONVERSION_RE_ENGAGES = "mobile_conversion_re_engages";
    public static final String MOBILE_CONVERSION_SHARES = "mobile_conversion_shares";
    public static final String MOBILE_CONVERSION_RATES = "mobile_conversion_rates";
    public static final String MOBILE_CONVERSION_LOGINS = "mobile_conversion_logins";
    public static final String MOBILE_CONVERSION_UPDATES = "mobile_conversion_updates";
    public static final String MOBILE_CONVERSION_LEVELS_ACHIEVED = "mobile_conversion_levels_achieved";
    public static final String MOBILE_CONVERSION_INVITES = "mobile_conversion_invites";
    public static final String MOBILE_CONVERSION_KEY_PAGE_VIEWS = "mobile_conversion_key_page_views";
    public static final String MOBILE_CONVERSION_SITE_VISITS = "mobile_conversion_site_visits";
    public static final String MOBILE_CONVERSION_PURCHASES = "mobile_conversion_purchases";
    public static final String MOBILE_CONVERSION_DOWNLOADS = "mobile_conversion_downloads";
    public static final String MOBILE_CONVERSION_SIGN_UPS = "mobile_conversion_sign_ups";


    //LIFE_TIME_VALUE_MOBILE_CONVERSION
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_PURCHASES = "mobile_conversion_lifetime_value_purchases";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_SIGN_UPS = "mobile_conversion_lifetime_value_sign_ups";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_UPDATES = "mobile_conversion_lifetime_value_updates";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_TUTORIALS_COMPLETED = "mobile_conversion_lifetime_value_tutorials_completed";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_RESERVATIONS = "mobile_conversion_lifetime_value_reservations";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_ADD_TO_CARTS = "mobile_conversion_lifetime_value_add_to_carts";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_ADD_TO_WISHLISTS = "mobile_conversion_lifetime_value_add_to_wishlists";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_CHECKOUTS_INITIATED = "mobile_conversion_lifetime_value_checkouts_initiated";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_LEVELS_ACHIEVED = "mobile_conversion_lifetime_value_levels_achieved";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_ACHIEVEMENTS_UNLOCKED = "mobile_conversion_lifetime_value_achievements_unlocked";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_SHARES = "mobile_conversion_lifetime_value_shares";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_INVITES = "mobile_conversion_lifetime_value_invites";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_PAYMENT_INFO_ADDITIONS = "mobile_conversion_lifetime_value_payment_info_additions";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_SPENT_CREDITS = "mobile_conversion_lifetime_value_spent_credits";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_RATES = "mobile_conversion_lifetime_value_rates";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_LOGINS = "mobile_conversion_lifetime_value_logins";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_SEARCHES = "mobile_conversion_lifetime_value_searches";
    public static final String MOBILE_CONVERSION_LIFETIME_VALUE_CONTENT_VIEWS = "mobile_conversion_lifetime_value_content_views";

    public static final String BILLED_FOLLOWS = "billed_follows";
    public static final String ESTIMATED_CHARGE_LOCAL_MICRO = "estimated_charge_local_micro";

    public static final String CONVERSION_APP_OPEN = "conversion_app_open";
    public static final String CONVERSION_KEY_PAGE_VIEWS = "conversion_key_page_views";
    public static final String CONVERSION_ORDER_QUANTITY = "conversion_order_quantity";
    public static final String CONVERSION_SALE_AMOUNT = "conversion_sale_amount";

    public static final String PROMOTION_CARD_RESPONSES = "promotion_card_responses";
    public static final String PROMOTED_TWEET_APP_INSTALL_ATTEMPTS = "promoted_tweet_app_install_attempts";
    public static final String PROMOTED_TWEET_APP_OPEN_ATTEMPTS = "promoted_tweet_app_open_attempts";
    public static final String PROMOTED_TWEET_APP_OPEN_ATTEMPTS_BREAKDOWN = "promoted_tweet_app_open_attempts_breakdown";
    public static final String PROMOTED_TWEET_APP_INSTALL_ATTEMPTS_BREAKDOWN = "promoted_tweet_app_install_attempts_breakdown";

    public static final String PROMOTED_VIDEO_TOTAL_VIEWS = "promoted_video_total_views";
    public static final String PROMOTED_VIDEO_REPLAYS = "promoted_video_replays";
    public static final String PROMOTED_VIDEO_VIEWS_100 = "promoted_video_views_100";
    public static final String PROMOTED_VIDEO_VIEWS_75 = "promoted_video_views_75";
    public static final String PROMOTED_VIDEO_VIEWS_50 = "promoted_video_views_50";
    public static final String PROMOTED_VIDEO_VIEWS_25 = "promoted_video_views_25";
    public static final String PROMOTED_VIDEO_CTA_CLICKS = "promoted_video_cta_clicks";

    public static final String MOBILE_CONVERSION_TUTORIAL_COMPLETED = "mobile_conversion_tutorial_completes";
    public static final String MOBILE_CONVERSION_ADD_TO_CART = "mobile_conversion_add_to_cart";
    public static final String MOBILE_CONVERSION_ADD_TO_WISHLIST = "mobile_conversion_add_to_wishlist";
    public static final String MOBILE_CONVERSION_CHECKOUT_INITIATED = "mobile_conversion_checkout_initiated";
    public static final String MOBILE_CONVERSION_LEVEL_ACHIEVED = "mobile_conversion_level_achieved";
    public static final String MOBILE_CONVERSION_ACHIEVEMENT_UNLOCKED = "mobile_conversion_achievement_unlocked";
    public static final String MOBILE_CONVERSION_ADDED_PAYMENT_INFOS = "mobile_conversion_added_payment_infos";
    public static final String MOBILE_CONVERSION_RATED = "mobile_conversion_rated";
    public static final String MOBILE_CONVERSION_SALE_AMOUNT_LOCAL_MICRO = "mobile_conversion_sale_amount_local_micro";
    public static final String MOBILE_CONVERSION_ORDER_QUANTITY = "mobile_conversion_order_quantity";

    public static final String MOBILE_LIFETIME_VALUE_CONVERSION_BREAKDOWN = "mobile_lifetime_value_conversion_breakdown";
    public static final String MOBILE_LIFETIME_VALUE_CONVERSION_SALE_AMOUNT_LOCAL_MICRO_BREAKDOWN = "mobile_lifetime_value_conversion_sale_amount_local_micro_breakdown";
    public static final String MOBILE_LIFETIME_VALUE_CONVERSION_ORDER_QUANTITY_BREAKDOWN = "mobile_lifetime_value_conversion_order_quantity_breakdown";

    public static final String MOBILE_CONVERSION_INVITES_BREAKDOWN = "mobile_conversion_invites_breakdown";
    public static final String MOBILE_CONVERSION_RE_ENGAGES_BREAKDOWN = "mobile_conversion_re_engages_breakdown";
    public static final String MOBILE_CONVERSION_ADD_TO_CART_BREAKDOWN = "mobile_conversion_add_to_cart_breakdown";
    public static final String MOBILE_CONVERSION_CONTENT_VIEWS_BREAKDOWN = "mobile_conversion_content_views_breakdown";
    public static final String MOBILE_CONVERSION_SPENT_CREDITS_BREAKDOWN = "mobile_conversion_spent_credits_breakdown";
    public static final String MOBILE_CONVERSION_SHARES_BREAKDOWN = "mobile_conversion_shares_breakdown";
    public static final String MOBILE_CONVERSION_SEARCHES_BREAKDOWN = "mobile_conversion_searches_breakdown";
    public static final String MOBILE_CONVERSION_ADDED_PAYMENT_INFOS_BREAKDOWN = "mobile_conversion_added_payment_infos_breakdown";
    public static final String MOBILE_CONVERSION_ACHIEVEMENT_UNLOCKED_BREAKDOWN = "mobile_conversion_achievement_unlocked_breakdown";
    public static final String MOBILE_CONVERSION_ADD_TO_WISHLIST_BREAKDOWN = "mobile_conversion_add_to_wishlist_breakdown";
    public static final String MOBILE_CONVERSION_LEVEL_ACHIEVED_BREAKDOWN = "mobile_conversion_level_achieved_breakdown";
    public static final String MOBILE_CONVERSION_RESERVATIONS_BREAKDOWN = "mobile_conversion_reservations_breakdown";
    public static final String MOBILE_CONVERSION_SIGN_UPS_BREAKDOWN = "mobile_conversion_sign_ups_breakdown";
    public static final String MOBILE_CONVERSION_LOGINS_BREAKDOWN = "mobile_conversion_logins_breakdown";
    public static final String MOBILE_CONVERSION_RATED_BREAKDOWN = "mobile_conversion_rated_breakdown";
    public static final String MOBILE_CONVERSION_UPDATES_BREAKDOWN = "mobile_conversion_updates_breakdown";
    public static final String MOBILE_CONVERSION_CHECKOUT_INITIATED_BREAKDOWN = "mobile_conversion_checkout_initiated_breakdown";
    public static final String MOBILE_CONVERSION_INSTALLS_BREAKDOWN = "mobile_conversion_installs_breakdown";
    public static final String MOBILE_CONVERSION_PURCHASES_BREAKDOWN = "mobile_conversion_purchases_breakdown";
    public static final String MOBILE_CONVERSION_TUTORIAL_COMPLETES_BREAKDOWN = "mobile_conversion_tutorial_completes_breakdown";

    public static final String PROMOTED_ACCOUNT_FOLLOW_RATE = "promoted_account_follow_rate";
    public static final String PROMOTED_ACCOUNT_IMPRESSIONS = "promoted_account_impressions";
    public static final String PROMOTED_ACCOUNT_PROFILE_VISITS = "promoted_account_profile_visits";
    public static final String PROMOTED_ACCOUNT_FOLLOWS = "promoted_account_follows";

    public static final String PROMOTED_TWEET_SEARCH_CLICKS = "promoted_tweet_search_clicks";
    public static final String PROMOTED_TWEET_SEARCH_URL_CLICKS = "promoted_tweet_search_url_clicks";
    public static final String PROMOTED_TWEET_SEARCH_IMPRESSIONS = "promoted_tweet_search_impressions";
    public static final String PROMOTED_TWEET_SEARCH_FOLLOWS = "promoted_tweet_search_follows";
    public static final String PROMOTED_TWEET_SEARCH_ENGAGEMENTS = "promoted_tweet_search_engagements";
    public static final String PROMOTED_TWEET_SEARCH_CARD_ENGAGEMENTS = "promoted_tweet_search_card_engagements";
    public static final String PROMOTED_TWEET_SEARCH_ENGAGEMENT_RATE = "promoted_tweet_search_engagement_rate";
    public static final String PROMOTED_TWEET_SEARCH_REPLIES = "promoted_tweet_search_replies";
    public static final String PROMOTED_TWEET_SEARCH_RETWEETS = "promoted_tweet_search_retweets";
    public static final String PROMOTED_TWEET_SEARCH_FAVOURITES = "promoted_tweet_search_favorites";
    public static final String PROMOTED_TWEET_SEARCH_QUALIFIED_IMPRESSIONS = "promoted_tweet_search_qualified_impressions";

    public static final String PROMOTED_TWEET_TIMELINE_CLICKS = "promoted_tweet_timeline_clicks";
    public static final String PROMOTED_TWEET_TIMELINE_URL_CLICKS = "promoted_tweet_timeline_url_clicks";
    public static final String PROMOTED_TWEET_TIMELINE_IMPRESSIONS = "promoted_tweet_timeline_impressions";
    public static final String PROMOTED_TWEET_TIMELINE_FOLLOWS = "promoted_tweet_timeline_follows";
    public static final String PROMOTED_TWEET_TIMELINE_ENGAGEMENTS = "promoted_tweet_timeline_engagements";
    public static final String PROMOTED_TWEET_TIMELINE_CARD_ENGAGEMENTS = "promoted_tweet_timeline_card_engagements";
    public static final String PROMOTED_TWEET_TIMELINE_ENGAGEMENT_RATE = "promoted_tweet_timeline_engagement_rate";
    public static final String PROMOTED_TWEET_TIMELINE_REPLIES = "promoted_tweet_timeline_replies";
    public static final String PROMOTED_TWEET_TIMELINE_RETWEETS = "promoted_tweet_timeline_retweets";
    public static final String PROMOTED_TWEET_TIMELINE_FAVOURITES = "promoted_tweet_timeline_favorites";
    public static final String PROMOTED_TWEET_TIMELINE_QUALIFIED_IMPRESSIONS = "promoted_tweet_timeline_qualified_impressions";

    public static final String PROMOTED_TWEET_PROFILE_CLICKS = "promoted_tweet_profile_clicks";
    public static final String PROMOTED_TWEET_PROFILE_URL_CLICKS = "promoted_tweet_profile_url_clicks";
    public static final String PROMOTED_TWEET_PROFILE_IMPRESSIONS = "promoted_tweet_profile_impressions";
    public static final String PROMOTED_TWEET_PROFILE_FOLLOWS = "promoted_tweet_profile_follows";
    public static final String PROMOTED_TWEET_PROFILE_ENGAGEMENTS = "promoted_tweet_profile_engagements";
    public static final String PROMOTED_TWEET_PROFILE_CARD_ENGAGEMENTS = "promoted_tweet_profile_card_engagements";
    public static final String PROMOTED_TWEET_PROFILE_ENGAGEMENT_RATE = "promoted_tweet_profile_engagement_rate";
    public static final String PROMOTED_TWEET_PROFILE_REPLIES = "promoted_tweet_profile_replies";
    public static final String PROMOTED_TWEET_PROFILE_RETWEETS = "promoted_tweet_profile_retweets";
    public static final String PROMOTED_TWEET_PROFILE_FAVORITES = "promoted_tweet_profile_favorites";
    public static final String PROMOTED_TWEET_PROFILE_QUALIFIED_IMPRESSIONS = "promoted_tweet_profile_qualified_impressions";

    public static final String PROMOTED_TWEET_TPN_CLICKS = "promoted_tweet_tpn_clicks";
    public static final String PROMOTED_TWEET_TPN_URL_CLICKS = "promoted_tweet_tpn_url_clicks";
    public static final String PROMOTED_TWEET_TPN_ENGAGEMENTS = "promoted_tweet_tpn_engagements";
    public static final String PROMOTED_TWEET_TPN_FOLLOWS = "promoted_tweet_tpn_follows";
    public static final String PROMOTED_TWEET_TPN_IMPRESSIONS = "promoted_tweet_tpn_impressions";
    public static final String PROMOTED_TWEET_TPN_REPLIES = "promoted_tweet_tpn_replies";
    public static final String PROMOTED_TWEET_TPN_RETWEETS = "promoted_tweet_tpn_retweets";
    public static final String PROMOTED_TWEET_TPN_FAVORITES = "promoted_tweet_tpn_favorites";
    public static final String PROMOTED_TWEET_TPN_ENGAGEMENT_RATE = "promoted_tweet_tpn_engagement_rate";
    public static final String PROMOTED_TWEET_TPN_CARD_ENGAGEMENTS = "promoted_tweet_tpn_card_engagements";
    public static final String PROMOTED_TWEET_TPN_QUALIFIED_IMPRESSIONS = "promoted_tweet_tpn_qualified_impressions";

    public static final String PROMOTED_TWEET_TIMELINE_MEDIA_VIEWS = "promoted_tweet_timeline_media_views";
    public static final String PROMOTED_TWEET_SEARCH_MEDIA_VIEWS = "promoted_tweet_search_media_views";
    public static final String PROMOTED_TWEET_PROFILE_MEDIA_VIEWS = "promoted_tweet_profile_media_views";
    public static final String PROMOTED_TWEET_TPN_MEDIA_VIEWS = "promoted_tweet_tpn_media_views";

    @SerializedName(START_TIME)
    private Date startTime;

    @SerializedName(END_TIME)
    private Date endTime;

    @SerializedName(GRANULARITY)
    private Granularity granularity;

    @SerializedName(PLACEMENT)
    private String placement;

    @SerializedName(ENGAGEMENTS)
    private String[] engagements;

    @SerializedName(IMPRESSIONS)
    private String[] impressions;

    @SerializedName(RETWEETS)
    private String[] retweets;

    @SerializedName(REPLIES)
    private String[] replies;

    @SerializedName(LIKES)
    private String[] likes;

    @SerializedName(FOLLOWS)
    private String[] follows;

    @SerializedName(CARD_ENGAGEMENTS)
    private String[] cardEngagements;

    @SerializedName(CLICKS)
    private String[] clicks;

    @SerializedName(APP_CLICKS)
    private String[] appClicks;

    @SerializedName(URL_CLICKS)
    private String[] urlClicks;

    @SerializedName(QUALIFIED_IMPRESSIONS)
    private String[] qualifiedImpressions;

    @SerializedName(CAROUSEL_SWIPES)
    private String[] carouselSwipes;

    @SerializedName(BILLED_ENGAGEMENTS)
    private String[] billedEngagements;

    @SerializedName(BILLED_CHARGE_LOCAL_MICRO)
    private String[] billedChargeLocalMicro;

    @SerializedName(VIDEO_TOTAL_VIEWS)
    private String[] videoTotalViews;

    @SerializedName(VIDEO_VIEWS_25)
    private String[] videoViews25;

    @SerializedName(VIDEO_VIEWS_50)
    private String[] videoViews50;

    @SerializedName(VIDEO_VIEWS_75)
    private String[] videoViews75;

    @SerializedName(VIDEO_VIEWS_100)
    private String[] videoViews100;

    @SerializedName(VIDEO_CTA_CLICKS)
    private String[] videoCtaClicks;

    @SerializedName(VIDEO_CONTENT_STARTS)
    private String[] videoContentStarts;

    @SerializedName(VIDEO_MRC_VIEWS)
    private String[] videoMrcViews;

    @SerializedName(VIDEO_3S_100_VIEWS)
    private String[] video3s100PercentViews;

    @SerializedName(MEDIA_VIEWS)
    private String[] mediaViews;

    @SerializedName(MEDIA_ENGAGEMENTS)
    private String[] mediaEngagements;

    @SerializedName(SEGMENT)
    private NewSegment segment;

    @SerializedName(CONVERSION_PURCHASES)
    private TwitterAdStatsBreakdown conversionPurchases;

    @SerializedName(CONVERSION_SIGN_UPS)
    private TwitterAdStatsBreakdown conversionSignUps;

    @SerializedName(CONVERSION_SITE_VISITS)
    private TwitterAdStatsBreakdown conversionSiteVisits;

    @SerializedName(CONVERSION_DOWNLOADS)
    private TwitterAdStatsBreakdown conversionDownloads;

    @SerializedName(CONVERSION_CUSTOM)
    private TwitterAdStatsBreakdown conversionCustom;

    @SerializedName(MOBILE_CONVERSION_SPENT_CREDITS)
    private TwitterAdStatsBreakdown mobileConversionSpentCredits;

    @SerializedName(MOBILE_CONVERSION_INSTALLS)
    private TwitterAdStatsBreakdown mobileConversionInstalls;

    @SerializedName(MOBILE_CONVERSION_CONTENT_VIEWS)
    private TwitterAdStatsBreakdown mobileConversionContentViews;

    @SerializedName(MOBILE_CONVERSION_ADD_TO_WISHLISTS)
    private TwitterAdStatsBreakdown mobileConversionAddToWishlist;

    @SerializedName(MOBILE_CONVERSION_CHECKOUTS_INITIATIED)
    private TwitterAdStatsBreakdown mobileConversionCheckoutInitiated;

    @SerializedName(MOBILE_CONVERSION_RESERVATIONS)
    private TwitterAdStatsBreakdown mobileConversionReservations;

    @SerializedName(MOBILE_CONVERSION_TUTORIALS_COMPLETED)
    private TwitterAdStatsBreakdown mobileConversionTutorialsCompleted;

    @SerializedName(MOBILE_CONVERSION_ACHIEVEMENTS_UNLOCKED)
    private TwitterAdStatsBreakdown mobileConversionAchievementsUnlocked;

    @SerializedName(MOBILE_CONVERSION_SEARCHES)
    private TwitterAdStatsBreakdown mobileConversionSearches;

    @SerializedName(MOBILE_CONVERSION_ADD_TO_CARTS)
    private TwitterAdStatsBreakdown mobileConversionAddToCarts;

    @SerializedName(MOBILE_CONVERSION_PAYMENT_INFO_ADDITIONS)
    private TwitterAdStatsBreakdown mobileConversionPaymentInfoAdditions;

    @SerializedName(MOBILE_CONVERSION_RE_ENGAGES)
    private TwitterAdStatsBreakdown mobileConversionReengages;

    @SerializedName(MOBILE_CONVERSION_SHARES)
    private TwitterAdStatsBreakdown mobileConversionShares;

    @SerializedName(MOBILE_CONVERSION_RATES)
    private TwitterAdStatsBreakdown mobileConversionRates;

    @SerializedName(MOBILE_CONVERSION_LOGINS)
    private TwitterAdStatsBreakdown mobileConversionLogins;

    @SerializedName(MOBILE_CONVERSION_UPDATES)
    private TwitterAdStatsBreakdown mobileConversionUpdates;

    @SerializedName(MOBILE_CONVERSION_LEVELS_ACHIEVED)
    private TwitterAdStatsBreakdown mobileConversionLevelsAchieved;

    @SerializedName(MOBILE_CONVERSION_INVITES)
    private TwitterAdStatsBreakdown mobileConversionInvites;

    @SerializedName(MOBILE_CONVERSION_KEY_PAGE_VIEWS)
    private TwitterAdStatsBreakdown mobileConversionKeyPageViews;

    @SerializedName(MOBILE_CONVERSION_SITE_VISITS)
    private TwitterAdStatsBreakdown mobileConversionSiteVisits;

    @SerializedName(MOBILE_CONVERSION_PURCHASES)
    private TwitterAdStatsBreakdown mobileConversionPurchases;

    @SerializedName(MOBILE_CONVERSION_DOWNLOADS)
    private TwitterAdStatsBreakdown mobileConversionDownloads;

    @SerializedName(MOBILE_CONVERSION_SIGN_UPS)
    private TwitterAdStatsBreakdown mobileConversionSignUps;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_PURCHASES)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValuePurchases;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_SIGN_UPS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueSignUps;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_UPDATES)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueUpdates;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_TUTORIALS_COMPLETED)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueTutorialsCompleted;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_RESERVATIONS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueReservations;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_ADD_TO_CARTS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueAddToCarts;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_ADD_TO_WISHLISTS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueAddToWishlists;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_CHECKOUTS_INITIATED)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueCheckoutsInitiated;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_LEVELS_ACHIEVED)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueLevelsAchieved;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_ACHIEVEMENTS_UNLOCKED)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueAchievementsUnlocked;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_SHARES)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueShares;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_INVITES)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueInvites;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_PAYMENT_INFO_ADDITIONS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValuePaymentInfoAdditions;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_SPENT_CREDITS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueSpentCredits;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_RATES)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueRates;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_LOGINS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueLogins;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_SEARCHES)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueSearches;

    @SerializedName(MOBILE_CONVERSION_LIFETIME_VALUE_CONTENT_VIEWS)
    private TwitterAdStatsBreakdown mobileConversionLifetimeValueContentViews;


    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueRates() {
        return mobileConversionLifetimeValueRates;
    }

    public void setMobileConversionLifetimeValueRates(TwitterAdStatsBreakdown mobileConversionLifetimeValueRates) {
        this.mobileConversionLifetimeValueRates = mobileConversionLifetimeValueRates;
    }

    public TwitterAdStatsBreakdown getMobileConversionShares() {
        return mobileConversionShares;
    }

    public void setMobileConversionShares(TwitterAdStatsBreakdown mobileConversionShares) {
        this.mobileConversionShares = mobileConversionShares;
    }

    public TwitterAdStatsBreakdown getMobileConversionRates() {
        return mobileConversionRates;
    }

    public void setMobileConversionRates(TwitterAdStatsBreakdown mobileConversionRates) {
        this.mobileConversionRates = mobileConversionRates;
    }

    public TwitterAdStatsBreakdown getMobileConversionUpdates() {
        return mobileConversionUpdates;
    }

    public void setMobileConversionUpdates(TwitterAdStatsBreakdown mobileConversionUpdates) {
        this.mobileConversionUpdates = mobileConversionUpdates;
    }

    public TwitterAdStatsBreakdown getMobileConversionLevelsAchieved() {
        return mobileConversionLevelsAchieved;
    }

    public void setMobileConversionLevelsAchieved(TwitterAdStatsBreakdown mobileConversionLevelsAchieved) {
        this.mobileConversionLevelsAchieved = mobileConversionLevelsAchieved;
    }

    public TwitterAdStatsBreakdown getMobileConversionInvites() {
        return mobileConversionInvites;
    }

    public void setMobileConversionInvites(TwitterAdStatsBreakdown mobileConversionInvites) {
        this.mobileConversionInvites = mobileConversionInvites;
    }

    public TwitterAdStatsBreakdown getMobileConversionKeyPageViews() {
        return mobileConversionKeyPageViews;
    }

    public void setMobileConversionKeyPageViews(TwitterAdStatsBreakdown mobileConversionKeyPageViews) {
        this.mobileConversionKeyPageViews = mobileConversionKeyPageViews;
    }

    public TwitterAdStatsBreakdown getMobileConversionLogins() {
        return mobileConversionLogins;
    }

    public void setMobileConversionLogins(TwitterAdStatsBreakdown mobileConversionLogins) {
        this.mobileConversionLogins = mobileConversionLogins;
    }

    public TwitterAdStatsBreakdown getMobileConversionSiteVisits() {
        return mobileConversionSiteVisits;
    }

    public void setMobileConversionSiteVisits(TwitterAdStatsBreakdown mobileConversionSiteVisits) {
        this.mobileConversionSiteVisits = mobileConversionSiteVisits;
    }

    public TwitterAdStatsBreakdown getMobileConversionPurchases() {
        return mobileConversionPurchases;
    }

    public void setMobileConversionPurchases(TwitterAdStatsBreakdown mobileConversionPurchases) {
        this.mobileConversionPurchases = mobileConversionPurchases;
    }

    public TwitterAdStatsBreakdown getMobileConversionDownloads() {
        return mobileConversionDownloads;
    }

    public void setMobileConversionDownloads(TwitterAdStatsBreakdown mobileConversionDownloads) {
        this.mobileConversionDownloads = mobileConversionDownloads;
    }

    public TwitterAdStatsBreakdown getMobileConversionSignUps() {
        return mobileConversionSignUps;
    }

    public void setMobileConversionSignUps(TwitterAdStatsBreakdown mobileConversionSignUps) {
        this.mobileConversionSignUps = mobileConversionSignUps;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValuePurchases() {
        return mobileConversionLifetimeValuePurchases;
    }

    public void setMobileConversionLifetimeValuePurchases(TwitterAdStatsBreakdown mobileConversionLifetimeValuePurchases) {
        this.mobileConversionLifetimeValuePurchases = mobileConversionLifetimeValuePurchases;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueSignUps() {
        return mobileConversionLifetimeValueSignUps;
    }

    public void setMobileConversionLifetimeValueSignUps(TwitterAdStatsBreakdown mobileConversionLifetimeValueSignUps) {
        this.mobileConversionLifetimeValueSignUps = mobileConversionLifetimeValueSignUps;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueUpdates() {
        return mobileConversionLifetimeValueUpdates;
    }

    public void setMobileConversionLifetimeValueUpdates(TwitterAdStatsBreakdown mobileConversionLifetimeValueUpdates) {
        this.mobileConversionLifetimeValueUpdates = mobileConversionLifetimeValueUpdates;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueTutorialsCompleted() {
        return mobileConversionLifetimeValueTutorialsCompleted;
    }

    public void setMobileConversionLifetimeValueTutorialsCompleted(TwitterAdStatsBreakdown mobileConversionLifetimeValueTutorialsCompleted) {
        this.mobileConversionLifetimeValueTutorialsCompleted = mobileConversionLifetimeValueTutorialsCompleted;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueReservations() {
        return mobileConversionLifetimeValueReservations;
    }

    public void setMobileConversionLifetimeValueReservations(TwitterAdStatsBreakdown mobileConversionLifetimeValueReservations) {
        this.mobileConversionLifetimeValueReservations = mobileConversionLifetimeValueReservations;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueAddToCarts() {
        return mobileConversionLifetimeValueAddToCarts;
    }

    public void setMobileConversionLifetimeValueAddToCarts(TwitterAdStatsBreakdown mobileConversionLifetimeValueAddToCarts) {
        this.mobileConversionLifetimeValueAddToCarts = mobileConversionLifetimeValueAddToCarts;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueAddToWishlists() {
        return mobileConversionLifetimeValueAddToWishlists;
    }

    public void setMobileConversionLifetimeValueAddToWishlists(TwitterAdStatsBreakdown mobileConversionLifetimeValueAddToWishlists) {
        this.mobileConversionLifetimeValueAddToWishlists = mobileConversionLifetimeValueAddToWishlists;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueCheckoutsInitiated() {
        return mobileConversionLifetimeValueCheckoutsInitiated;
    }

    public void setMobileConversionLifetimeValueCheckoutsInitiated(TwitterAdStatsBreakdown mobileConversionLifetimeValueCheckoutsInitiated) {
        this.mobileConversionLifetimeValueCheckoutsInitiated = mobileConversionLifetimeValueCheckoutsInitiated;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueLevelsAchieved() {
        return mobileConversionLifetimeValueLevelsAchieved;
    }

    public void setMobileConversionLifetimeValueLevelsAchieved(TwitterAdStatsBreakdown mobileConversionLifetimeValueLevelsAchieved) {
        this.mobileConversionLifetimeValueLevelsAchieved = mobileConversionLifetimeValueLevelsAchieved;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueAchievementsUnlocked() {
        return mobileConversionLifetimeValueAchievementsUnlocked;
    }

    public void setMobileConversionLifetimeValueAchievementsUnlocked(TwitterAdStatsBreakdown mobileConversionLifetimeValueAchievementsUnlocked) {
        this.mobileConversionLifetimeValueAchievementsUnlocked = mobileConversionLifetimeValueAchievementsUnlocked;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueShares() {
        return mobileConversionLifetimeValueShares;
    }

    public void setMobileConversionLifetimeValueShares(TwitterAdStatsBreakdown mobileConversionLifetimeValueShares) {
        this.mobileConversionLifetimeValueShares = mobileConversionLifetimeValueShares;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueInvites() {
        return mobileConversionLifetimeValueInvites;
    }

    public void setMobileConversionLifetimeValueInvites(TwitterAdStatsBreakdown mobileConversionLifetimeValueInvites) {
        this.mobileConversionLifetimeValueInvites = mobileConversionLifetimeValueInvites;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValuePaymentInfoAdditions() {
        return mobileConversionLifetimeValuePaymentInfoAdditions;
    }

    public void setMobileConversionLifetimeValuePaymentInfoAdditions(TwitterAdStatsBreakdown mobileConversionLifetimeValuePaymentInfoAdditions) {
        this.mobileConversionLifetimeValuePaymentInfoAdditions = mobileConversionLifetimeValuePaymentInfoAdditions;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueSpentCredits() {
        return mobileConversionLifetimeValueSpentCredits;
    }

    public void setMobileConversionLifetimeValueSpentCredits(TwitterAdStatsBreakdown mobileConversionLifetimeValueSpentCredits) {
        this.mobileConversionLifetimeValueSpentCredits = mobileConversionLifetimeValueSpentCredits;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueSearches() {
        return mobileConversionLifetimeValueSearches;
    }

    public void setMobileConversionLifetimeValueSearches(TwitterAdStatsBreakdown mobileConversionLifetimeValueSearches) {
        this.mobileConversionLifetimeValueSearches = mobileConversionLifetimeValueSearches;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public String[] getEngagements() {
        return engagements;
    }

    public void setEngagements(String[] engagements) {
        this.engagements = engagements;
    }

    public String[] getImpressions() {
        return impressions;
    }

    public void setImpressions(String[] impressions) {
        this.impressions = impressions;
    }

    public String[] getRetweets() {
        return retweets;
    }

    public void setRetweets(String[] retweets) {
        this.retweets = retweets;
    }

    public String[] getReplies() {
        return replies;
    }

    public void setReplies(String[] replies) {
        this.replies = replies;
    }

    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String[] getFollows() {
        return follows;
    }

    public void setFollows(String[] follows) {
        this.follows = follows;
    }

    public String[] getCardEngagements() {
        return cardEngagements;
    }

    public void setCardEngagements(String[] cardEngagements) {
        this.cardEngagements = cardEngagements;
    }

    public String[] getClicks() {
        return clicks;
    }

    public void setClicks(String[] clicks) {
        this.clicks = clicks;
    }

    public String[] getAppClicks() {
        return appClicks;
    }

    public void setAppClicks(String[] appClicks) {
        this.appClicks = appClicks;
    }

    public String[] getUrlClicks() {
        return urlClicks;
    }

    public void setUrlClicks(String[] urlClicks) {
        this.urlClicks = urlClicks;
    }

    public String[] getQualifiedImpressions() {
        return qualifiedImpressions;
    }

    public void setQualifiedImpressions(String[] qualifiedImpressions) {
        this.qualifiedImpressions = qualifiedImpressions;
    }

    public String[] getBilledEngagements() {
        return billedEngagements;
    }

    public void setBilledEngagements(String[] billedEngagements) {
        this.billedEngagements = billedEngagements;
    }

    public String[] getBilledChargeLocalMicro() {
        return billedChargeLocalMicro;
    }

    public void setBilledChargeLocalMicro(String[] billedChargeLocalMicro) {
        this.billedChargeLocalMicro = billedChargeLocalMicro;
    }

    public String[] getVideoTotalViews() {
        return videoTotalViews;
    }

    public void setVideoTotalViews(String[] videoTotalViews) {
        this.videoTotalViews = videoTotalViews;
    }

    public String[] getVideoViews25() {
        return videoViews25;
    }

    public void setVideoViews25(String[] videoViews25) {
        this.videoViews25 = videoViews25;
    }

    public String[] getVideoViews50() {
        return videoViews50;
    }

    public void setVideoViews50(String[] videoViews50) {
        this.videoViews50 = videoViews50;
    }

    public String[] getVideoViews75() {
        return videoViews75;
    }

    public void setVideoViews75(String[] videoViews75) {
        this.videoViews75 = videoViews75;
    }

    public String[] getVideoViews100() {
        return videoViews100;
    }

    public void setVideoViews100(String[] videoViews100) {
        this.videoViews100 = videoViews100;
    }

    public String[] getVideoCtaClicks() {
        return videoCtaClicks;
    }

    public void setVideoCtaClicks(String[] videoCtaClicks) {
        this.videoCtaClicks = videoCtaClicks;
    }

    public String[] getVideoContentStarts() {
        return videoContentStarts;
    }

    public void setVideoContentStarts(String[] videoContentStarts) {
        this.videoContentStarts = videoContentStarts;
    }

    public String[] getVideoMrcViews() {
        return videoMrcViews;
    }

    public void setVideoMrcViews(String[] videoMrcViews) {
        this.videoMrcViews = videoMrcViews;
    }

    public String[] getVideo3s100PercentViews() {
        return video3s100PercentViews;
    }

    public void setVideo3s100PercentViews(String[] video3s100PercentViews) {
        this.video3s100PercentViews = video3s100PercentViews;
    }

    public String[] getMediaViews() {
        return mediaViews;
    }

    public void setMediaViews(String[] mediaViews) {
        this.mediaViews = mediaViews;
    }

    public NewSegment getSegment() {
        return segment;
    }

    public void setSegment(NewSegment segment) {
        this.segment = segment;
    }

    public TwitterAdStatsBreakdown getConversionPurchases() {
        return conversionPurchases;
    }

    public void setConversionPurchases(TwitterAdStatsBreakdown conversionPurchases) {
        this.conversionPurchases = conversionPurchases;
    }

    public TwitterAdStatsBreakdown getConversionSignUps() {
        return conversionSignUps;
    }

    public void setConversionSignUps(TwitterAdStatsBreakdown conversionSignUps) {
        this.conversionSignUps = conversionSignUps;
    }

    public TwitterAdStatsBreakdown getConversionSiteVisits() {
        return conversionSiteVisits;
    }

    public void setConversionSiteVisits(TwitterAdStatsBreakdown conversionSiteVisits) {
        this.conversionSiteVisits = conversionSiteVisits;
    }

    public TwitterAdStatsBreakdown getConversionDownloads() {
        return conversionDownloads;
    }

    public void setConversionDownloads(TwitterAdStatsBreakdown conversionDownloads) {
        this.conversionDownloads = conversionDownloads;
    }

    public TwitterAdStatsBreakdown getConversionCustom() {
        return conversionCustom;
    }

    public void setConversionCustom(TwitterAdStatsBreakdown conversionCustom) {
        this.conversionCustom = conversionCustom;
    }

    public TwitterAdStatsBreakdown getMobileConversionSpentCredits() {
        return mobileConversionSpentCredits;
    }

    public void setMobileConversionSpentCredits(TwitterAdStatsBreakdown mobileConversionSpentCredits) {
        this.mobileConversionSpentCredits = mobileConversionSpentCredits;
    }

    public TwitterAdStatsBreakdown getMobileConversionInstalls() {
        return mobileConversionInstalls;
    }

    public void setMobileConversionInstalls(TwitterAdStatsBreakdown mobileConversionInstalls) {
        this.mobileConversionInstalls = mobileConversionInstalls;
    }

    public TwitterAdStatsBreakdown getMobileConversionContentViews() {
        return mobileConversionContentViews;
    }

    public void setMobileConversionContentViews(TwitterAdStatsBreakdown mobileConversionContentViews) {
        this.mobileConversionContentViews = mobileConversionContentViews;
    }

    public TwitterAdStatsBreakdown getMobileConversionAddToWishlist() {
        return mobileConversionAddToWishlist;
    }

    public void setMobileConversionAddToWishlist(TwitterAdStatsBreakdown mobileConversionAddToWishlist) {
        this.mobileConversionAddToWishlist = mobileConversionAddToWishlist;
    }

    public TwitterAdStatsBreakdown getMobileConversionCheckoutInitiated() {
        return mobileConversionCheckoutInitiated;
    }

    public void setMobileConversionCheckoutInitiated(TwitterAdStatsBreakdown mobileConversionCheckoutInitiated) {
        this.mobileConversionCheckoutInitiated = mobileConversionCheckoutInitiated;
    }

    public TwitterAdStatsBreakdown getMobileConversionReservations() {
        return mobileConversionReservations;
    }

    public void setMobileConversionReservations(TwitterAdStatsBreakdown mobileConversionReservations) {
        this.mobileConversionReservations = mobileConversionReservations;
    }

    public TwitterAdStatsBreakdown getMobileConversionTutorialsCompleted() {
        return mobileConversionTutorialsCompleted;
    }

    public void setMobileConversionTutorialsCompleted(TwitterAdStatsBreakdown mobileConversionTutorialsCompleted) {
        this.mobileConversionTutorialsCompleted = mobileConversionTutorialsCompleted;
    }

    public TwitterAdStatsBreakdown getMobileConversionAchievementsUnlocked() {
        return mobileConversionAchievementsUnlocked;
    }

    public void setMobileConversionAchievementsUnlocked(TwitterAdStatsBreakdown mobileConversionAchievementsUnlocked) {
        this.mobileConversionAchievementsUnlocked = mobileConversionAchievementsUnlocked;
    }

    public TwitterAdStatsBreakdown getMobileConversionSearches() {
        return mobileConversionSearches;
    }

    public void setMobileConversionSearches(TwitterAdStatsBreakdown mobileConversionSearches) {
        this.mobileConversionSearches = mobileConversionSearches;
    }

    public TwitterAdStatsBreakdown getMobileConversionAddToCarts() {
        return mobileConversionAddToCarts;
    }

    public void setMobileConversionAddToCarts(TwitterAdStatsBreakdown mobileConversionAddToCarts) {
        this.mobileConversionAddToCarts = mobileConversionAddToCarts;
    }

    public TwitterAdStatsBreakdown getMobileConversionPaymentInfoAdditions() {
        return mobileConversionPaymentInfoAdditions;
    }

    public void setMobileConversionPaymentInfoAdditions(TwitterAdStatsBreakdown mobileConversionPaymentInfoAdditions) {
        this.mobileConversionPaymentInfoAdditions = mobileConversionPaymentInfoAdditions;
    }

    public TwitterAdStatsBreakdown getMobileConversionReengages() {
        return mobileConversionReengages;
    }

    public void setMobileConversionReengages(TwitterAdStatsBreakdown mobileConversionReengages) {
        this.mobileConversionReengages = mobileConversionReengages;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }


    public String[] getCarouselSwipes() {
        return carouselSwipes;
    }

    public void setCarouselSwipes(String[] carouselSwipes) {
        this.carouselSwipes = carouselSwipes;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueLogins() {
        return mobileConversionLifetimeValueLogins;
    }

    public void setMobileConversionLifetimeValueLogins(TwitterAdStatsBreakdown mobileConversionLifetimeValueLogins) {
        this.mobileConversionLifetimeValueLogins = mobileConversionLifetimeValueLogins;
    }

    public TwitterAdStatsBreakdown getMobileConversionLifetimeValueContentViews() {
        return mobileConversionLifetimeValueContentViews;
    }

    public void setMobileConversionLifetimeValueContentViews(TwitterAdStatsBreakdown mobileConversionLifetimeValueContentViews) {
        this.mobileConversionLifetimeValueContentViews = mobileConversionLifetimeValueContentViews;
    }

    public String[] getMediaEngagements() {
        return mediaEngagements;
    }

    public void setMediaEngagements(String[] mediaEngagements) {
        this.mediaEngagements = mediaEngagements;
    }

    @Override
    public String toString() {
        return "NewTwitterAdStatistics{" +
                "appClicks=" + Arrays.toString(appClicks) +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", granularity=" + granularity +
                ", placement='" + placement + '\'' +
                ", engagements=" + Arrays.toString(engagements) +
                ", impressions=" + Arrays.toString(impressions) +
                ", retweets=" + Arrays.toString(retweets) +
                ", replies=" + Arrays.toString(replies) +
                ", likes=" + Arrays.toString(likes) +
                ", follows=" + Arrays.toString(follows) +
                ", cardEngagements=" + Arrays.toString(cardEngagements) +
                ", clicks=" + Arrays.toString(clicks) +
                ", urlClicks=" + Arrays.toString(urlClicks) +
                ", qualifiedImpressions=" + Arrays.toString(qualifiedImpressions) +
                ", carouselSwipes=" + Arrays.toString(carouselSwipes) +
                ", billedEngagements=" + Arrays.toString(billedEngagements) +
                ", billedChargeLocalMicro=" + Arrays.toString(billedChargeLocalMicro) +
                ", videoTotalViews=" + Arrays.toString(videoTotalViews) +
                ", videoViews25=" + Arrays.toString(videoViews25) +
                ", videoViews50=" + Arrays.toString(videoViews50) +
                ", videoViews75=" + Arrays.toString(videoViews75) +
                ", videoViews100=" + Arrays.toString(videoViews100) +
                ", videoCtaClicks=" + Arrays.toString(videoCtaClicks) +
                ", videoContentStarts=" + Arrays.toString(videoContentStarts) +
                ", videoMrcViews=" + Arrays.toString(videoMrcViews) +
                ", video3s100PercentViews=" + Arrays.toString(video3s100PercentViews) +
                ", mediaViews=" + Arrays.toString(mediaViews) +
                ", mediaEngagements=" + Arrays.toString(mediaEngagements) +
                ", segment=" + segment +
                ", conversionPurchases=" + conversionPurchases +
                ", conversionSignUps=" + conversionSignUps +
                ", conversionSiteVisits=" + conversionSiteVisits +
                ", conversionDownloads=" + conversionDownloads +
                ", conversionCustom=" + conversionCustom +
                ", mobileConversionSpentCredits=" + mobileConversionSpentCredits +
                ", mobileConversionInstalls=" + mobileConversionInstalls +
                ", mobileConversionContentViews=" + mobileConversionContentViews +
                ", mobileConversionAddToWishlist=" + mobileConversionAddToWishlist +
                ", mobileConversionCheckoutInitiated=" + mobileConversionCheckoutInitiated +
                ", mobileConversionReservations=" + mobileConversionReservations +
                ", mobileConversionTutorialsCompleted=" + mobileConversionTutorialsCompleted +
                ", mobileConversionAchievementsUnlocked=" + mobileConversionAchievementsUnlocked +
                ", mobileConversionSearches=" + mobileConversionSearches +
                ", mobileConversionAddToCarts=" + mobileConversionAddToCarts +
                ", mobileConversionPaymentInfoAdditions=" + mobileConversionPaymentInfoAdditions +
                ", mobileConversionReengages=" + mobileConversionReengages +
                ", mobileConversionShares=" + mobileConversionShares +
                ", mobileConversionRates=" + mobileConversionRates +
                ", mobileConversionLogins=" + mobileConversionLogins +
                ", mobileConversionUpdates=" + mobileConversionUpdates +
                ", mobileConversionLevelsAchieved=" + mobileConversionLevelsAchieved +
                ", mobileConversionInvites=" + mobileConversionInvites +
                ", mobileConversionKeyPageViews=" + mobileConversionKeyPageViews +
                ", mobileConversionSiteVisits=" + mobileConversionSiteVisits +
                ", mobileConversionPurchases=" + mobileConversionPurchases +
                ", mobileConversionDownloads=" + mobileConversionDownloads +
                ", mobileConversionSignUps=" + mobileConversionSignUps +
                ", mobileConversionLifetimeValuePurchases=" + mobileConversionLifetimeValuePurchases +
                ", mobileConversionLifetimeValueSignUps=" + mobileConversionLifetimeValueSignUps +
                ", mobileConversionLifetimeValueUpdates=" + mobileConversionLifetimeValueUpdates +
                ", mobileConversionLifetimeValueTutorialsCompleted=" + mobileConversionLifetimeValueTutorialsCompleted +
                ", mobileConversionLifetimeValueReservations=" + mobileConversionLifetimeValueReservations +
                ", mobileConversionLifetimeValueAddToCarts=" + mobileConversionLifetimeValueAddToCarts +
                ", mobileConversionLifetimeValueAddToWishlists=" + mobileConversionLifetimeValueAddToWishlists +
                ", mobileConversionLifetimeValueCheckoutsInitiated=" + mobileConversionLifetimeValueCheckoutsInitiated +
                ", mobileConversionLifetimeValueLevelsAchieved=" + mobileConversionLifetimeValueLevelsAchieved +
                ", mobileConversionLifetimeValueAchievementsUnlocked=" + mobileConversionLifetimeValueAchievementsUnlocked +
                ", mobileConversionLifetimeValueShares=" + mobileConversionLifetimeValueShares +
                ", mobileConversionLifetimeValueInvites=" + mobileConversionLifetimeValueInvites +
                ", mobileConversionLifetimeValuePaymentInfoAdditions=" + mobileConversionLifetimeValuePaymentInfoAdditions +
                ", mobileConversionLifetimeValueSpentCredits=" + mobileConversionLifetimeValueSpentCredits +
                ", mobileConversionLifetimeValueRates=" + mobileConversionLifetimeValueRates +
                ", mobileConversionLifetimeValueLogins=" + mobileConversionLifetimeValueLogins +
                ", mobileConversionLifetimeValueSearches=" + mobileConversionLifetimeValueSearches +
                ", mobileConversionLifetimeValueContentViews=" + mobileConversionLifetimeValueContentViews +
                '}';
    }
}
