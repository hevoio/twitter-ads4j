package twitter4jads.internal.async;

import twitter4jads.conf.Configuration;
import twitter4jads.conf.ConfigurationContext;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @since Twitter4J 2.1.2
 */
public final class DispatcherFactory {
    private String dispatcherImpl;
    private Configuration conf;

    public DispatcherFactory(Configuration conf) {
        dispatcherImpl = conf.getDispatcherImpl();
        this.conf = conf;
    }

    public DispatcherFactory() {
        this(ConfigurationContext.getInstance());
    }

    /**
     * returns a Dispatcher instance.
     *
     * @return dispatcher instance
     */
    public Dispatcher getInstance() {
        try {
            return (Dispatcher) Class.forName(dispatcherImpl)
                    .getConstructor(Configuration.class).newInstance(conf);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        } catch (ClassCastException e) {
            throw new AssertionError(e);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }
}
