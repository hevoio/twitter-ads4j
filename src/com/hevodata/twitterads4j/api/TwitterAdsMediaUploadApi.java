package com.hevodata.twitterads4j.api;

import com.hevodata.twitterads4j.internal.models4j.TwitterException;
import com.hevodata.twitterads4j.models.media.TwitterMediaType;

import java.util.Set;

/**
 *
 * Date: 4/5/16
 * Time: 10:40 AM
 */
public interface TwitterAdsMediaUploadApi {

    String uploadMediaAndGetMediaKey(String mediaUrl, Set<String> accountUserIds, TwitterMediaType twitterMediaType, String name)
            throws TwitterException;

}
