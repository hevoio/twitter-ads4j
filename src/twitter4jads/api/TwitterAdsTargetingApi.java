package twitter4jads.api;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.internal.models4j.LocationType;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.*;
import java.util.Optional;

/**
 *
 * Date: 4/4/16
 * Time: 7:15 PM
 */
public interface TwitterAdsTargetingApi {
    /**
     * @param q (optional) Search results for matching a specific platform.
     * @return all possible targeting platforms to choose from
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/targeting_criteria/platforms">https://dev.twitter.com/ads/reference/get/targeting_criteria/platforms</a>
     */
    BaseAdsListResponseIterable<TargetingCriteria> getAllTargetingPlatforms(String q) throws TwitterException;

    /**
     * @param locationType (optional) Scope the results to a specific type of location.
     * @param q            (optional) Search for a specific location.
     * @param countryCode  (optional) Specify a country code to retrieve results from.
     * @param count        (optional) Limit the number of results to the given count.
     * @return all possible targeting locations to choose from
     * @throws TwitterException
     * @see <a href="https://dev.twitter.com/ads/reference/get/targeting_criteria/locations">https://dev.twitter.com/ads/reference/get/targeting_criteria/locations</a>
     */
    BaseAdsListResponseIterable<TargetingLocation> getAllTargetingLocations(Optional<LocationType> locationType, String q,
                                                                            String countryCode, Optional<Integer> count) throws TwitterException;

}
