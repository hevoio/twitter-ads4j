package twitter4jads;

import com.google.gson.Gson;
import twitter4jads.auth.Authorization;
import twitter4jads.auth.OAuthSupport;
import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.TwitterAPIMonitor;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.models4j.TwitterImpl;
import twitter4jads.internal.models4j.Version;
import twitter4jads.models.ads.HttpVerb;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static twitter4jads.util.TwitterAdUtil.constructBaseAdsResponse;

/**
 *
 * Date: 4/4/16
 * Time: 7:07 PM
 */
public class TwitterAdsClient extends TwitterImpl implements OAuthSupport {

    public static final String ADS_API_URL = "https://ads-api.twitter.com/";
    public static final Gson GSON_INSTANCE = new Gson();

    private static final Map<String, String> requestHeaders;

    static {
        requestHeaders = new HashMap<>();
        requestHeaders.put("X-Twitter-Client-Version", Version.getVersion());
        requestHeaders.put("X-Twitter-Client-URL", "http://twitter4jads.org/en/twitter4jads-" + Version.getVersion() + ".xml");
        requestHeaders.put("X-Twitter-Client", "Twitter4J");
        requestHeaders.put("User-Agent", "twitter4jads http://twitter4jads.org/ /" + Version.getVersion());
        requestHeaders.put("Accept-Encoding", "gzip");
    }

    public TwitterAdsClient(Configuration conf, Authorization auth) {
        super(conf, auth);
    }



    public String getBaseAdsAPIUrl() {
        return ADS_API_URL;//conf.getAdsAPIURL();
    }


    public <T> BaseAdsListResponseIterable<T> executeHttpListRequest(String baseUrl, List<HttpParameter> params, Type type) throws TwitterException {
        return executeHttpListRequest(baseUrl, params, type, false);
    }

    public <T> BaseAdsListResponseIterable<T> executeHttpListRequest(String baseUrl, List<HttpParameter> params, Type type,
                                                                     boolean isCostBasedRateLimit) throws TwitterException {
        HttpResponse httpResponse;
        if (params != null) {
            httpResponse = get(baseUrl, params.toArray(new HttpParameter[params.size()]));
        } else {
            httpResponse = get(baseUrl);
        }
        BaseAdsListResponseIterable<T> response;
        try {
            response = constructBaseAdsListResponse(baseUrl, httpResponse, params, type, isCostBasedRateLimit);
        } catch (IOException e) {
            throw new TwitterException("Failed to parse response.", e);
        }

        return response;
    }

    public <T> BaseAdsListResponseIterable<T> constructBaseAdsListResponse(String baseUrl, HttpResponse httpResponse, List<HttpParameter> params,
                                                                           Type type, boolean isCostBasedRateLimit)
            throws TwitterException, IOException {
        return new BaseAdsListResponseIterable<>(this, baseUrl, params, type, httpResponse, isCostBasedRateLimit);
    }

    public <T> BaseAdsResponse<T> executeHttpRequest(String baseUrl, HttpParameter[] params, Type type, HttpVerb httpVerb) throws TwitterException {
        HttpResponse httpResponse;
        String stringResponse;
        BaseAdsResponse<T> response = null;
        //noinspection Duplicates
        switch (httpVerb) {
            case GET:
                try {
                    httpResponse = get(baseUrl, params);
                    stringResponse = httpResponse.asString();
                    response = constructBaseAdsResponse(httpResponse, stringResponse, type);
                } catch (IOException e) {
                    throw new TwitterException("Failed to parse response.", e);
                }
                break;
            case POST:
                //noinspection Duplicates
                try {
                    httpResponse = postRequest(baseUrl, params);
                    stringResponse = httpResponse.asString();
                    response = constructBaseAdsResponse(httpResponse, stringResponse, type);
                } catch (IOException e) {
                    throw new TwitterException("Failed to parse response.", e);
                }
                break;
        }

        return response;
    }

    public HttpResponse postRequest(String url, HttpParameter[] params) throws TwitterException {
        return post(url, params);
    }
    //https://twittercommunity.com/t/details-for-media-library-media-status/117756

    // ------------------------------------------------------------------- PRIVATE METHODS -------------------------------------------------

    public HttpResponse get(String url) throws TwitterException {
        ensureAuthorizationEnabled();

        if (!conf.isMBeanEnabled()) {
            return http.get(url, null, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url, null, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                TwitterAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    public HttpResponse get(String url, HttpParameter... params) throws TwitterException {
        ensureAuthorizationEnabled();
        if (!conf.isMBeanEnabled()) {
            return http.get(url, params, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url, params, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                TwitterAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    public HttpResponse post(String url, HttpParameter... params) throws TwitterException {
        ensureAuthorizationEnabled();
        if (!conf.isMBeanEnabled()) {
            return http.post(url, params, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.post(url, params, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                TwitterAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private boolean isOk(HttpResponse response) {
        return response != null && response.getStatusCode() < 300;
    }
}

