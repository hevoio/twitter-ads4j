package twitter4jads.internal.models4j;

/**
 * A data interface representing one single Hashtag entity.
 *
 *
 * @since Twitter4J 2.1.9
 */
public interface HashtagEntity extends java.io.Serializable {
    /**
     * Returns the text of the hashtag without #.
     *
     * @return the text of the hashtag
     */
    String getText();

    /**
     * Returns the index of the start character of the hashtag.
     *
     * @return the index of the start character of the hashtag
     */
    int getStart();

    /**
     * Returns the index of the end character of the hashtag.
     *
     * @return the index of the end character of the hashtag
     */
    int getEnd();
}
