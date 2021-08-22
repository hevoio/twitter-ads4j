package twitter4jads.internal.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class HttpResponseImpl extends HttpResponse {
    private HttpURLConnection con;

    HttpResponseImpl(HttpURLConnection con, HttpClientConfiguration conf) throws IOException {
        super(conf);
        this.con = con;
        this.statusCode = con.getResponseCode();
        if (null == (is = con.getErrorStream())) {
            is = con.getInputStream();
        }
        if (is != null && "gzip".equals(con.getContentEncoding())) {
            // the response is gzipped
            is = new StreamingGZIPInputStream(is);
        }
    }

    // for test purpose
    /*package*/ HttpResponseImpl(String content) {
        super();
        this.responseAsString = content;
    }

    @Override
    public String getResponseHeader(String name) {
        return con.getHeaderField(name);
    }

    @Override
    public Map<String, List<String>> getResponseHeaderFields() {
        return con.getHeaderFields();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect() {
        con.disconnect();
    }
}
