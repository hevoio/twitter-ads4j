package twitter4jads.internal.models4j;

import java.util.Date;

/**
 * A data interface representing a Saved Search
 *
 *
 * @since Twitter4J 2.0.8
 */
public interface SavedSearch extends Comparable<SavedSearch>, TwitterResponse, java.io.Serializable {
    Date getCreatedAt();

    String getQuery();

    int getPosition();

    String getName();

    int getId();

}