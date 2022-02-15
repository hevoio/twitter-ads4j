package twitter4jads.impl;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import twitter4jads.*;
import twitter4jads.api.TwitterAdsMediaApi;
import twitter4jads.models.media.*;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.TwitterAdsClient;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.util.TwitterAdUtil;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * Date: 16/05/16 12:25 PM.
 */
public class TwitterAdsMediaApiImpl implements TwitterAdsMediaApi {


    public static final int TWITTER_MAX_LIBRARY_GET_COUNT = 50;
    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsMediaApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<TwitterAccountMediaCreative> getMediaCreativesForAccount(String accountId, Boolean fetchDeleted)
            throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, TwitterAdsConstants.ACCOUNT_ID);

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_MEDIA_CREATIVES;
        final List<HttpParameter> parameters = Lists.newArrayList();
        parameters.add(new HttpParameter(TwitterAdsConstants.PARAM_ACCOUNT_ID, accountId));
        if (fetchDeleted != null) {
            parameters.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, fetchDeleted));
        }

        final Type type = new TypeToken<BaseAdsListResponse<TwitterAccountMediaCreative>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, parameters, type);
    }

    //----------------------------------------------PRIVATE METHODS----------------------------------------------
}
