package twitter4jads.impl;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import twitter4jads.*;
import twitter4jads.api.TwitterAdsTargetingApi;
import twitter4jads.internal.models4j.LocationType;
import twitter4jads.models.ads.*;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.util.TwitterAdUtil;
import java.lang.reflect.Type;
import java.util.*;
import static twitter4jads.models.ads.TargetingType.*;

/**
 *
 * Date: 4/4/16
 * Time: 7:16 PM
 */
public class TwitterAdsTargetingApiImpl implements TwitterAdsTargetingApi {

    private static final Integer MAX_REQUEST_PARAMETER_SIZE = 50;
    private static final Set<TargetingType> TARGETING_TYPES_WITH_MODIFIER_PREFIX_OR_SUFFIX =
            Sets.newHashSet(EXCLUDE_APP_LIST, NEGATIVE_BEHAVIOR, NEGATIVE_EXACT_KEYWORD, NEGATIVE_PHRASE_KEYWORD, NEGATIVE_UNORDERED_KEYWORD,
                    NETWORK_ACTIVATION_DURATION_GTE, NETWORK_ACTIVATION_DURATION_LT);

    private static final Gson GSON = new Gson();
    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsTargetingApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<TargetingCriteria> getAllTargetingPlatforms(String q) throws TwitterException {
        return hitQueryForPath(q, TwitterAdsConstants.PATH_TARGETING_CRITERIA_PLATFORMS);
    }

    @Override
    public BaseAdsListResponseIterable<TargetingLocation> getAllTargetingLocations(Optional<LocationType> locationType, String q,
                                                                                   String countryCode, Optional<Integer> count)
            throws TwitterException {
        List<HttpParameter> params = validateTargetingLocationParameters(locationType, q, countryCode, count);
        String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + TwitterAdsConstants.PATH_TARGETING_CRITERIA_LOCATION;

        Type type = new TypeToken<BaseAdsListResponse<TargetingLocation>>() {
        }.getType();

        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    // ------------------------------------------------------------------- PRIVATE METHODS ---------------------------------------------------------

    @SuppressWarnings("Duplicates")
    private <T> BaseAdsListResponseIterable<T> hitQueryForPath(String q, String queryPath) throws
            TwitterException {
        return hitQueryForPath(q, queryPath, null);
    }

    private <T> BaseAdsListResponseIterable<T> hitQueryForPath(String q, String queryPath, TwitterOSType osType)
            throws TwitterException {
        final List<HttpParameter> params = new ArrayList<>();
        if (TwitterAdUtil.isNotNullOrEmpty(q)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_Q, q));
        }
        if (TwitterAdUtil.isNotNull(osType)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_OS_TYPE, osType.name()));
        }

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + queryPath;
        final Type type = new TypeToken<BaseAdsListResponse<TargetingCriteria>>() {
        }.getType();

        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }
    private List<HttpParameter> validateTargetingLocationParameters(final Optional<LocationType> locationType,
                                                                    final String q, final String countryCode,
                                                                    final Optional<Integer> count) {

        List<HttpParameter> params = new ArrayList<>();
        if (locationType != null && locationType.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_LOCATION_TYPE, locationType.get().name()));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(q)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_Q, q));
        }
        if (TwitterAdUtil.isNotNullOrEmpty(countryCode)) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNTRY_CODE, countryCode));
        }
        if (count != null && count.isPresent()) {
            params.add(new HttpParameter(TwitterAdsConstants.PARAM_COUNT, count.get()));
        }
        return params;
    }
}
