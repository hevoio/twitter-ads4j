package twitter4jads.impl;

import com.google.gson.reflect.TypeToken;
import twitter4jads.*;
import twitter4jads.api.TwitterAdsCampaignApi;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.TwitterAdsClient;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.Campaign;
import twitter4jads.models.ads.sort.CampaignSortByField;
import twitter4jads.util.TwitterAdUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * Date: 4/7/16
 * Time: 12:26 PM
 */
public class TwitterAdsCampaignApiImpl implements TwitterAdsCampaignApi {

    private static final Integer MAX_REQUEST_PARAMETER_SIZE = 200;
    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsCampaignApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<Campaign> getAllCampaigns(String accountId, Optional<Collection<String>> campaignIds,
                                                                 Optional<Collection<String>> fundingInstrumentIds, boolean withDeleted, Optional<Integer> count,
                                                                 Optional<String> cursor, Optional<CampaignSortByField> sortByField, Optional<String> q) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        String campaignIdsAsString = null;
        String fundingInstrumentIdsAsString = null;
        if (campaignIds != null && campaignIds.isPresent()) {
            TwitterAdUtil.ensureMaxSize(campaignIds.get(), MAX_REQUEST_PARAMETER_SIZE);
            campaignIdsAsString = TwitterAdUtil.getCsv(campaignIds.get());
        }
        if (fundingInstrumentIds != null && fundingInstrumentIds.isPresent()) {
            TwitterAdUtil.ensureMaxSize(fundingInstrumentIds.get(), MAX_REQUEST_PARAMETER_SIZE);
            fundingInstrumentIdsAsString = TwitterAdUtil.getCsv(fundingInstrumentIds.get());
        }

        List<HttpParameter> params =
                getCampaignParameters(accountId, Optional.ofNullable(campaignIdsAsString), Optional.ofNullable(fundingInstrumentIdsAsString), withDeleted, count, cursor, q);

        if (sortByField != null && sortByField.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SORT_BY, sortByField.get().getField()));
        }
        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_CAMPAIGN;

        final Type type = new TypeToken<BaseAdsListResponse<Campaign>>() {
        }.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }


    // ------------------------------------------------------------------------ PRIVATE METHODS -----------------------------------------------------

    private List<HttpParameter> getCampaignParameters(String accountId, Optional<String> campaignIds, Optional<String> fundingInstrumentIds, boolean withDeleted,
                                                      Optional<Integer> count, Optional<String> cursor, Optional<String> q) {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, withDeleted));
        if (campaignIds != null && campaignIds.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CAMPAIGN_IDS, campaignIds.get()));
        }
        if (fundingInstrumentIds != null && fundingInstrumentIds.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_FUNDING_INSTRUMENT_IDS, fundingInstrumentIds.get()));
        }
        if (count != null && count.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNT, count.get()));
        }
        if (cursor != null && cursor.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_CURSOR, cursor.get()));
        }

        if (q != null && q.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_Q, q.get()));
        }

        return params;
    }
}
