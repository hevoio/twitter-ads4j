package twitter4jads.api;

import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.media.TwitterMediaType;

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
