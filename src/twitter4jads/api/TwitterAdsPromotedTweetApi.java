package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.PromotedTweets;
import twitter4jads.models.ads.sort.PromotedTweetsSortByField;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * Date: 4/22/16
 * Time: 1:04 PM
 */
public interface TwitterAdsPromotedTweetApi {

    /**
     * @param accountId   The identifier for the leveraged account.
     * @param withDeleted Include deleted results in your request. Defaults to false.
     * @param sortByField (optional) Sorts by supported attribute in ascending or descending order.
     * @param count       (optional) Specifies the number of Promoted Tweets to try to retrieve, up to a maximum of 1000 per distinct request.
     * @param cursor      (optional) Specify a cursor to retrieve data from a specific page (function automatically handles paging upon iteration when you do not specify cursor value).
     * @return Retrieve references to the Promoted Tweets associated with one or more line items.
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/promoted_tweets">https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/promoted_tweets</a>
     */
    BaseAdsListResponseIterable<PromotedTweets> getAllPromotedTweets(String accountId, boolean withDeleted, Optional<Collection<String>> lineItemIds,
                                                                     Optional<Integer> count, String cursor, Optional<PromotedTweetsSortByField> sortByField) throws TwitterException;
}
