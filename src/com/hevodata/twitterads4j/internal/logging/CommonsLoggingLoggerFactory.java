package com.hevodata.twitterads4j.internal.logging;

/**
 *
 * @since Twitter4J 2.1.1
 */
final class CommonsLoggingLoggerFactory extends LoggerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class clazz) {
        return new CommonsLoggingLogger(org.apache.commons.logging.LogFactory.getLog(clazz));
    }
}