package com.hevodata.twitterads4j.conf;

/**
 * ConfigurationFactory implementation for PropertyConfiguration.
 * Currently getInstance calls concrete constructor each time. No caching at all.
 *
 *
 */
class PropertyConfigurationFactory implements ConfigurationFactory {
    private static final PropertyConfiguration ROOT_CONFIGURATION;

    static {
        ROOT_CONFIGURATION = new PropertyConfiguration();
        // calling ROOT_CONFIGURATION.dumpConfiguration() will cause ExceptionInInitializerError as Logger has not been initialized.
        // as a quick and dirty solution, static initializer of twitter4jads.internal.logging.Logger will call dumpConfiguration() on behalf.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration getInstance() {
        return ROOT_CONFIGURATION;
    }

    // It may be preferable to cache the config instance

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration getInstance(String configTreePath) {
        PropertyConfiguration conf = new PropertyConfiguration(configTreePath);
        conf.dumpConfiguration();
        return conf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        // nothing to do for property based configuration
    }
}
