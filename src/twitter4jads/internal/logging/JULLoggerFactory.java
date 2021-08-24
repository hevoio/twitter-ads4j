package twitter4jads.internal.logging;

final class JULLoggerFactory extends LoggerFactory {
    @Override
    public Logger getLogger(Class clazz) {
        return new JULLogger(java.util.logging.Logger.getLogger(clazz.getName()));
    }
}
