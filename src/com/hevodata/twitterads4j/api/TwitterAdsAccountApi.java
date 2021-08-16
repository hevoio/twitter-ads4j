package com.hevodata.twitterads4j.api;

import com.hevodata.twitterads4j.BaseAdsListResponseIterable;
import com.hevodata.twitterads4j.BaseAdsResponse;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.ads.AdAccount;
import com.hevodata.twitterads4j.models.ads.AdAccountNativePermissions;
import com.hevodata.twitterads4j.models.ads.PromotableUser;
import com.hevodata.twitterads4j.models.ads.sort.AccountsSortByField;

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

    /**
     * @param accountId The identifier for the leveraged account.
     * @return account features associated with the given account.
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/features">https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/features</a>
     */
    List<String> getAccountPermissions(String accountId) throws TwitterException;

    /**
     * @param accountId   The identifier for the leveraged account.
     * @param withDeleted Include deleted results in your request. Defaults to false.
     * @return the collection of promotable_users associated with an account.
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/promotable_users">https://dev.twitter.com/ads/reference/get/accounts/%3Aaccount_id/promotable_users</a>
     */
    BaseAdsListResponseIterable<PromotableUser> getPromotableUsers(String accountId, boolean withDeleted) throws TwitterException;

    /**
     * @param accountId The identifier for the leveraged account.
     * @return permissions of the currently authenticated user (access_token) as they relate to the specified ads account
     * @throws TwitterException
     */
    BaseAdsResponse<AdAccountNativePermissions> getAdAccountNativePermissions(String accountId) throws TwitterException;
}
