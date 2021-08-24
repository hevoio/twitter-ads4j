package twitter4jads.internal.models4j;

import java.util.List;
import java.util.Map;

public interface MediaEntity extends URLEntity {
    /**
     * Returns the id of the media.
     *
     * @return the id of the media
     */
    long getId();

    /**
     * Returns the media URL.
     *
     * @return the media URL
     */
    String getMediaURL();

    /**
     * Returns the media secure URL.
     *
     * @return the media secure URL
     */
    String getMediaURLHttps();

    /**
     * Returns size variations of the media.
     *
     * @return size variations of the media
     */
    Map<Integer, Size> getSizes();

    interface Size extends java.io.Serializable {
        Integer THUMB = 0;
        Integer SMALL = 1;
        Integer MEDIUM = 2;
        Integer LARGE = 3;
        int FIT = 100;
        int CROP = 101;

        int getWidth();

        int getHeight();

        int getResize();
    }

    /**
     * Returns the media type ("photo").
     *
     * @return the media type ("photo").
     */
    String getType();

    VideoInfo getVideoInfo();

    interface VideoInfo extends java.io.Serializable {
        List<Variant> getVariants();

        List<Integer> getAspectRatio();

        Long getMillis();
    }

    interface Variant extends java.io.Serializable {

        String getContentType();

        String getUrl();

        Long getbitrate();

    }
}
