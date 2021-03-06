package twitter4jads.internal.http;

import twitter4jads.conf.ConfigurationContext;
import twitter4jads.internal.logging.Logger;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.internal.org.json.JSONArray;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.org.json.JSONTokener;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * A data class representing HTTP Response
 *
 *
 */
public abstract class HttpResponse {
    private static final Logger logger = Logger.getLogger(HttpResponseImpl.class);
    protected final HttpClientConfiguration CONF;

    HttpResponse() {
        this.CONF = ConfigurationContext.getInstance();
    }

    public HttpResponse(HttpClientConfiguration conf) {
        this.CONF = conf;
    }

    protected int statusCode;
    protected String responseAsString = null;
    protected InputStream is;
    private boolean streamConsumed = false;

    public int getStatusCode() {
        return statusCode;
    }

    public abstract String getResponseHeader(String name);

    public abstract Map<String, List<String>> getResponseHeaderFields();

    /**
     * Returns the response stream.<br>
     * This method cannot be called after calling asString() or asDcoument()<br>
     * It is suggested to call disconnect() after consuming the stream.
     * <p/>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body stream
     * @throws TwitterException
     * @see #disconnect()
     */
    public InputStream asStream() {
        if (streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return is;
    }

    /**
     * Returns the response body as string.<br>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body
     * @throws TwitterException
     */
    public String asString() throws TwitterException {
        if (null == responseAsString) {
            BufferedReader br = null;
            InputStream stream = null;
            try {
                stream = asStream();
                if (null == stream) {
                    return null;
                }
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                StringBuilder buf = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    buf.append(line).append("\n");
                }
                this.responseAsString = buf.toString();
                logger.debug(responseAsString);
                stream.close();
                streamConsumed = true;
            } catch (IOException ioe) {
                throw new TwitterException(ioe.getMessage(), ioe);
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ignore) {
                    }
                }
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ignore) {
                    }
                }
                disconnectForcibly();
            }
        }
        return responseAsString;
    }

    private JSONObject json = null;

    /**
     * Returns the response body as twitter4jads.internal.org.json.JSONObject.<br>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body as twitter4jads.internal.org.json.JSONObject
     * @throws TwitterException
     */
    public JSONObject asJSONObject() throws TwitterException {
        if (json == null) {
            Reader reader = null;
            try {
                if (responseAsString == null) {
                    reader = asReader();
                    json = new JSONObject(new JSONTokener(reader));
                } else {
                    json = new JSONObject(responseAsString);
                }
                if (CONF.isPrettyDebugEnabled()) {
                    logger.debug(json.toString(1));
                } else {
                    logger.debug(responseAsString != null ? responseAsString :
                            json.toString());
                }
            } catch (JSONException jsone) {
                if (responseAsString == null) {
                    throw new TwitterException(jsone.getMessage(), jsone);
                } else {
                    throw new TwitterException(jsone.getMessage() + ":" + this.responseAsString, jsone);
                }
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignore) {
                    }
                }
                disconnectForcibly();
            }
        }
        return json;
    }

    private JSONArray jsonArray = null;

    /**
     * Returns the response body as twitter4jads.internal.org.json.JSONArray.<br>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body as twitter4jads.internal.org.json.JSONArray
     * @throws TwitterException
     */
    public JSONArray asJSONArray() throws TwitterException {
        if (jsonArray == null) {
            Reader reader = null;
            try {
                if (responseAsString == null) {
                    reader = asReader();
                    jsonArray = new JSONArray(new JSONTokener(reader));
                } else {
                    jsonArray = new JSONArray(responseAsString);
                }
                if (CONF.isPrettyDebugEnabled()) {
                    logger.debug(jsonArray.toString(1));
                } else {
                    logger.debug(responseAsString != null ? responseAsString :
                            jsonArray.toString());
                }
            } catch (JSONException jsone) {
                if (logger.isDebugEnabled()) {
                    throw new TwitterException(jsone.getMessage() + ":" + this.responseAsString, jsone);
                } else {
                    throw new TwitterException(jsone.getMessage(), jsone);
                }
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignore) {
                    }
                }
                disconnectForcibly();
            }
        }
        return jsonArray;
    }

    public Reader asReader() {
        try {
            return new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            return new InputStreamReader(is);
        }
    }

    private void disconnectForcibly() {
        try {
            disconnect();
        } catch (Exception ignore) {
        }
    }

    public abstract void disconnect() throws IOException;

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", responseAsString='" + responseAsString + '\'' +
                ", is=" + is +
                ", streamConsumed=" + streamConsumed +
                '}';
    }
}