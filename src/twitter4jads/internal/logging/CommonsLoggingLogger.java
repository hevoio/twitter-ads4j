package twitter4jads.internal.logging;

/**
 *
 * @since Twitter4J 2.1.1
 */
final class CommonsLoggingLogger extends Logger {

    private final org.apache.commons.logging.Log LOGGER;

    CommonsLoggingLogger(org.apache.commons.logging.Log logger) {
        LOGGER = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return LOGGER.isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return LOGGER.isWarnEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return LOGGER.isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message) {
        LOGGER.debug(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message, String message2) {
        LOGGER.debug(message + message2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message) {
        LOGGER.info(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message, String message2) {
        LOGGER.info(message + message2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(String message) {
        LOGGER.warn(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(String message, String message2) {
        LOGGER.warn(message + message2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message) {
        LOGGER.error(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message, Throwable th) {
        LOGGER.error(message, th);
    }

}
