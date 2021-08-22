package twitter4jads.internal.logging;

final class StdOutLoggerFactory extends LoggerFactory {
    private static final Logger SINGLETON = new StdOutLogger();

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class clazz) {
        return SINGLETON;
    }
}
