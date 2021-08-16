package com.hevodata.twitterads4j.impl;

import com.google.gson.reflect.TypeToken;
import com.hevodata.twitterads4j.*;
import com.hevodata.twitterads4j.api.TwitterScheduledTweetsApi;
import org.apache.commons.lang3.StringUtils;
import com.hevodata.twitterads4j.internal.http.HttpParameter;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.ads.HttpVerb;
import com.hevodata.twitterads4j.models.ads.ScheduledTweet;
import com.hevodata.twitterads4j.util.TwitterAdUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Date: 03/08/17 2:17 PM.
 */
public class TwitterScheduledTweetsApiImpl implements TwitterScheduledTweetsApi {

    private final TwitterAdsClient twitterAdsClient;

    public TwitterScheduledTweetsApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public BaseAdsListResponseIterable<ScheduledTweet> getScheduledTweets(String accountId, String userId, boolean withDeleted, Integer count, String cursor)
            throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        TwitterAdUtil.ensureNotNull(userId, "userId");

        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_USER_ID, userId));
        if (TwitterAdUtil.isNotNullOrEmpty(cursor)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CURSOR, cursor));
        }

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_TWEETS;
        final Type type = new TypeToken<BaseAdsListResponse<ScheduledTweet>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public BaseAdsResponse<ScheduledTweet> getScheduledTweetById(String accountId, String scheduledTweetId, boolean withDeleted)
            throws TwitterException {
        TwitterAdUtil.ensureNotNull(scheduledTweetId, "scheduledTweetId");
        TwitterAdUtil.ensureNotNull(accountId, "accountId");

        //noinspection MismatchedQueryAndUpdateOfCollection
        final List<HttpParameter> params = new ArrayList<>();

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_TWEETS + scheduledTweetId;
        final Type type = new TypeToken<BaseAdsResponse<ScheduledTweet>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, params.toArray(new HttpParameter[0]), type, HttpVerb.GET);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public BaseAdsResponse<ScheduledTweet> createScheduledTweet(String accountId, Date scheduledAt, String text, String userId, String cardId,
                                                                List<String> mediaKeys,
                                                                boolean nullCast) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        TwitterAdUtil.ensureNotNull(userId, "userId");
        TwitterAdUtil.ensureNotNull(scheduledAt, "scheduledAt");

        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_AS_USER_ID, userId));
        if (StringUtils.isNotBlank(cardId)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CARD_URI, "card://" + cardId));
        }
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_SCHEDULED_AT, TwitterAdUtil.convertTimeToZuluFormatAndToUTC(scheduledAt.getTime())));
        if (TwitterAdUtil.isNotEmpty(mediaKeys)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_KEYS, TwitterAdUtil.getCsv(mediaKeys)));
        }
        if (StringUtils.isNotBlank(text)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TEXT, text));
        }
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_NULLCAST, nullCast));

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_TWEETS;
        final Type type = new TypeToken<BaseAdsResponse<ScheduledTweet>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, params.toArray(new HttpParameter[0]), type, HttpVerb.POST);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public BaseAdsResponse<ScheduledTweet> updateScheduledTweet(String accountId, String scheduledTweetId, Date scheduledAt, String text, String cardId,
                                                                List<String> mediaKeys) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        TwitterAdUtil.ensureNotNull(scheduledTweetId, "scheduledTweetId");

        final List<HttpParameter> params = new ArrayList<>();
        if (StringUtils.isNotBlank(cardId)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CARD_URI, "card://" + cardId));
        }
        if (TwitterAdUtil.isNotEmpty(mediaKeys)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_KEYS, TwitterAdUtil.getCsv(mediaKeys)));
        }
        if (StringUtils.isNotBlank(text)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TEXT, text));
        }
        if (scheduledAt != null) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SCHEDULED_AT, TwitterAdUtil.convertTimeToZuluFormatAndToUTC(scheduledAt.getTime())));
        }

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_TWEETS + scheduledTweetId;
        final Type type = new TypeToken<BaseAdsResponse<ScheduledTweet>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, params.toArray(new HttpParameter[0]), type, HttpVerb.PUT);

    }

    @SuppressWarnings("Duplicates")
    @Override
    public BaseAdsResponse<ScheduledTweet> deleteScheduledTweet(String accountId, String scheduledTweetId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "Account Id");
        TwitterAdUtil.ensureNotNull(scheduledTweetId, "Tweet Id");

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_TWEETS + scheduledTweetId;
        final Type type = new TypeToken<BaseAdsResponse<ScheduledTweet>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, null, type, HttpVerb.DELETE);
    }
}
