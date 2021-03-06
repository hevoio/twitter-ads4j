package twitter4jads.internal.http;

import twitter4jads.conf.ConfigurationContext;
import twitter4jads.internal.logging.Logger;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.util.z_T4JInternalStringUtil;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static twitter4jads.internal.http.RequestMethod.POST;
import static twitter4jads.internal.http.RequestMethod.PUT;

public class HttpClientImpl extends HttpClientBase implements HttpResponseCode, Serializable {
    private static final Logger logger = Logger.getLogger(HttpClientImpl.class);

    private static final long serialVersionUID = -8819171414069621503L;

    static {
        if (ConfigurationContext.getInstance().isDalvik()) {
            // quick and dirty workaround for TFJ-296
            // it must be an Android/Dalvik/Harmony side issue!!!!
            System.setProperty("http.keepAlive", "false");
        }
    }

    public HttpClientImpl() {
        super(ConfigurationContext.getInstance());
    }

    public HttpClientImpl(HttpClientConfiguration conf) {
        super(conf);
    }

    private static final Map<HttpClientConfiguration, HttpClient> instanceMap = new HashMap<HttpClientConfiguration, HttpClient>(1);

    public static HttpClient getInstance(HttpClientConfiguration conf) {
        HttpClient client = instanceMap.get(conf);
        if (null == client) {
            client = new HttpClientImpl(conf);
            instanceMap.put(conf, client);
        }
        return client;
    }

    public HttpResponse get(String url) throws TwitterException {
        return request(new HttpRequest(RequestMethod.GET, url, null, null, null, null));
    }

    public HttpResponse post(String url, HttpParameter[] params) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, url, params, null, null, null));
    }

    public HttpResponse post(String url, String requestBody) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, url, null, requestBody, null, null));
    }

    @Override
    public HttpResponse request(HttpRequest req) throws TwitterException {
        int retriedCount;
        int retry = CONF.getHttpRetryCount() + 1;
        HttpResponse res = null;
        for (retriedCount = 0; retriedCount < retry; retriedCount++) {
            int responseCode = -1;
            try {
                HttpURLConnection con;
                OutputStream os = null;
                try {
                    con = getConnection(req.getURL());
                    con.setDoInput(true);
                    setHeaders(req, con);
                    con.setRequestMethod(req.getMethod().name());
                    if (req.getMethod() == POST || req.getMethod() == PUT) {
                        if (HttpParameter.containsFile(req.getParameters())) {
                            String boundary = "----Twitter4J-upload" + System.currentTimeMillis();
                            if (!req.isTonUploadRequest()) {
                                //ton upload is non multi part
                                con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                            }
                            boundary = "--" + boundary;
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            DataOutputStream out = new DataOutputStream(os);
                            for (HttpParameter param : req.getParameters()) {
                                if (param.isNonMultipartUpload()) {
                                    out.write(param.getFileData(), 0, param.getFileData().length);
                                } else if (param.isFile()) {
                                    write(out, boundary + "\r\n");
                                    write(out, "Content-Disposition: form-data; name=\"" + param.getName() + "\"; filename=\"" +
                                               param.getFile().getName() + "\"\r\n");
                                    write(out, "Content-Type: " + param.getContentType() + "\r\n\r\n");
                                    BufferedInputStream in =
                                            new BufferedInputStream(param.hasFileBody() ? param.getFileBody() : new FileInputStream(param.getFile()));
                                    byte[] buff = new byte[1024];
                                    int length;
                                    try {
                                        while ((length = in.read(buff)) != -1) {
                                            out.write(buff, 0, length);
                                        }
                                        write(out, "\r\n");
                                    } finally {
                                        in.close();
                                    }
                                } else {
                                    write(out, boundary + "\r\n");
                                    write(out, "Content-Disposition: form-data; name=\"" + param.getName() + "\"\r\n");
                                    write(out, "Content-Type: text/plain; charset=UTF-8\r\n\r\n");
                                    logger.debug(param.getValue());
                                    out.write(param.getValue().getBytes("UTF-8"));
                                    write(out, "\r\n");
                                }
                            }
                            if (!req.isTonUploadRequest()) {
                                write(out, boundary + "--\r\n");
                                write(out, "\r\n");
                            }

                        } else if (req.getRequestBody() != null) {
                            con.setRequestProperty("Content-Type", "application/json");
                            String payload = req.getRequestBody();
                            logger.debug("Payload: ", payload);
                            byte[] bytes = payload.getBytes("UTF-8");
                            con.setRequestProperty("Content-Length", Integer.toString(bytes.length));
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            os.write(bytes);
                        } else if (!req.isTonUploadRequest() || req.getParameters() != null) {
                            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            String postParam = HttpParameter.encodeParameters(req.getParameters());
                            logger.debug("Post Params: ", postParam);
                            byte[] bytes = postParam.getBytes("UTF-8");
                            con.setRequestProperty("Content-Length", Integer.toString(bytes.length));
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            os.write(bytes);
                        }
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                    }
                    res = new HttpResponseImpl(con, CONF);
                    responseCode = con.getResponseCode();
                    if (logger.isDebugEnabled()) {
                        logger.debug("Response: ");
                        Map<String, List<String>> responseHeaders = con.getHeaderFields();
                        for (String key : responseHeaders.keySet()) {
                            List<String> values = responseHeaders.get(key);
                            for (String value : values) {
                                if (key != null) {
                                    logger.debug(key + ": " + value);
                                } else {
                                    logger.debug(value);
                                }
                            }
                        }
                    }
                    if (responseCode < OK || (responseCode != FOUND && responseCode != RESUME_INCOMPLETE && MULTIPLE_CHOICES <= responseCode)) {
                        if (responseCode == ENHANCE_YOUR_CLAIM ||
                            responseCode == BAD_REQUEST ||
                            responseCode < INTERNAL_SERVER_ERROR ||
                            retriedCount == CONF.getHttpRetryCount()) {
                            throw new TwitterException(res.asString(), req, res);
                        }
                        // will retry if the status code is INTERNAL_SERVER_ERROR
                    } else {
                        break;
                    }
                } finally {
                    try {
                        os.close();
                    } catch (Exception ignore) {
                    }
                }
            } catch (IOException ioe) {
                // connection timeout or read timeout
                if (retriedCount == CONF.getHttpRetryCount()) {
                    throw new TwitterException(ioe.getMessage(), ioe, responseCode);
                }
            }
            try {
                if (logger.isDebugEnabled() && res != null) {
                    res.asString();
                }
                logger.debug("Sleeping " + CONF.getHttpRetryIntervalSeconds() + " seconds until the next retry.");
                Thread.sleep(CONF.getHttpRetryIntervalSeconds() * 1000);
            } catch (InterruptedException ignore) {
                //nothing to do
            }
        }
        return res;
    }


    /**
     * sets HTTP headers
     *
     * @param req        The request
     * @param connection HttpURLConnection
     */
    private void setHeaders(HttpRequest req, HttpURLConnection connection) {
        if (logger.isDebugEnabled()) {
            logger.debug("Request: ");
            logger.debug(req.getMethod().name() + " ", req.getURL());
        }

        String authorizationHeader;
        if (req.getAuthorization() != null && (authorizationHeader = req.getAuthorization().getAuthorizationHeader(req)) != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authorization: ", z_T4JInternalStringUtil.maskString(authorizationHeader));
            }
            connection.addRequestProperty("Authorization", authorizationHeader);
        }
        if (req.getRequestHeaders() != null) {
            for (String key : req.getRequestHeaders().keySet()) {
                connection.addRequestProperty(key, req.getRequestHeaders().get(key));
                logger.debug(key + ": " + req.getRequestHeaders().get(key));
            }
        }
    }

    protected HttpURLConnection getConnection(String url) throws IOException {
        url = ensureHttps(url);
        HttpURLConnection con;
        if (isProxyConfigured()) {
            if (CONF.getHttpProxyUser() != null && !CONF.getHttpProxyUser().equals("")) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Proxy AuthUser: " + CONF.getHttpProxyUser());
                    logger.debug("Proxy AuthPassword: " + z_T4JInternalStringUtil.maskString(CONF.getHttpProxyPassword()));
                }
                Authenticator.setDefault(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        //respond only to proxy auth requests
                        if (getRequestorType().equals(RequestorType.PROXY)) {
                            return new PasswordAuthentication(CONF.getHttpProxyUser(), CONF.getHttpProxyPassword().toCharArray());
                        } else {
                            return null;
                        }
                    }
                });
            }
            final Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(CONF.getHttpProxyHost(), CONF.getHttpProxyPort()));
            if (logger.isDebugEnabled()) {
                logger.debug("Opening proxied connection(" + CONF.getHttpProxyHost() + ":" + CONF.getHttpProxyPort() + ")");
            }
            con = (HttpURLConnection) new URL(url).openConnection(proxy);
        } else {
            con = (HttpURLConnection) new URL(url).openConnection();
        }
        if (CONF.getHttpConnectionTimeout() > 0) {
            con.setConnectTimeout(CONF.getHttpConnectionTimeout());
        }
        if (CONF.getHttpReadTimeout() > 0) {
            con.setReadTimeout(CONF.getHttpReadTimeout());
        }
        con.setInstanceFollowRedirects(false);
        return con;
    }

    private String ensureHttps(String url) {
        if (url.startsWith("http://")) {
            logger.error("Following url was still using (http) protocol: " + url);
            url = "https://" + url.substring("http://".length());
        }
        return url;
    }
}
