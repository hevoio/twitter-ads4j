package twitter4jads.internal.http;

import java.util.Map;

public interface HttpClientWrapperConfiguration extends HttpClientConfiguration {
    /**
     * @return request headers
     */
    Map<String, String> getRequestHeaders();
}
