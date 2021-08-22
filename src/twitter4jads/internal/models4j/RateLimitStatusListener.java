package twitter4jads.internal.models4j;

/**
 *  Andrew Hedges - andrew.hedges at gmail.com
 */
public interface RateLimitStatusListener {

    /**
     * Called when the response contains rate limit status.
     *
     * @param event rate limit status event.
     */
    public void onRateLimitStatus(RateLimitStatusEvent event);

    /**
     * Called when the account or IP address is hitting the rate limit.<br>
     * onRateLimitStatus will be also called before this event.
     *
     * @param event rate limit status event.
     */
    public void onRateLimitReached(RateLimitStatusEvent event);
}
