package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsResponse;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.AdAccount;
import twitter4jads.models.ads.sort.AccountsSortByField;

import java.util.List;
import java.util.Optional;

public interface TwitterAdsAccountApi {
    /**
     * @param withDeleted Include deleted results in your request. Defaults to false.
     * @param q           An optional query to scope resource by name
     * @return all the advertising-enabled accounts the authenticating user has access to.
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts">https://dev.twitter.com/ads/reference/get/accounts</a>
     */
    BaseAdsListResponseIterable<AdAccount> getAllAccounts(Boolean withDeleted, Optional<AccountsSortByField> accountsSortByField, Optional<List<String>> accountIds, Optional<String> q) throws TwitterException;

    /**
     * @param accountId   The identifier for the leveraged account.
     * @param withDeleted Include deleted results in your request. Defaults to false.
     * @return detailed information on the specified account that the authenticating user has access to.
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id">https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id</a>
     */
    BaseAdsResponse<AdAccount> getAdAccountById(String accountId, Boolean withDeleted) throws TwitterException;
}
