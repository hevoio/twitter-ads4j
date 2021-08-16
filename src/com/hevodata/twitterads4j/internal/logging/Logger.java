package com.hevodata.twitterads4j.internal.logging;

import com.hevodata.twitterads4j.conf.Configuration;
import com.hevodata.twitterads4j.conf.ConfigurationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Logger {
    private static final LoggerFactory LOGGER_FACTORY;
    private static final String LOGGER_FACTORY_IMPLEMENTATION = "twitter4jads.loggerFactory";

    static {
        LoggerFactory loggerFactory = null;
        // -Dtwitter4jads.debug=true -Dtwitter4jads.loggerFactory=twitter4jads.internal.logging.StdOutLoggerFactory
        String loggerFactoryImpl = System.getProperty(LOGGER_FACTORY_IMPLEMENTATION);
        if (loggerFactoryImpl != null) {
            loggerFactory = getLoggerFactoryIfAvailable(loggerFactoryImpl, loggerFactoryImpl);
        }

        Configuration conf = ConfigurationContext.getInstance();
        // configuration in twitter4jads.properties
        // loggerFactory=twitter4jads.internal.logging.StdOutLoggerFactory
        loggerFactoryImpl = conf.getLoggerFactory();
        if (loggerFactoryImpl != null) {
            loggerFactory = getLoggerFactoryIfAvailable(loggerFactoryImpl, loggerFactoryImpl);
        }
        // use SLF4J if it's found in the classpath
        if (null == loggerFactory) {
            loggerFactory = getLoggerFactoryIfAvailable("org.slf4j.impl.StaticLoggerBinder", "twitter4jads.internal.logging.SLF4JLoggerFactory");
        }
        // otherwise, use commons-logging if it's found in the classpath
        if (null == loggerFactory) {
            loggerFactory = getLoggerFactoryIfAvailable("org.apache.commons.logging.Log", "twitter4jads.internal.logging.CommonsLoggingLoggerFactory");
        }
        // otherwise, use log4j if it's found in the classpath
        if (null == loggerFactory) {
            loggerFactory = getLoggerFactoryIfAvailable("org.apache.log4j.Logger", "twitter4jads.internal.logging.Log4JLoggerFactory");
        }
        // on Google App Engine, use java.util.logging
        if (null == loggerFactory) {
            loggerFactory = getLoggerFactoryIfAvailable("com.google.appengine.api.urlfetch.URLFetchService", "twitter4jads.internal.logging.JULLoggerFactory");
        }
        // otherwise, use the default logger
        if (null == loggerFactory) {
            loggerFactory = new StdOutLoggerFactory();
        }
        LOGGER_FACTORY = loggerFactory;

        try {
            Method method = conf.getClass().getMethod("dumpConfiguration", new Class[]{});
            method.setAccessible(true);
            method.invoke(conf);
        } catch (IllegalAccessException ignore) {
        } catch (InvocationTargetException ignore) {
        } catch (NoSuchMethodException ignore) {
        }
    }

    private static LoggerFactory getLoggerFactoryIfAvailable(String checkClassName, String implementationClass) {
        try {
            Class.forName(checkClassName);
            return (LoggerFactory) Class.forName(implementationClass).newInstance();
        } catch (ClassNotFoundException ignore) {
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (SecurityException ignore) {
            // Unsigned applets are not allowed to access System properties
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
        return null;
    }

    /**
     * Returns a Logger instance associated with the specified class.
     *
     * @param clazz class
     * @return logger instance
     */
    public static Logger getLogger(Class clazz) {
        return LOGGER_FACTORY.getLogger(clazz);
    }

    /**
     * tests if debug level logging is enabled
     *
     * @return if debug level logging is enabled
     */
    public abstract boolean isDebugEnabled();

    /**
     * tests if info level logging is enabled
     *
     * @return if info level logging is enabled
     */
    public abstract boolean isInfoEnabled();

    /**
     * tests if warn level logging is enabled
     *
     * @return if warn level logging is enabled
     */
    public abstract boolean isWarnEnabled();

    /**
     * tests if error level logging is enabled
     *
     * @return if error level logging is enabled
     */
    public abstract boolean isErrorEnabled();

    /**
     * @param message message
     */
    public abstract void debug(String message);

    /**
     * @param message  message
     * @param message2 message2
     */
    public abstract void debug(String message, String message2);

    /**
     * @param message message
     */
    public abstract void info(String message);

    /**
     * @param message  message
     * @param message2 message2
     */
    public abstract void info(String message, String message2);

    /**
     * @param message message
     */
    public abstract void warn(String message);

    /**
     * @param message  message
     * @param message2 message2
     */
    public abstract void warn(String message, String message2);

    /**
     * @param message message
     */
    public abstract void error(String message);

    /**
     * @param message message
     * @param th      throwable
     */
    public abstract void error(String message, Throwable th);

}
