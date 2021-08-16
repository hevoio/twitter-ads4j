package com.hevodata.twitterads4j.impl;

import com.google.gson.reflect.TypeToken;
import com.hevodata.twitterads4j.api.TwitterAdsFundingInstrumentApi;
import com.hevodata.twitterads4j.BaseAdsListResponse;
import com.hevodata.twitterads4j.BaseAdsListResponseIterable;
import com.hevodata.twitterads4j.BaseAdsResponse;
import com.hevodata.twitterads4j.TwitterAdsClient;
import com.hevodata.twitterads4j.TwitterAdsConstants;
import com.hevodata.twitterads4j.internal.http.HttpParameter;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.ads.FundingInstrument;
import com.hevodata.twitterads4j.models.ads.HttpVerb;
import com.hevodata.twitterads4j.models.ads.sort.FundingInstrumentSortByField;
import com.hevodata.twitterads4j.util.TwitterAdUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * Date: 4/5/16
 * Time: 10:37 AM
 */
public class TwitterAdsFundingInstrumentApiImpl implements TwitterAdsFundingInstrumentApi {

    private static final Integer MAX_REQUEST_PARAMETER_SIZE = 50;

    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsFundingInstrumentApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<FundingInstrument> getAllFundingInstruments(String accountId, boolean withDeleted, Optional<Collection<String>> fundingInstrumentIds,
                                                                                   Optional<FundingInstrumentSortByField> sortByField) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        String fundingInstrumentIdsAsString = null;
        if (fundingInstrumentIds != null && fundingInstrumentIds.isPresent()) {
            TwitterAdUtil.ensureMaxSize(fundingInstrumentIds.get(), MAX_REQUEST_PARAMETER_SIZE);
            fundingInstrumentIdsAsString = TwitterAdUtil.getCsv(fundingInstrumentIds.get());
        }

        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, withDeleted));
        if (TwitterAdUtil.isNotNullOrEmpty(fundingInstrumentIdsAsString)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_FUNDING_INSTRUMENT_IDS, fundingInstrumentIdsAsString));
        }
        if(sortByField != null && sortByField.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_SORT_BY, sortByField.get().getField()));
        }
        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_FUNDING_INSTRUMENTS;
        Type type = new TypeToken<BaseAdsListResponse<FundingInstrument>>() {}.getType();
        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @Override
    public BaseAdsResponse<FundingInstrument> getFundingInstrumentById(String accountId, String fundingInstrumentId, boolean withDeleted)
        throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        TwitterAdUtil.ensureNotNull(fundingInstrumentId, "fundingInstrumentId");
        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PREFIX_ACCOUNTS_URI + accountId + TwitterAdsConstants.PATH_FUNDING_INSTRUMENTS + fundingInstrumentId;
        HttpParameter[] param  = new HttpParameter[]{new HttpParameter(TwitterAdsConstants.PARAM_WITH_DELETED, withDeleted)};

        final Type type = new TypeToken<BaseAdsResponse<FundingInstrument>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, param, type, HttpVerb.GET);
    }
}
