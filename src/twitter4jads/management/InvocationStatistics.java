package twitter4jads.management;

/**
 *
 */
public interface InvocationStatistics {
    public String getName();

    public long getCallCount();

    public long getErrorCount();

    public long getTotalTime();

    public long getAverageTime();

    public void reset();
}
