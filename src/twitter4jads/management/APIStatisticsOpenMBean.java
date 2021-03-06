package twitter4jads.management;

import javax.management.*;
import javax.management.openmbean.*;
import java.util.Iterator;

/**
 * Dynamic version of APIStatisticsMBean that wraps an APIStatisticsOpenMBean.
 * Provides a tabular view of method stats. This MBean can only run on JDK 1.5+
 *
 *
 */
public class APIStatisticsOpenMBean implements DynamicMBean {
    // metadata
    private static final String[] ITEM_NAMES = {"methodName", "callCount", "errorCount", "totalTime", "avgTime"};
    private static final OpenType[] ITEM_TYPES =
            {SimpleType.STRING, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG};
    private static final String[] ITEM_DESCRIPTIONS =
            {"The method name",
                    "The number of times this method has been called",
                    "The number of calls that failed",
                    "The total amount of time spent invoking this method in milliseconds",
                    "The average amount of time spent invoking this method in milliseconds"};
    private final CompositeType METHOD_STATS_TYPE;

    private final APIStatisticsMBean API_STATISTICS;
    private final TabularType API_STATISTICS_TYPE;

    public APIStatisticsOpenMBean(APIStatistics apiStatistics) {
        API_STATISTICS = apiStatistics;

        try {
            METHOD_STATS_TYPE =
                    new CompositeType("method statistics", "method statistics",
                            ITEM_NAMES, ITEM_DESCRIPTIONS, ITEM_TYPES);

            String[] index = {"methodName"};
            API_STATISTICS_TYPE = new TabularType("API statistics",
                    "list of methods",
                    METHOD_STATS_TYPE,
                    index);
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        OpenMBeanInfoSupport info;
        OpenMBeanAttributeInfoSupport[] attributes = new OpenMBeanAttributeInfoSupport[5];
        OpenMBeanConstructorInfoSupport[] constructors = new OpenMBeanConstructorInfoSupport[1];
        OpenMBeanOperationInfoSupport[] operations = new OpenMBeanOperationInfoSupport[1];
        MBeanNotificationInfo[] notifications = new MBeanNotificationInfo[0];

        int attrIdx = 0;
        attributes[attrIdx++] =
                new OpenMBeanAttributeInfoSupport("callCount",
                        "Total number of API calls",
                        SimpleType.LONG, true, false, false);

        attributes[attrIdx++] =
                new OpenMBeanAttributeInfoSupport("errorCount",
                        "The number of failed API calls",
                        SimpleType.LONG, true, false, false);

        attributes[attrIdx++] =
                new OpenMBeanAttributeInfoSupport("averageTime",
                        "Average time spent invoking any API method",
                        SimpleType.LONG, true, false, false);

        attributes[attrIdx++] =
                new OpenMBeanAttributeInfoSupport("totalTime",
                        "Average time spent invoking any API method",
                        SimpleType.LONG, true, false, false);

        attributes[attrIdx++] =
                new OpenMBeanAttributeInfoSupport("statisticsTable",
                        "Table of statisics for all API methods",
                        API_STATISTICS_TYPE, true, false, false);

        constructors[0] = new OpenMBeanConstructorInfoSupport(
                "APIStatisticsOpenMBean",
                "Constructs an APIStatisticsOpenMBean instance",
                new OpenMBeanParameterInfoSupport[0]);

        OpenMBeanParameterInfo[] params = new OpenMBeanParameterInfoSupport[0];
        operations[0] = new OpenMBeanOperationInfoSupport(
                "reset",
                "reset the statistics",
                params,
                SimpleType.VOID,
                MBeanOperationInfo.INFO);

        info = new OpenMBeanInfoSupport(this.getClass().getName(),
                "API Statistics Open MBean", attributes, constructors,
                operations, notifications);

        return info;
    }

    public synchronized TabularDataSupport getStatistics() {
        TabularDataSupport apiStatisticsTable = new TabularDataSupport(API_STATISTICS_TYPE);

        Iterator<? extends InvocationStatistics> itr = API_STATISTICS.getInvocationStatistics().iterator();
        while (itr.hasNext()) {
            InvocationStatistics methodStats = itr.next();
            Object[] itemValues = {methodStats.getName(),
                    methodStats.getCallCount(),
                    methodStats.getErrorCount(),
                    methodStats.getTotalTime(),
                    methodStats.getAverageTime()};

            try {
                CompositeData result = new CompositeDataSupport(METHOD_STATS_TYPE,
                        ITEM_NAMES,
                        itemValues);
                apiStatisticsTable.put(result);
            } catch (OpenDataException e) {
                throw new RuntimeException(e);
            }

        }

        return apiStatisticsTable;
    }

    public void reset() {
        API_STATISTICS.reset();
    }

    @Override
    public Object getAttribute(String attribute)
            throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (attribute.equals("statisticsTable")) {
            return (TabularData) getStatistics();
        } else if (attribute.equals("callCount")) {
            return API_STATISTICS.getCallCount();
        } else if (attribute.equals("errorCount")) {
            return API_STATISTICS.getErrorCount();
        } else if (attribute.equals("totalTime")) {
            return API_STATISTICS.getTotalTime();
        } else if (attribute.equals("averageTime")) {
            return API_STATISTICS.getAverageTime();
        }
        throw new AttributeNotFoundException("Cannot find " + attribute + " attribute ");
    }

    @Override
    public AttributeList getAttributes(String[] attributeNames) {
        AttributeList resultList = new AttributeList();
        if (attributeNames.length == 0)
            return resultList;
        for (int i = 0; i < attributeNames.length; i++) {
            try {
                Object value = getAttribute(attributeNames[i]);
                resultList.add(new Attribute(attributeNames[i], value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (resultList);
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature)
            throws MBeanException, ReflectionException {
        if (actionName.equals("reset")) {
            reset();
            return "Statistics reset";
        } else {
            throw new ReflectionException(new NoSuchMethodException(
                    actionName), "Cannot find the operation " + actionName);
        }
    }

    @Override
    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException,
            ReflectionException {
        throw new AttributeNotFoundException(
                "No attributes can be set in this MBean");
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return new AttributeList();
    }
}