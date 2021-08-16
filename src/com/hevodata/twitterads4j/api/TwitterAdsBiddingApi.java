package com.hevodata.twitterads4j.api;

import com.hevodata.twitterads4j.BaseAdsListResponseIterable;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.ads.BiddingRules;
import com.hevodata.twitterads4j.models.ads.TwitterBidInfo;

import java.util.Optional;

/**
 *
 * Date: 22/04/16.
 * Time: 2:49 PM
 */
public interface TwitterAdsBiddingApi {
    /**
     * @param currency The type of a currency to filter results by, identified using ISO-4217
     * @return Retrieve the bidding rules for a specific or all currencies.
     * @throws TwitterException
     */
    BaseAdsListResponseIterable<BiddingRules> getBiddingRules(String currency) throws TwitterException;

    TwitterBidInfo getBidInfo(String accountId, String campaignType, Optional<String> currency, Optional<String> objectiveForBidding) throws TwitterException;

}
