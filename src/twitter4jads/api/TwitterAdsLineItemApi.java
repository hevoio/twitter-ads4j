package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.*;
import twitter4jads.models.ads.sort.LineItemsSortByField;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * Date: 4/4/16
 * Time: 8:00 PM
 */
public interface TwitterAdsLineItemApi {

    /**
     * @param accountId            The identifier for the leveraged account.
     * @param campaignIds          (optional) Scope the response to just the desired campaigns by specifying a Collection of identifiers. Up to 50 ids may be provided.
     * @param lineItemIds          (optional) Scope the response to just the desired line items by specifying a Collection of identifiers. Up to 50 ids may be provided.
     * @param fundingInstrumentIds (optional) Scope the response to just the desired funding instruments by specifying a Collection of identifiers. Up to 50 ids may be provided.
     * @param count                (optional) Specifies the number of campaigns to try and retrieve, up to a maximum of 1000 per distinct request.
     * @param cursor               (optional) Specify a cursor to retrieve data from a specific page (function automatically handles paging upon iteration when you do not specify cursor value).
     * @param sortByField          (optional) Specify to return the line items according to the sorted parameter given.
     * @param withDeleted          Include deleted results in your request. Defaults to false.
     * @param q                    (optional) A query to scope resource by name.
     * @return Retrieve the line items associated with a specific campaign belonging to the current account.
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/line_items">https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/line_items</a>
     */
    BaseAdsListResponseIterable<LineItem> getAllLineItems(String accountId, Optional<Collection<String>> campaignIds, Optional<Collection<String>> lineItemIds,
                                                          Optional<Collection<String>> fundingInstrumentIds, Optional<Integer> count, boolean withDeleted,
                                                          String cursor, Optional<LineItemsSortByField> sortByField, Optional<String> q) throws TwitterException;
}
