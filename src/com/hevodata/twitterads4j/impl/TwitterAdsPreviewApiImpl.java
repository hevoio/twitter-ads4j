package com.hevodata.twitterads4j.impl;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import com.hevodata.twitterads4j.BaseAdsListResponse;
import com.hevodata.twitterads4j.TwitterAdsClient;
import com.hevodata.twitterads4j.TwitterAdsConstants;
import com.hevodata.twitterads4j.api.TwitterAdsPreviewApi;
import com.hevodata.twitterads4j.internal.http.HttpParameter;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.ads.TwitterCreativePreview;
import com.hevodata.twitterads4j.models.ads.TwitterPreviewTarget;
import com.hevodata.twitterads4j.util.TwitterAdUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static com.hevodata.twitterads4j.models.ads.TwitterPreviewTarget.PUBLISHER_NETWORK;

/**
 *
 * Date: 13/06/16
 */
public class TwitterAdsPreviewApiImpl implements TwitterAdsPreviewApi {

    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsPreviewApiImpl(TwitterAdsClient twitterAdsClient) {
        TwitterAdUtil.ensureNotNull(twitterAdsClient, "Twitter_Ads_Client");
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponse<TwitterCreativePreview> getUnpublishedTweetPreview(String accountId, String text, String asUserId,
                                                                                  List<String> mediaIds, String cardId,
                                                                                  TwitterPreviewTarget twitterPreviewTarget, String videoKey)
            throws TwitterException {

        List<HttpParameter> parameterList =
                validateAndGetParametersForUnpublishedPostPreview(text, asUserId, mediaIds, twitterPreviewTarget, videoKey);

        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI +
                accountId + TwitterAdsConstants.TWEET_PATH_PREVIEW;

        Type type = new TypeToken<BaseAdsListResponse<TwitterCreativePreview>>() {
        }.getType();
        HttpResponse response = twitterAdsClient.getRequest(baseUrl, parameterList.toArray(new HttpParameter[parameterList.size()]));

        try {
            return TwitterAdUtil.constructBaseAdsListResponse(response, response.asString(), type);
        } catch (IOException io) {
            throw new TwitterException("Response for tweet preview failed from TwitterApi.");
        }
    }

    @Override
    public BaseAdsListResponse<TwitterCreativePreview> getPublishedTweetPreview(String accountId, String tweetId,
                                                                                TwitterPreviewTarget twitterPreviewTarget) throws TwitterException {
        final List<HttpParameter> parameterList = Lists.newArrayList();

        if (TwitterAdUtil.isNotNullOrEmpty(twitterPreviewTarget.name())) {
            parameterList.add(new HttpParameter(TwitterAdsConstants.PARAM_PREVIEW_TARGET, twitterPreviewTarget.name()));
        }

        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI +
                accountId + TwitterAdsConstants.TWEET_PATH_PREVIEW + tweetId;

        Type type = new TypeToken<BaseAdsListResponse<TwitterCreativePreview>>() {
        }.getType();
        HttpResponse response = twitterAdsClient.getRequest(baseUrl, parameterList.toArray(new HttpParameter[parameterList.size()]));

        try {
            return TwitterAdUtil.constructBaseAdsListResponse(response, response.asString(), type);
        } catch (IOException io) {
            throw new TwitterException("Response for tweet preview failed from TwitterApi");
        }
    }

    //-------------------------------------------------------------- PRIVATE METHODS-------------------------------------------------------------//

    private List<HttpParameter> validateAndGetParametersForUnpublishedPostPreview(String text, String asUserId, List<String> mediaKeys,
                                                                                  TwitterPreviewTarget twitterPreviewTarget, String videoId)
            throws TwitterException {
        final boolean isPreviewTargetPublisherNetwork = twitterPreviewTarget == PUBLISHER_NETWORK;
        final String mediaKeysCsv = TwitterAdUtil.getCsv(mediaKeys);
        if (isPreviewTargetPublisherNetwork && !TwitterAdUtil.isNotNullOrEmpty(mediaKeysCsv)) {
            throw new TwitterException("To preview an unpublished tweet, mediaIds is a required field when preview_target is " +
                    PUBLISHER_NETWORK.name());
        }

        final List<HttpParameter> parameterList = Lists.newArrayList();
        parameterList.add(new HttpParameter(TwitterAdsConstants.PARAM_TEXT, text));

        if (TwitterAdUtil.isNotNullOrEmpty(asUserId)) {
            parameterList.add(new HttpParameter(TwitterAdsConstants.PARAM_AS_USER_ID, asUserId));
        }
        if (TwitterAdUtil.isNotNull(twitterPreviewTarget)) {
            parameterList.add(new HttpParameter(TwitterAdsConstants.PARAM_PREVIEW_TARGET, twitterPreviewTarget.name()));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(mediaKeysCsv)) {
            parameterList.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_KEYS, mediaKeysCsv));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(videoId)) {
            parameterList.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_ID, videoId));
        }

        return parameterList;
    }
}