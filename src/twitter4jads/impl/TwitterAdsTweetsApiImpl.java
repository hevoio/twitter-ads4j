package twitter4jads.impl;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import twitter4jads.*;
import twitter4jads.api.TwitterAdsTweetsApi;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.Tweet;
import twitter4jads.models.ads.TwitterTimelineType;
import twitter4jads.models.ads.TwitterTweetType;
import twitter4jads.util.TwitterAdUtil;

import java.lang.reflect.Type;
import java.util.List;


/**
 *  niketkhandelwal
 * @Date Dec 20, 2019
 */
public class TwitterAdsTweetsApiImpl implements TwitterAdsTweetsApi {

    private static final Integer MAX_TWEET_IDS_REQUEST_SIZE = 200;
    private static final Integer NUMBER_OF_RECORDS_PER_REQUEST = 1000;
    private TwitterAdsClient twitterAdsClient;

    public TwitterAdsTweetsApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<Tweet> getTweets(String accountId, TwitterTweetType tweetType, Integer count, String cursor,
                                                        Boolean includeMentionsAndReplies, TwitterTimelineType timelineType, Boolean trimUser,
                                                        List<Long> tweetIds, Long userId) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.PARAM_ACCOUNT_ID);
        TwitterAdUtil.ensureNotNull(tweetType, TwitterAdsConstants.PARAM_TWEET_TYPE);
        final List<HttpParameter> params = Lists.newArrayList();

        params.add(new HttpParameter(TwitterAdsConstants.PARAM_TWEET_TYPE, tweetType.name()));
        validateGetTweetParametersAndAddToParams(params, count, cursor, includeMentionsAndReplies, timelineType, trimUser, tweetIds, userId);

        final String baseUrl =
                twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_TWEETS;

        final Type type = new TypeToken<BaseAdsListResponse<Tweet>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }



    //--------------------------------------------------------------- PRIVATE METHODS ----------------------------------------------------------------

    private void validateGetTweetParametersAndAddToParams(List<HttpParameter> params, Integer count, String cursor, Boolean includeMentionsAndReplies,
                                                          TwitterTimelineType timelineType, Boolean trimUser, List<Long> tweetIds, Long userId) {
        if (count != null && count < NUMBER_OF_RECORDS_PER_REQUEST) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNT, count));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(cursor)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CURSOR, cursor));
        }
        if (TwitterAdUtil.isNotNull(includeMentionsAndReplies)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_INCLUDE_MENTIONS_AND_REPLIES, includeMentionsAndReplies));
        }
        if (TwitterAdUtil.isNotNull(timelineType)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TIMELINE_TYPE, timelineType.name()));
        }
        if (TwitterAdUtil.isNotNull(trimUser)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TRIM_USER, trimUser));
        }
        if (TwitterAdUtil.isNotNull(tweetIds) && TwitterAdUtil.isNotEmpty(tweetIds)) {
            TwitterAdUtil.ensureMaxSize(tweetIds, MAX_TWEET_IDS_REQUEST_SIZE);
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_TWEET_IDS, TwitterAdUtil.getCsv(tweetIds)));
        }
        if (TwitterAdUtil.isNotNull(userId)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_USER_ID, userId));
        }
    }
}
