package twitter4jads.internal.logging;

final class SLF4JLoggerFactory extends LoggerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class clazz) {
        return new SLF4JLogger(org.slf4j.LoggerFactory.getLogger(clazz));
    }
}
