package twitter4jads.internal.http;

import twitter4jads.auth.Authorization;
import twitter4jads.conf.ConfigurationContext;
import org.apache.commons.collections.MapUtils;
import twitter4jads.internal.models4j.TwitterException;

import java.util.HashMap;
import java.util.Map;

import static twitter4jads.internal.http.RequestMethod.*;

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


    public HttpResponse post(String url) throws TwitterException {
        return request(new HttpRequest(POST, url, null, null, null, this.requestHeaders));
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
