package com.hevodata.twitterads4j.internal.logging;

final class Log4JLoggerFactory extends LoggerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class clazz) {
        return new Log4JLogger(org.apache.log4j.Logger.getLogger(clazz));
    }
}
