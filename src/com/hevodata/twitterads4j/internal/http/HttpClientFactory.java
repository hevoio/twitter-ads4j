package com.hevodata.twitterads4j.internal.http;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class HttpClientFactory {
    private static final Constructor HTTP_CLIENT_CONSTRUCTOR;
    private static final String HTTP_CLIENT_IMPLEMENTATION = "twitter4jads.http.httpClient";

    static {
        Class clazz = null;
        //-Dtwitter4jads.http.httpClient=twitter4jads.internal.http.HttpClient
        String httpClientImpl = System.getProperty(HTTP_CLIENT_IMPLEMENTATION);
        if (httpClientImpl != null) {
            try {
                clazz = Class.forName(httpClientImpl);
            } catch (ClassNotFoundException ignore) {
            }
        }
        if (null == clazz) {
            try {
                clazz = Class.forName("twitter4jads.internal.http.alternative.HttpClientImpl");
            } catch (ClassNotFoundException ignore) {
            }
        }
        if (null == clazz) {
            try {
                clazz = Class.forName("com.hevodata.twitterads4j.internal.http.HttpClientImpl");
            } catch (ClassNotFoundException cnfe) {
                throw new AssertionError(cnfe);
            }
        }
        try {
            HTTP_CLIENT_CONSTRUCTOR = clazz.getConstructor(HttpClientConfiguration.class);
        } catch (NoSuchMethodException nsme) {
            throw new AssertionError(nsme);
        }
    }

    public static HttpClient getInstance(HttpClientConfiguration conf) {
        try {
            return (HttpClient) HTTP_CLIENT_CONSTRUCTOR.newInstance(conf);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }
}
