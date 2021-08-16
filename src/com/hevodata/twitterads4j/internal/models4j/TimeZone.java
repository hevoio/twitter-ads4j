package com.hevodata.twitterads4j.internal.models4j;

/**
 *  Alessandro Bahgat - ale.bahgat at gmail.com
 */
public interface TimeZone extends java.io.Serializable {
    String getName();

    String tzinfoName();

    int utcOffset();
}