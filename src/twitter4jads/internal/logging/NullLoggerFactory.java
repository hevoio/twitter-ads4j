package twitter4jads.internal.logging;

final class NullLoggerFactory extends LoggerFactory {
    private static final Logger SINGLETON = new NullLogger();

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class clazz) {
        return SINGLETON;
    }
}