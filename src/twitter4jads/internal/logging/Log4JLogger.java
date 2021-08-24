package twitter4jads.internal.logging;

/**
 *
 * @since Twitter4J 2.1.1
 */
final class Log4JLogger extends Logger {
    private final org.apache.log4j.Logger LOGGER;

    Log4JLogger(org.apache.log4j.Logger logger) {
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
        return LOGGER.isEnabledFor(org.apache.log4j.Level.WARN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return LOGGER.isEnabledFor(org.apache.log4j.Level.WARN);
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
        debug(message + message2);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String message) {
        LOGGER.info(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message, String message2) {
        info(message + message2);
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
        warn(message + message2);
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
