package twitter4jads.internal.models4j;

public interface CursorSupport {
    /**
     * @since Twitter4J 2.2.3
     */
    long START = -1L;

    /**
     * @since Twitter4J 2.0.10
     */
    boolean hasPrevious();

    /**
     * @since Twitter4J 2.0.10
     */
    long getPreviousCursor();

    /**
     * @since Twitter4J 2.0.10
     */
    boolean hasNext();

    /**
     * @since Twitter4J 2.0.10
     */
    long getNextCursor();
}
