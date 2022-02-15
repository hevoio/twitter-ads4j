package twitter4jads;

import twitter4jads.api.*;
import twitter4jads.impl.*;
import twitter4jads.auth.Authorization;
import twitter4jads.conf.Configuration;

/**
 *
 * Date: 29/01/14
 * Time: 11:55 AM
 */
public class TwitterAdsImpl implements TwitterAds {

    private final TwitterAdsClient twitterAdsClient;
    private final TwitterAdsTargetingApi targetingApi;
    private final TwitterAdsAccountApi accountApi;
    private final TwitterAdsLineItemApi lineItemApi;
    private final TwitterAdsFundingInstrumentApi fundingInstrumentApi;
    private final TwitterAdsMediaApi promotedApi;
    private final TwitterAdsPromotedTweetApi promotedTweetApi;
    private final TwitterAdsStatApi statApi;
    private final TwitterAdsCampaignApi campaignApi;
    private final TwitterAdsTweetsApi tweetsApi;

    TwitterAdsImpl(Configuration conf, Authorization auth) {
        this.twitterAdsClient = new TwitterAdsClient(conf, auth);
        this.targetingApi = new TwitterAdsTargetingApiImpl(twitterAdsClient);
        this.accountApi = new TwitterAdsAccountApiImpl(twitterAdsClient);
        this.lineItemApi = new TwitterAdsLineItemApiImpl(twitterAdsClient);
        this.fundingInstrumentApi = new TwitterAdsFundingInstrumentApiImpl(twitterAdsClient);
        this.promotedApi = new TwitterAdsMediaApiImpl(twitterAdsClient);
        this.statApi = new TwitterAdsStatApiImpl(twitterAdsClient);
        this.campaignApi = new TwitterAdsCampaignApiImpl(twitterAdsClient);
        this.promotedTweetApi = new TwitterAdsPromotedTweetApiImpl(twitterAdsClient);
        this.tweetsApi = new TwitterAdsTweetsApiImpl(twitterAdsClient);
    }
    
    public TwitterAdsClient getTwitterAdsClient() {
        return twitterAdsClient;
    }

    @Override
    public TwitterAdsCampaignApi getCampaignApi() {
        return campaignApi;
    }

    @Override
    public TwitterAdsFundingInstrumentApi getFundingInstrumentApi() {
        return fundingInstrumentApi;
    }

    @Override
    public TwitterAdsLineItemApi getLineItemApi() {
        return lineItemApi;
    }

    @Override
    public TwitterAdsMediaApi getPromotedApi() {
        return promotedApi;
    }

    @Override
    public TwitterAdsStatApi getStatApi() {
        return statApi;
    }

    @Override
    public TwitterAdsTargetingApi getTargetingApi() {
        return targetingApi;
    }

    @Override
    public TwitterAdsAccountApi getAccountApi() {
        return accountApi;
    }

    @Override
    public TwitterAdsPromotedTweetApi getPromotedTweetApi() {
        return promotedTweetApi;
    }

    @Override
    public TwitterAdsTweetsApi getTweetsApi() {
        return tweetsApi;
    }
}
