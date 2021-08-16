package com.hevodata.twitterads4j.api;

import com.hevodata.twitterads4j.BaseAdsListResponseIterable;
import com.hevodata.twitterads4j.BaseAdsResponse;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.media.TwitterMediaCallToAction;
import com.hevodata.twitterads4j.models.video.TwitterCallToActionType;

import java.util.Collection;

/**
 *
 * Date: 30/05/16 5:17 PM.
 */
public interface TwitterCallToActionApi {


    BaseAdsResponse<TwitterMediaCallToAction> create(String accountId, String lineItemId, TwitterCallToActionType twitterCallToActionType,
                                                     String callToActionUrl) throws TwitterException;


    BaseAdsResponse<TwitterMediaCallToAction> update(String accountId, String preRollCTAId, TwitterCallToActionType twitterCallToActionType,
                                                       String callToActionUrl) throws TwitterException;

    @Deprecated
    BaseAdsListResponseIterable<TwitterMediaCallToAction> getByLineItemId(String accountId, String lineItemId, Boolean withDeleted)
            throws TwitterException;

    BaseAdsListResponseIterable<TwitterMediaCallToAction> getByLineItemId(String accountId, Collection<String> lineItemIds, Boolean withDeleted)
            throws TwitterException;

    BaseAdsResponse<TwitterMediaCallToAction> getById(String accountId, String callToActionId, Boolean withDeleted) throws TwitterException;


    BaseAdsResponse<TwitterMediaCallToAction> delete(String accountId, String callToActionId) throws TwitterException;


}
