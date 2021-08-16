package com.hevodata.twitterads4j.internal.logging;

final class StdOutLoggerFactory extends LoggerFactory {
    private static final Logger SINGLETON = new StdOutLogger();

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class clazz) {
        return SINGLETON;
    }
}
