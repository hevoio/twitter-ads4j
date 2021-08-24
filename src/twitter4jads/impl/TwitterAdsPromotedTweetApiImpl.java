package twitter4jads.impl;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import twitter4jads.*;
import twitter4jads.api.TwitterAdsPromotedTweetApi;
import twitter4jads.internal.models4j.Status;
import org.apache.commons.collections.CollectionUtils;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.HttpVerb;
import twitter4jads.models.ads.PromotedTweet;
import twitter4jads.models.ads.PromotedTweets;
import twitter4jads.models.ads.sort.PromotedTweetsSortByField;
import twitter4jads.util.TwitterAdUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * Date: 4/22/16
 * Time: 1:06 PM
 */
public class TwitterAdsPromotedTweetApiImpl implements TwitterAdsPromotedTweetApi {

    private static final Integer MAX_REQUEST_PARAMETER_SIZE = 200;

    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsPromotedTweetApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<PromotedTweets> getAllPromotedTweets(String accountId, boolean withDeleted, Optional<Collection<String>> lineItemIds,
                                                                            Optional<Integer> count, String cursor, Optional<PromotedTweetsSortByField> sortByField) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, withDeleted));
        if (count != null && count.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNT, count.get()));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(cursor)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CURSOR, cursor));
        }
        if (lineItemIds != null && lineItemIds.isPresent()) {
            TwitterAdUtil.ensureMaxSize(lineItemIds.get(), MAX_REQUEST_PARAMETER_SIZE);
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_LINE_ITEM_IDS, TwitterAdUtil.getCsv(lineItemIds.get())));
        }
        if (sortByField != null && sortByField.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SORT_BY, sortByField.get().getField()));
        }

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_PROMOTED_TWEETS;
        final Type type = new TypeToken<BaseAdsListResponse<PromotedTweets>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @Override
    public BaseAdsResponse<PromotedTweets> getPromotedTweetsById(String accountId, String promotedTweetsId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(promotedTweetsId, "promotedTweetsId");

        final Type type = new TypeToken<BaseAdsResponse<PromotedTweets>>() {
        }.getType();
        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_PROMOTED_TWEETS + promotedTweetsId;
        return twitterAdsClient.executeHttpRequest(baseUrl, null, type, HttpVerb.GET);
    }

    @Override
    public BaseAdsListResponse<PromotedTweets> createPromotedTweets(String accountId, String lineItemId, Collection<String> tweetIds)
        throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(lineItemId, "Line Item Id");

        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_LINE_ITEM_ID, lineItemId));

        if (TwitterAdUtil.isNotEmpty(tweetIds)) {
            TwitterAdUtil.ensureMaxSize(tweetIds, MAX_REQUEST_PARAMETER_SIZE);
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TWEET_IDS, TwitterAdUtil.getCsv(tweetIds)));
        }
        HttpResponse httpResponse = twitterAdsClient.postRequest(twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId +
                TwitterAdsConstants.PATH_PROMOTED_TWEETS, params.toArray(new HttpParameter[params.size()]));
        try {
            final Type type = new TypeToken<BaseAdsListResponse<PromotedTweets>>() {
            }.getType();
            return TwitterAdUtil.constructBaseAdsListResponse(httpResponse, httpResponse.asString(), type);
        } catch (IOException e) {
            throw new TwitterException("Failed to parse promoted tweets.");
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public BaseAdsResponse<PromotedTweets> deletePromotedTweets(String accountId, String tweetId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(tweetId, "Tweet Id");
        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_PROMOTED_TWEETS +
                tweetId;
        Type type = new TypeToken<BaseAdsResponse<PromotedTweets>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, null, type, HttpVerb.DELETE);
    }

    @Override
    public Status createPromotedVideoTweet(String accountId, String targetUserId, String tweetText, String videoId, String videoTitle, String videoDescription, String callToAction, String ctaValue) throws TwitterException, IOException, InterruptedException {
        try {
            TwitterAdUtil.ensureNotNull(accountId, "AccountId");
            TwitterAdUtil.ensureNotNull(tweetText, "Tweet text");
            TwitterAdUtil.ensureNotNull(videoId, "Video");

            List<HttpParameter> params = Lists.newArrayList();
            if (TwitterAdUtil.isNotNullOrEmpty(targetUserId)) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_AS_USER_ID, targetUserId));
            }

            if (TwitterAdUtil.isNotNullOrEmpty(tweetText)) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_TEXT, tweetText));
            }

            if (TwitterAdUtil.isNotNullOrEmpty(videoId)) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_ID, videoId));
            }

            if (TwitterAdUtil.isNotNullOrEmpty(videoTitle)) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_TITLE, videoTitle));
            }

            if (TwitterAdUtil.isNotNullOrEmpty(videoDescription)) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_DESCRIPTION, videoDescription));
            }

            if (TwitterAdUtil.isNotNullOrEmpty(callToAction) && TwitterAdUtil.isNotNullOrEmpty(ctaValue)) {
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_CTA, callToAction));
                params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_CTA_VALUE, ctaValue));
            }
            String url = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_PROMOTED_VIDEO_TWEET;
            HttpParameter[] parameters = params.toArray(new HttpParameter[0]);
            Type type = new TypeToken<BaseAdsResponse<PromotedTweet>>() {
            }.getType();

            final BaseAdsResponse<PromotedTweet> response = twitterAdsClient.executeHttpRequest(url, parameters, type, HttpVerb.POST);
            final PromotedTweet tweet = response.getData();
            if (tweet == null) {
                throw new TwitterException("Unable to create Video promoted Tweet. Definitely something is wrong here.");
            }
            return tweet;
        } catch (Exception eX) {
            throw new TwitterException("Unable to Create Promoted Video Tweet. " + eX.getMessage());
        }
    }

    @Override
    public BaseAdsResponse<PromotedTweets> createScheduledPromotedTweets(String accountId, String lineItemId, String scheduledTweetId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(lineItemId, "Line Item Id");
        TwitterAdUtil.ensureNotNull(scheduledTweetId, "Scheduled Tweet Id");

        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_LINE_ITEM_ID, lineItemId));
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_SCHEDULED_TWEET_ID, scheduledTweetId));

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_PROMOTED_TWEETS;
        final Type type = new TypeToken<BaseAdsResponse<PromotedTweets>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, params.toArray(new HttpParameter[params.size()]), type, HttpVerb.POST);
    }

    @Override
    public BaseAdsResponse<PromotedTweets> deleteScheduledPromotedTweet(String accountId, String scheduledPromotedTweet) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(scheduledPromotedTweet, "scheduledPromotedTweet");
        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_PROMOTED_TWEETS +
                scheduledPromotedTweet;
        Type type = new TypeToken<BaseAdsResponse<PromotedTweets>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, null, type, HttpVerb.DELETE);
    }

    @Override
    public BaseAdsListResponseIterable<PromotedTweets> getScheduledPromotedTweets(String accountId, List<String> lineItemIds, List<String> scheduledPromotedTweetIds, Integer count, String cursor, String sortBy, boolean withDeleted, boolean includeTotalCount) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, withDeleted));
        if (includeTotalCount && !TwitterAdUtil.isNotNullOrEmpty(cursor)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_TOTAL_COUNT, includeTotalCount));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(sortBy)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SORT_BY, sortBy));
        }
        if (TwitterAdUtil.isNotNull(count)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNT, count));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(cursor)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CURSOR, cursor));
        }

        if (CollectionUtils.isNotEmpty(lineItemIds)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_LINE_ITEM_IDS, TwitterAdUtil.getCsv(lineItemIds)));
        }
        if (CollectionUtils.isNotEmpty(scheduledPromotedTweetIds)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SCHEDULED_PROMOTED_TWEET_IDS, TwitterAdUtil.getCsv(scheduledPromotedTweetIds)));
        }

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_SCHEDULED_PROMOTED_TWEETS;
        final Type type = new TypeToken<BaseAdsListResponse<PromotedTweets>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }


    @Deprecated
    @Override
    public Status createPromotedTweetV2(String accountId, String targetUserId, String tweetText, List<String> mediaIds, String videoId,
                                        String videoTitle, String videoDescription, String videoCallToAction, String videoCtaValue, String cardUri,
                                        boolean promotedOnly) throws TwitterException, IOException {
        try {
            final List<HttpParameter> params =
                    validateAndCreateParamsForPromotedTweet(accountId, targetUserId, tweetText, mediaIds, videoId, videoTitle, videoDescription,
                            videoCallToAction, videoCtaValue, cardUri, promotedOnly);

            final String url = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_PROMOTED_TWEET_V2;
            final HttpParameter[] parameters = params.toArray(new HttpParameter[params.size()]);
            final Type type = new TypeToken<BaseAdsResponse<PromotedTweet>>() {
            }.getType();

            final BaseAdsResponse<PromotedTweet> response = twitterAdsClient.executeHttpRequest(url, parameters, type, HttpVerb.POST);
            final PromotedTweet tweet = response.getData();
            if (tweet == null) {
                throw new TwitterException("Unable to create the tweet. Please contact support.");
            }

            return tweet;
        } catch (Exception eX) {
            throw new TwitterException("Unable to create the tweet. " + eX.getMessage());
        }
    }

    private List<HttpParameter> validateAndCreateParamsForPromotedTweet(String accountId, String targetUserId, String tweetText,
                                                                        List<String> mediaIds, String videoId, String videoTitle,
                                                                        String videoDescription, String videoCallToAction, String videoCtaValue,
                                                                        String cardUri, boolean promotedOnly) throws TwitterException {
        List<HttpParameter> params = Lists.newArrayList();
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(tweetText, "Tweet text");

        params.add(new HttpParameter(TwitterAdsConstants.PARAM_NULLCAST, promotedOnly));

        if (TwitterAdUtil.isNotNullOrEmpty(targetUserId)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_AS_USER_ID, targetUserId));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(tweetText)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TEXT, tweetText));
        }

        if (TwitterAdUtil.isNotNullOrEmpty(cardUri)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CARD_URI, cardUri));
        }

        if (TwitterAdUtil.isNotEmpty(mediaIds)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_MEDIA_IDS, TwitterAdUtil.getCsv(mediaIds)));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(videoId)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_ID, videoId));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(videoTitle)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_TITLE, videoTitle));
        }

        if (TwitterAdUtil.isNotNullOrEmpty(videoDescription)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_DESCRIPTION, videoDescription));
        }

        if (TwitterAdUtil.isNotNullOrEmpty(videoCallToAction) && TwitterAdUtil.isNotNullOrEmpty(videoCtaValue)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_CTA, videoCallToAction));
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_VIDEO_CTA_VALUE, videoCtaValue));
        }

        return params;
    }
}
