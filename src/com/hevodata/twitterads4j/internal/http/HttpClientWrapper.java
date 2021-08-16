package com.hevodata.twitterads4j.internal.http;

import com.hevodata.twitterads4j.auth.Authorization;
import com.hevodata.twitterads4j.conf.ConfigurationContext;
import org.apache.commons.collections.MapUtils;
import com.hevodata.twitterads4j.internal.models4j.TwitterException;

import java.util.HashMap;
import java.util.Map;

import static com.hevodata.twitterads4j.internal.http.RequestMethod.*;

/**
 * HTTP Client wrapper with handy request methods, ResponseListener mechanism
 *
 *
 */
public final class HttpClientWrapper implements java.io.Serializable {
    private final HttpClientWrapperConfiguration wrapperConf;
    private HttpClient http;

    private final Map<String, String> requestHeaders;
    private static final long serialVersionUID = -6511977105603119379L;
    private HttpResponseListener httpResponseListener;

    public HttpClientWrapper(HttpClientWrapperConfiguration wrapperConf) {
        this.wrapperConf = wrapperConf;
        requestHeaders = wrapperConf.getRequestHeaders();
        http = HttpClientFactory.getInstance(wrapperConf);
    }

    // never used with this project. Just for handiness for those using this class.
    public HttpClientWrapper() {
        this.wrapperConf = ConfigurationContext.getInstance();
        requestHeaders = wrapperConf.getRequestHeaders();
        http = HttpClientFactory.getInstance(wrapperConf);
    }

    public void shutdown() {
        http.shutdown();
    }

    private HttpResponse request(HttpRequest req) throws TwitterException {
        HttpResponse res;
        try {
            res = http.request(req);
            //fire HttpResponseEvent
            if (httpResponseListener != null) {
                httpResponseListener.httpResponseReceived(new HttpResponseEvent(req, res, null));
            }
        } catch (TwitterException te) {
            if (httpResponseListener != null) {
                httpResponseListener.httpResponseReceived(new HttpResponseEvent(req, null, te));
            }
            throw te;
        }
        return res;
    }

    public void setHttpResponseListener(HttpResponseListener listener) {
        httpResponseListener = listener;
    }

    public HttpResponse get(String url, HttpParameter[] parameters, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(GET, url, parameters, null, authorization, this.requestHeaders));
    }

    public HttpResponse get(String url, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(GET, url, null, null, authorization, this.requestHeaders));
    }

    public HttpResponse post(String url, HttpParameter[] parameters, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(POST, url, parameters, null, authorization, this.requestHeaders));
    }

    public HttpResponse post(String url, String requestBody, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(POST, url, null, requestBody, authorization, this.requestHeaders));
    }

    public HttpResponse putWithCustomHeaders(String url, HttpParameter[] parameters, Authorization authorization,
                                             Map<String, String> customRequestHeaders, boolean isTonUpload) throws TwitterException {
        Map<String, String> headers = new HashMap<>();
        if (MapUtils.isNotEmpty(requestHeaders)) {
            headers.putAll(this.requestHeaders);
        }
        headers.putAll(customRequestHeaders);
        HttpRequest req = new HttpRequest(PUT, url, parameters, null, authorization, headers);
        req.setTonUploadRequest(isTonUpload);
        return request(req);
    }

    public HttpResponse postWithCustomHeaders(String url, HttpParameter[] parameters, Authorization authorization,
                                              Map<String, String> customRequestHeaders, boolean isTonUpload) throws TwitterException {
        Map<String, String> headers = new HashMap<>();
        if (MapUtils.isNotEmpty(requestHeaders)) {
            headers.putAll(this.requestHeaders);
        }
        headers.putAll(customRequestHeaders);
        HttpRequest req = new HttpRequest(POST, url, parameters, null, authorization, headers);
        req.setTonUploadRequest(isTonUpload);
        return request(req);
    }

    public HttpResponse post(String url, HttpParameter[] parameters) throws TwitterException {
        return request(new HttpRequest(POST, url, parameters, null, null, this.requestHeaders));
    }

    public HttpResponse post(String url, HttpParameter[] parameters, Map<String, String> requestHeaders) throws TwitterException {
        Map<String, String> headers = new HashMap<String, String>(this.requestHeaders);
        if (requestHeaders != null) {
            headers.putAll(requestHeaders);
        }

        return request(new HttpRequest(POST, url, parameters, null, null, headers));
    }

    public HttpResponse post(String url, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(POST, url, null, null, authorization, this.requestHeaders));
    }

    public HttpResponse postBatchRequest(String url, HttpParameter[] parameters, Authorization authorization, String requestBody)
            throws TwitterException {
        return request(new HttpRequest(POST, url, parameters, requestBody, authorization, this.requestHeaders));
    }

    public HttpResponse post(String url) throws TwitterException {
        return request(new HttpRequest(POST, url, null, null, null, this.requestHeaders));
    }

    public HttpResponse delete(String url, HttpParameter[] parameters, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(DELETE, url, parameters, null, authorization, this.requestHeaders));
    }

    public HttpResponse delete(String url, HttpParameter[] parameters) throws TwitterException {
        return request(new HttpRequest(DELETE, url, parameters, null, null, this.requestHeaders));
    }

    public HttpResponse delete(String url, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(DELETE, url, null, null, authorization, this.requestHeaders));
    }

    public HttpResponse delete(String url) throws TwitterException {
        return request(new HttpRequest(DELETE, url, null, null, null, this.requestHeaders));
    }

    public HttpResponse head(String url, HttpParameter[] parameters, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(HEAD, url, parameters, null, authorization, this.requestHeaders));
    }

    public HttpResponse head(String url, HttpParameter[] parameters) throws TwitterException {
        return request(new HttpRequest(HEAD, url, parameters, null, null, this.requestHeaders));
    }

    public HttpResponse head(String url, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(HEAD, url, null, null, authorization, this.requestHeaders));
    }

    public HttpResponse head(String url) throws TwitterException {
        return request(new HttpRequest(HEAD, url, null, null, null, this.requestHeaders));
    }

    public HttpResponse put(String url, HttpParameter[] parameters, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(PUT, url, parameters, null, authorization, this.requestHeaders));
    }

    public HttpResponse put(String url, HttpParameter[] parameters) throws TwitterException {
        return request(new HttpRequest(PUT, url, parameters, null, null, this.requestHeaders));
    }

    public HttpResponse put(String url, Authorization authorization) throws TwitterException {
        return request(new HttpRequest(PUT, url, null, null, authorization, this.requestHeaders));
    }

    public HttpResponse put(String url) throws TwitterException {
        return request(new HttpRequest(PUT, url, null, null, null, this.requestHeaders));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HttpClientWrapper that = (HttpClientWrapper) o;

        if (!http.equals(that.http)) {
            return false;
        }
        if (!requestHeaders.equals(that.requestHeaders)) {
            return false;
        }
        if (!wrapperConf.equals(that.wrapperConf)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = wrapperConf.hashCode();
        result = 31 * result + http.hashCode();
        result = 31 * result + requestHeaders.hashCode();
        return result;
    }
}
