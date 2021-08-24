package twitter4jads.internal.models4j;

/**
 * ResponseList with cursor support.
 *
 *
 */
public interface PagableResponseList<T extends TwitterResponse> extends ResponseList<T>, CursorSupport {
    boolean hasPrevious();

    long getPreviousCursor();

    boolean hasNext();

    long getNextCursor();

}
