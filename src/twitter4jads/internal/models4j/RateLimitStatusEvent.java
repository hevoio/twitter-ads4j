package twitter4jads.internal.models4j;

/**
 * A data interface representing Twitter REST API's rate limit status
 *
 *
 * @see <a href="https://dev.twitter.com/docs/rate-limiting">Rate Limiting | Twitter Developers</a>
 */
public final class RateLimitStatusEvent extends java.util.EventObject {

    private RateLimitStatus rateLimitStatus;

    private boolean isAccountRateLimitStatus;
    private static final long serialVersionUID = -2332507741769177298L;

    RateLimitStatusEvent(Object source, RateLimitStatus rateLimitStatus, boolean isAccountRateLimitStatus) {
        super(source);
        this.rateLimitStatus = rateLimitStatus;
        this.isAccountRateLimitStatus = isAccountRateLimitStatus;
    }

    public RateLimitStatus getRateLimitStatus() {
        return rateLimitStatus;
    }

    public boolean isAccountRateLimitStatus() {
        return isAccountRateLimitStatus;
    }

    public boolean isIPRateLimitStatus() {
        return !isAccountRateLimitStatus;
    }

}
