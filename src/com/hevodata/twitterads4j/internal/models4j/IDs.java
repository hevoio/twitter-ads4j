package com.hevodata.twitterads4j.internal.models4j;


/**
 * A data interface representing array of numeric IDs.
 *
 *
 */
public interface IDs extends TwitterResponse, CursorSupport, java.io.Serializable {
    long[] getIDs();

    boolean hasPrevious();

    long getPreviousCursor();

    boolean hasNext();

    long getNextCursor();
}
