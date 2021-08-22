package twitter4jads.internal.models4j;

public final class Version {
    private static final String VERSION = "9.0";
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
