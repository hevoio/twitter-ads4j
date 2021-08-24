package twitter4jads.internal.models4j;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @since Twitter4J 2.2.3
 */
public interface TwitterAPIConfiguration extends TwitterResponse, Serializable {
    int getPhotoSizeLimit();

    int getShortURLLength();

    int getShortURLLengthHttps();

    int getCharactersReservedPerMedia();

    Map<Integer, MediaEntity.Size> getPhotoSizes();

    String[] getNonUsernamePaths();

    int getMaxMediaPerUpload();
}
