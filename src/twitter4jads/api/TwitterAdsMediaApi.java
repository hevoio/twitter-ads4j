package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.media.*;

/**
 *
 * Date: 16/05/16 12:25 PM.
 */
public interface TwitterAdsMediaApi {

    /**
     * @param accountId    The identifier for the leveraged account.
     * @param fetchDeleted Include deleted results in your request.
     * @return
     * @throws TwitterException
     */
    BaseAdsListResponseIterable<TwitterAccountMediaCreative> getMediaCreativesForAccount(String accountId, Boolean fetchDeleted)
            throws TwitterException;
}
