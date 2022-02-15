package twitter4jads;

import twitter4jads.api.*;

/**
 *
 * Date: 29/01/14.
 * Time: 8:08 PM
 */
public interface TwitterAds extends java.io.Serializable {

    TwitterAdsCampaignApi getCampaignApi();

    TwitterAdsFundingInstrumentApi getFundingInstrumentApi();

    TwitterAdsLineItemApi getLineItemApi();

    TwitterAdsMediaApi getPromotedApi();

    TwitterAdsStatApi getStatApi();

    TwitterAdsTargetingApi getTargetingApi();

    TwitterAdsAccountApi getAccountApi();

    TwitterAdsPromotedTweetApi getPromotedTweetApi();

    TwitterAdsClient getTwitterAdsClient();

    TwitterAdsTweetsApi getTweetsApi();
}