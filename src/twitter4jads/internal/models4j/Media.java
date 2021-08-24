package twitter4jads.internal.models4j;

/**
 * Created with IntelliJ IDEA.
 *
 * Date: 08/05/14
 * Time: 7:45 PM
 */
public interface Media {
    Long getMediaId();

    String getMediaIdString();

    Long getSize();

    Image getImage();

    String getState();

    boolean isStatePending();

    boolean isStateSucceeded();

    boolean isStateInProgress();

}
