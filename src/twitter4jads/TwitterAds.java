package twitter4jads;

import twitter4jads.api.*;

/**
 *
 * Date: 29/01/14.
 * Time: 8:08 PM
 */
public interface TwitterAds extends java.io.Serializable {

    TwitterAdsCampaignApi getCampaignApi();

    TwitterAdsCardsApi getCardsApi();

    TwitterAdsFundingInstrumentApi getFundingInstrumentApi();

    TwitterAdsLineItemApi getLineItemApi();

    TwitterAdsMediaApi getPromotedApi();

    TwitterAdsStatApi getStatApi();

    TwitterAdsAudienceApi getTailoredAudienceApi();

    TwitterAdsTargetingApi getTargetingApi();

    TwitterAdsWebEventApi getWebEventApi();

    TwitterAdsAccountApi getAccountApi();

    TwitterAdsPromotedTweetApi getPromotedTweetApi();

    TwitterAdsBiddingApi getBiddingApi();

    TwitterAdsClient getTwitterAdsClient();

    TwitterCallToActionApi getCallToActionApi();

    TwitterAdsPreviewApi getPreviewApi();

    TwitterScheduledTweetsApi getScheduledTweetApi();

    TwitterAdsMediaUploadApi getMediaUploadApi();

    TwitterAdsTweetsApi getTweetsApi();
}