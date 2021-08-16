package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.org.json.JSONException;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.Place;
import com.hevodata.twitterads4j.internal.models4j.ResponseList;
import com.hevodata.twitterads4j.internal.models4j.SimilarPlaces;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

/**
 *
 * @since Twitter4J 2.1.7
 */
public class SimilarPlacesImpl extends ResponseListImpl<Place> implements SimilarPlaces {
    private static final long serialVersionUID = -7897806745732767803L;
    private final String token;

    SimilarPlacesImpl(ResponseList<Place> places, HttpResponse res, String token) {
        super(places.size(), res);
        this.addAll(places);
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() {
        return token;
    }

    /*package*/
    static SimilarPlaces createSimilarPlaces(HttpResponse res, Configuration conf) throws TwitterException {
        JSONObject json = null;
        try {
            json = res.asJSONObject();
            JSONObject result = json.getJSONObject("result");
            return new SimilarPlacesImpl(PlaceJSONImpl.createPlaceList(result.getJSONArray("places"), res, conf), res
                    , result.getString("token"));
        } catch (JSONException jsone) {
            throw new TwitterException(jsone.getMessage() + ":" + json.toString(), jsone);
        }
    }

}
