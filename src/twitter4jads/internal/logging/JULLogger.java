package twitter4jads.internal.logging;

import java.util.logging.Level;

/**
 *
 * @since Twitter4J 2.2.4
 */
final class JULLogger extends Logger {
    private final java.util.logging.Logger LOGGER;

    JULLogger(java.util.logging.Logger logger) {
        LOGGER = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return LOGGER.isLoggable(Level.FINE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return LOGGER.isLoggable(Level.INFO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return LOGGER.isLoggable(Level.WARNING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return LOGGER.isLoggable(Level.SEVERE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message) {
        LOGGER.fine(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message, String message2) {
        LOGGER.fine(message + message2);
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
        LOGGER.warning(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(String message, String message2) {
        LOGGER.warning(message + message2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message) {
        LOGGER.severe(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message, Throwable th) {
        LOGGER.severe(message + th.getMessage());
    }
}
