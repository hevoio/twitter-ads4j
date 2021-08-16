package com.hevodata.twitterads4j.impl;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import com.hevodata.twitterads4j.BaseAdsListResponse;
import com.hevodata.twitterads4j.BaseAdsListResponseIterable;
import com.hevodata.twitterads4j.TwitterAdsClient;
import com.hevodata.twitterads4j.TwitterAdsConstants;
import com.hevodata.twitterads4j.api.TwitterAdsBiddingApi;
import com.hevodata.twitterads4j.internal.http.HttpParameter;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.ads.BiddingRules;
import com.hevodata.twitterads4j.models.ads.HttpVerb;
import com.hevodata.twitterads4j.models.ads.TwitterBidInfo;
import com.hevodata.twitterads4j.util.TwitterAdUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * Date: 22/04/16.
 * Time: 2:50 PM
 */
public class TwitterAdsBiddingApiImpl implements TwitterAdsBiddingApi {
    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsBiddingApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<BiddingRules> getBiddingRules(String currency) throws TwitterException {
        List<HttpParameter> param = new ArrayList<>();
        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PATH_BIDDING_RULES;
        if (TwitterAdUtil.isNotNullOrEmpty(currency)) {
            param.add(new HttpParameter(TwitterAdsConstants.PARAM_CURRENCY, currency));
        }
        Type type = new TypeToken<BaseAdsListResponse<BiddingRules>>() {}.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, param, type);
    }

    /*
    This call does not hit any version of twitter ads api, it hits the same end point as is hit on native
     */
    @Override
    public TwitterBidInfo getBidInfo(String accountId, String campaignType, Optional<String> currency, Optional<String> objectiveForBidding) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        TwitterAdUtil.ensureNotNull(campaignType, "campaignType");
        List<HttpParameter> params = Lists.newArrayList();
        String baseUrl = "https://ads.twitter.com/" + "accounts/" + accountId + "/campaigns/bid_guidance";
        //noinspection ConstantConditions
        params.add(new HttpParameter("account", accountId));
        params.add(new HttpParameter("campaign_type", campaignType));
        if (currency != null &&currency.isPresent()) {
            params.add(new HttpParameter("currency", currency.get()));
        }
        if (objectiveForBidding != null && objectiveForBidding.isPresent()) {
            params.add(new HttpParameter("objective", objectiveForBidding.get()));
        }
        Type type = new TypeToken<TwitterBidInfo>() {}.getType();

        return twitterAdsClient.executeRequest(baseUrl, params.toArray(new HttpParameter[params.size()]), type, HttpVerb.GET);
    }
}
