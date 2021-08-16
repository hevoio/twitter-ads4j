package com.hevodata.twitterads4j.internal.models4j;


/**
 * A data class representing Status deletionNotice<br>
 * Clients are urged to honor deletionNotice requests and discard deleted statuses immediately. At times, status deletionNotice messages may arrive before the status. Even in this case, the late arriving status should be deleted from your backing store.
 *
 *
 * @since Twitter4J 2.1.0
 */
public interface StatusDeletionNotice extends Comparable<StatusDeletionNotice>, java.io.Serializable {
    long getStatusId();

    long getUserId();
}
