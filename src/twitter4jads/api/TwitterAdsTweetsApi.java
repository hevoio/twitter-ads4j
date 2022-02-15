package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.Tweet;
import twitter4jads.models.ads.TwitterTimelineType;
import twitter4jads.models.ads.TwitterTweetType;

import java.util.List;

public interface TwitterAdsTweetsApi {

    /**
     * @param accountId                 The identifier for the leveraged account. The specified account must be associated with the authenticated user.
     * @param tweetType                 The Tweet type for the specified tweet_ids. Possible values: DRAFT, PUBLISHED, SCHEDULED.
     * @param count                     Specifies the number of records to try and retrieve per distinct request. Values: [1, 1000], default = 200.
     * @param cursor                    Specifies a cursor to get the next page of results. See https://developer.twitter.com/en/docs/ads/general/guides/pagination.
     * @param includeMentionsAndReplies Whether to filter out mentions and replies from the list of available Tweets.
     * @param timelineType              Whether to return nullcasted (a.k.a. "Promoted-only") Tweets, organic Tweets, or both. Possible values: ALL, NULLCAST, ORGANIC.
     * @param trimUser                  Whether to exclude the user object in the Tweet response. When enabled, the only part of the user object that will be returned is the Tweet's author's user ID.
     * @param tweetIds                  A comma-separated list of identifiers. Up to 200 IDs may be provided.
     * @param userId                    Specifies the user to scope Tweets to. Defaults to the FULL promotable user on the account when not set.
     * @return Tweet details for the account's full promotable user (default) or the user specified in the user_id parameter.
     * @throws TwitterException
     */
    BaseAdsListResponseIterable<Tweet> getTweets(String accountId, TwitterTweetType tweetType, Integer count, String cursor,
                                                 Boolean includeMentionsAndReplies, TwitterTimelineType timelineType, Boolean trimUser,
                                                 List<Long> tweetIds, Long userId)
            throws TwitterException;


}