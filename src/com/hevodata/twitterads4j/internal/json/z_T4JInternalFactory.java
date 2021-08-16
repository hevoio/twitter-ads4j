package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.http.HttpResponse;
import com.hevodata.twitterads4j.internal.models4j.*;
import com.hevodata.twitterads4j.internal.org.json.JSONObject;

import java.util.Map;

/**
 *
 * @since Twitter4J 2.2.4
 */
public interface z_T4JInternalFactory extends java.io.Serializable {
    Status createStatus(JSONObject json) throws TwitterException;

    User createUser(JSONObject json) throws TwitterException;

    UserList createAUserList(JSONObject json) throws TwitterException;

    DirectMessage createDirectMessage(JSONObject json) throws TwitterException;

    Map<String , RateLimitStatus> createRateLimitStatuses(HttpResponse res) throws TwitterException;

    Status createStatus(HttpResponse res) throws TwitterException;

    ResponseList<Status> createStatusList(HttpResponse res) throws TwitterException;

    Trends createTrends(HttpResponse res) throws TwitterException;

    User createUser(HttpResponse res) throws TwitterException;

    ResponseList<User> createUserList(HttpResponse res) throws TwitterException;

    ResponseList<User> createUserListFromJSONArray(HttpResponse res) throws TwitterException;

    ResponseList<User> createUserListFromJSONArray_Users(HttpResponse res) throws TwitterException;

    PagableResponseList<User> createPagableUserList(HttpResponse res) throws TwitterException;

    UserList createAUserList(HttpResponse res) throws TwitterException;

    PagableResponseList<UserList> createPagableUserListList(HttpResponse res) throws TwitterException;

    ResponseList<UserList> createUserListList(HttpResponse res) throws TwitterException;

    DirectMessage createDirectMessage(HttpResponse res) throws TwitterException;

    ResponseList<DirectMessage> createDirectMessageList(HttpResponse res) throws TwitterException;

    AccountSettings createAccountSettings(HttpResponse res) throws TwitterException;

    ResponseList<Location> createLocationList(HttpResponse res) throws TwitterException;

    Place createPlace(HttpResponse res) throws TwitterException;

    ResponseList<Place> createPlaceList(HttpResponse res) throws TwitterException;

    SimilarPlaces createSimilarPlaces(HttpResponse res) throws TwitterException;

    RelatedResults createRelatedResults(HttpResponse res) throws TwitterException;

    TwitterAPIConfiguration createTwitterAPIConfiguration(HttpResponse res) throws TwitterException;

    <T> ResponseList<T> createEmptyResponseList();

    Media createMediaUpload(HttpResponse res) throws TwitterException;
}
