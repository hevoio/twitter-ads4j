package twitter4jads.internal.models4j;

/**
 *
 * @since Twitter4J 2.1.9
 */
public interface AccountSettings extends TwitterResponse, java.io.Serializable {
    /**
     * Returns true if the user enabled sleep time.
     *
     * @return true if the user enabled sleep time
     */
    boolean isSleepTimeEnabled();

    /**
     * Returns sleep start time.
     *
     * @return sleep start time
     */
    String getSleepStartTime();

    /**
     * Returns sleep end time.
     *
     * @return sleep end time
     */
    String getSleepEndTime();

    /**
     * Return the user's trend locations
     *
     * @return the user's trend locations
     */
    Location[] getTrendLocations();

    /**
     * Return true if the user is enabling geo location
     *
     * @return true if the user is enabling geo location
     */
    boolean isGeoEnabled();

    /**
     * Returns the timezone configured for this user.
     *
     * @return the timezone (formated as a Rails TimeZone name)
     */
    TimeZone getTimeZone();

    /**
     * Returns the language used to render Twitter's UII for this user.
     *
     * @return the language ISO 639-1 representation
     */
    String getLanguage();

    /**
     * Returns true if the user is discoverable by email.
     *
     * @return true if the user is discoverable by email
     */
    boolean isDiscoverableByEmail();

    /**
     * Returns true if the wants to always access twitter using HTTPS.
     *
     * @return true if the wants to always access twitter using HTTPS
     */
    boolean isAlwaysUseHttps();
}
