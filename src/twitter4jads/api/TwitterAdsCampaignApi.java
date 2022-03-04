package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.Campaign;
import twitter4jads.models.ads.sort.CampaignSortByField;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * Date: 4/7/16
 * Time: 12:25 PM
 */
public interface TwitterAdsCampaignApi {

    /**
     * @param accountId            The identifier for the leveraged account.
     * @param campaignIds          (optional) Scope the response to just the desired campaigns by specifying a comma-separated list of identifiers. Up to 50 ids may be provided.
     * @param fundingInstrumentIds (optional) Scope the response to just the desired funding instruments by specifying a comma-separated list of identifiers.
     *                             Up to 50 ids may be provided.
     * @param withDeleted          Include deleted results in your request. Defaults to false.
     * @param count                (optional) Specifies the number of campaigns to try and retrieve, up to a maximum of 1000 per distinct request.
     * @param cursor               (optional) Specify a cursor to retrieve data from a specific page (function automatically handles paging upon iteration when you do not specify cursor value).
     * @param q                    (optional) An query to scope resource by name.
     * @return Retrieve details for some or all campaigns associated with the current account.
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/campaigns">https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/campaigns</a>
     */
    BaseAdsListResponseIterable<Campaign> getAllCampaigns(String accountId, Optional<Collection<String>> campaignIds,
                                                          Optional<Collection<String>> fundingInstrumentIds, boolean withDeleted, Optional<Integer> count,
                                                          Optional<String> cursor, Optional<CampaignSortByField> sortByField, Optional<String> q) throws TwitterException;
}
