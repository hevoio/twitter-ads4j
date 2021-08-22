package twitter4jads.internal.models4j;

import java.util.Date;

/**
 * A data class representing Trends.
 *
 *
 * @since Twitter4J 2.0.2
 */

public interface Trends extends TwitterResponse, Comparable<Trends>, java.io.Serializable {
    Trend[] getTrends();

    /**
     * Returns the location associated with the trends.<br>
     * This method is effective only with getLocalTrends() method.<br>
     * i.e. The return value of this method will be null with Search API Methods (getTrends(), getCurrentTrends(), getDailyTrends(), and getWeeklyTrends()).<br>
     *
     * @return location
     * @since Twitter4J 2.1.1
     */
    Location getLocation();

    Date getAsOf();

    Date getTrendAt();

}
