package twitter4jads.models.ads;

import twitter4jads.internal.models4j.TwitterException;

public class TwitterRuntimeException extends RuntimeException {

    private final TwitterException twitterException;

    public TwitterRuntimeException(Throwable cause, TwitterException twitterException) {
        super(cause);
        this.twitterException = twitterException;
    }

    public TwitterException getTwitterException() {
        return twitterException;
    }
}
