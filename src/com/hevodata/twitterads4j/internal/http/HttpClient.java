package com.hevodata.twitterads4j.internal.http;


import com.hevodata.twitterads4j.internal.models4j.TwitterException;

/**
 * A utility class to handle HTTP request/response.
 *
 *
 */
public interface HttpClient {

    HttpResponse request(HttpRequest req) throws TwitterException;

    void shutdown();
}
