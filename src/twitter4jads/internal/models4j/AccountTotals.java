package twitter4jads.internal.models4j;

/**
 *
 * @since Twitter4J 2.1.9
 */
public interface AccountTotals extends TwitterResponse, java.io.Serializable {
    /**
     * Returns the number of total updates.
     *
     * @return the number of total updates
     */
    int getUpdates();

    /**
     * Returns the number of total followers.
     *
     * @return the number of total followers
     */
    int getFollowers();

    /**
     * Returns the number of total favorites.
     *
     * @return the number of total favorites
     */
    int getFavorites();

    /**
     * Returns the number of total friends.
     *
     * @return the number of total friends
     */
    int getFriends();
}
