package com.hevodata.twitterads4j.models.ads;

import com.hevodata.twitterads4j.internal.models4j.TwitterException;

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
