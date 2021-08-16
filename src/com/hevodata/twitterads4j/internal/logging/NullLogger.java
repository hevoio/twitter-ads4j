package com.hevodata.twitterads4j.internal.logging;

final class NullLogger extends Logger {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message, String message2) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message, String message2) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(String message) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(String message, String message2) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message, Throwable th) {
    }
}
