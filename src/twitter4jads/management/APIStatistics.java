package twitter4jads.management;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Container for all InvocationStatisticsCalculators in a given API (like Twitter)
 *
 *
 */
public class APIStatistics implements APIStatisticsMBean {
    private final InvocationStatisticsCalculator API_STATS_CALCULATOR;
    private final Map<String, InvocationStatisticsCalculator> METHOD_STATS_MAP;
    private final int HISTORY_SIZE;

    /**
     * @param historySize the number of calls to track (for invocation time averaging)
     */
    public APIStatistics(int historySize) {
        API_STATS_CALCULATOR = new InvocationStatisticsCalculator("API", historySize);
        METHOD_STATS_MAP = new HashMap<String, InvocationStatisticsCalculator>(100);
        HISTORY_SIZE = historySize;
    }

    /**
     * @param method the method invoked
     * @param time   the method execution time
     */
    public synchronized void methodCalled(String method, long time, boolean success) {
        getMethodStatistics(method).increment(time, success);

        // increment for entire API
        API_STATS_CALCULATOR.increment(time, success);
    }

    private synchronized InvocationStatisticsCalculator getMethodStatistics(String method) {
        InvocationStatisticsCalculator methodStats = METHOD_STATS_MAP.get(method);

        if (methodStats == null) {
            methodStats = new InvocationStatisticsCalculator(method, HISTORY_SIZE);
            METHOD_STATS_MAP.put(method, methodStats);
        }
        return methodStats;
    }

    @Override
    public synchronized Iterable<? extends InvocationStatistics> getInvocationStatistics() {
        return METHOD_STATS_MAP.values();
    }

    public synchronized void reset() {
        API_STATS_CALCULATOR.reset();
        METHOD_STATS_MAP.clear();
    }

    /**
     * APIStatisticsMBean implementation
     */

    public String getName() {
        return API_STATS_CALCULATOR.getName();
    }

    public long getCallCount() {
        return API_STATS_CALCULATOR.getCallCount();
    }

    public long getErrorCount() {
        return API_STATS_CALCULATOR.getErrorCount();
    }

    public long getTotalTime() {
        return API_STATS_CALCULATOR.getTotalTime();
    }

    public long getAverageTime() {
        return API_STATS_CALCULATOR.getAverageTime();
    }

    @Override
    public synchronized Map<String, String> getMethodLevelSummariesAsString() {
        Map<String, String> summariesMap = new HashMap<String, String>();

        Collection<InvocationStatisticsCalculator> allMethodStats = METHOD_STATS_MAP.values();
        for (InvocationStatisticsCalculator methodStats : allMethodStats) {
            summariesMap.put(methodStats.getName(), methodStats.toString());
        }

        return summariesMap;
    }

    @Override
    public synchronized String getMethodLevelSummary(String methodName) {
        return METHOD_STATS_MAP.get(methodName).toString();
    }
}