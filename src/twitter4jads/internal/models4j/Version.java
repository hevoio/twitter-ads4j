package twitter4jads.internal.models4j;

import static twitter4jads.TwitterAdsConstants.CURRENT_VERSION;

public final class Version {
    private static final String VERSION = CURRENT_VERSION;
    private static final String TITLE = "Twitter4JAds";

    private Version() {
        throw new AssertionError();
    }

    public static String getVersion() {
        return VERSION;
    }

    /**
     * prints the version string
     *
     * @param args will be just ignored.
     */
    public static void main(String[] args) {
        System.out.println(TITLE + " " + VERSION);
    }
}
