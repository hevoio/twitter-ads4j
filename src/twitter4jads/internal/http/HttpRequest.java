package twitter4jads.internal.http;

import twitter4jads.auth.Authorization;

import java.util.Arrays;
import java.util.Map;

/**
 * HTTP Request parameter object
 *
 *
 */
public final class HttpRequest implements java.io.Serializable {

    private final RequestMethod method;

    private final String url;

    private final HttpParameter[] parameters;

    private final String requestBody;

    private final Authorization authorization;

    private Map<String, String> requestHeaders;

    private boolean tonUploadRequest;
    private static final long serialVersionUID = -3463594029098858381L;


    private static final HttpParameter[] NULL_PARAMETERS = new HttpParameter[0];

    /**
     * @param method         Specifies the HTTP method
     * @param url            the request to request
     * @param parameters     parameters
     * @param requestBody    requestBody
     * @param authorization  Authentication implementation. Currently BasicAuthentication, OAuthAuthentication and NullAuthentication are supported.
     * @param requestHeaders
     */
    public HttpRequest(RequestMethod method, String url, HttpParameter[] parameters, String requestBody, Authorization authorization,
                       Map<String, String> requestHeaders) {
        this.method = method;
        if (method != RequestMethod.POST && method != RequestMethod.PUT && parameters != null && parameters.length != 0) {
            if (url.contains("?")) {
                this.url = url + "&" + HttpParameter.encodeParameters(parameters);
            } else {
                this.url = url + "?" + HttpParameter.encodeParameters(parameters);
            }
            this.parameters = NULL_PARAMETERS;
        } else {
            this.url = url;
            this.parameters = parameters;
        }
        this.requestBody = requestBody;
        this.authorization = authorization;
        this.requestHeaders = requestHeaders;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public HttpParameter[] getParameters() {
        return parameters;
    }

    public String getRequestBody() {return requestBody;}

    public String getURL() {
        return url;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public boolean isTonUploadRequest() {
        return tonUploadRequest;
    }

    public void setTonUploadRequest(boolean tonUploadRequest) {
        this.tonUploadRequest = tonUploadRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HttpRequest that = (HttpRequest) o;

        if (authorization != null ? !authorization.equals(that.authorization) : that.authorization != null) {
            return false;
        }
        if (!Arrays.equals(parameters, that.parameters)) {
            return false;
        }
        if (requestHeaders != null ? !requestHeaders.equals(that.requestHeaders) : that.requestHeaders != null) {
            return false;
        }
        if (method != null ? !method.equals(that.method) : that.method != null) {
            return false;
        }
        if (url != null ? !url.equals(that.url) : that.url != null) {
            return false;
        }
        if (requestBody != null ? !requestBody.equals(that.requestBody) : that.requestBody != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (parameters != null ? Arrays.hashCode(parameters) : 0);
        result = 31 * result + (authorization != null ? authorization.hashCode() : 0);
        result = 31 * result + (requestHeaders != null ? requestHeaders.hashCode() : 0);
        result = 31 * result + (requestBody != null ? requestBody.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
               "requestMethod=" + method +
               ", url='" + url + '\'' +
               ", postParams=" + (parameters == null ? null : Arrays.asList(parameters)) +
               ", authentication=" + authorization +
               ", requestHeaders=" + requestHeaders +
               ", requestBody=" + requestBody +
               '}';
    }
}
