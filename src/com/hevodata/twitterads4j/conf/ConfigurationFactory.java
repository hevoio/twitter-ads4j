package com.hevodata.twitterads4j.conf;

/**
 *
 */
public interface ConfigurationFactory {
    /**
     * returns the root configuration
     *
     * @return root configuration
     */
    Configuration getInstance();

    /**
     * returns the configuration specified by the path
     *
     * @param configTreePath the path
     * @return the configuratoin
     */
    Configuration getInstance(String configTreePath);

    /**
     * clean up resources acquired by this factory.
     */
    void dispose();
}
