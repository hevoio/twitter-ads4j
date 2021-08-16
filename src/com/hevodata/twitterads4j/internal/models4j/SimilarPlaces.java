package com.hevodata.twitterads4j.internal.models4j;

/**
 *
 * @since Twitter4J 2.1.7
 */
public interface SimilarPlaces extends ResponseList<Place> {
    /**
     * Returns the token needed to be able to create a new place  with {@link twitter4jads.api.PlacesGeoResources#createPlace(String, String, String, GeoLocation, String)}.
     *
     * @return token the token needed to be able to create a new place with {@link twitter4jads.api.PlacesGeoResources#createPlace(String, String, String, GeoLocation, String)}
     */
    String getToken();
}
