
package com.hevodata.twitterads4j.internal.async;

/**
 *
 */
public interface Dispatcher {

    void invokeLater(Runnable task);

    void shutdown();
}
