package twitter4jads.internal.http;


import twitter4jads.internal.models4j.TwitterException;

/**
 * A utility class to handle HTTP request/response.
 *
 *
 */
public interface HttpClient {

    HttpResponse request(HttpRequest req) throws TwitterException;

    void shutdown();
}
