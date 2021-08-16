package com.hevodata.twitterads4j.internal.logging;

import com.hevodata.twitterads4j.conf.ConfigurationContext;

final class StdOutLogger extends Logger {
    private static final boolean DEBUG = ConfigurationContext.getInstance().isDebugEnabled();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return DEBUG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message) {
        if (DEBUG) {
            System.out.println("[" + new java.util.Date() + "]" + message);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message, String message2) {
        if (DEBUG) {
            debug(message + message2);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message) {
        System.out.println("[" + new java.util.Date() + "]" + message);
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
        System.out.println("[" + new java.util.Date() + "]" + message);
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
        System.out.println("[" + new java.util.Date() + "]" + message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message, Throwable th) {
        System.out.println(message);
        th.printStackTrace(System.err);
    }
}
