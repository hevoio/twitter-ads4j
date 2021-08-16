package com.hevodata.twitterads4j.models.ads.cards;

import com.hevodata.twitterads4j.BaseAdsResponse;

/**
 *
 * Date: 26/11/15
 */
public class PromotedVideoTweetResponse extends BaseAdsResponse<Object> {
    public Object getStatusJSON() {
        return getData();
    }
}
