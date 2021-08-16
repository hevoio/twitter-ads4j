package com.hevodata.twitterads4j.models.ads;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hevodata.twitterads4j.models.MetricGroup;
import com.hevodata.twitterads4j.models.TwitterSegmentationType;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TwitterEntityStatisticsMetrics {
    public static final Set<String> ALL_METRIC_GROUPS;
    public static final Map<TwitterAdObjective, Set<String>> METRIC_GROUPS_BY_OBJECTIVE;
    public static final Set<String> WEB_CONVERSION_METRIC_GROUP = Collections.unmodifiableSet(Sets.newHashSet(MetricGroup.WEB_CONVERSION.name()));
    public static final Set<MetricGroup> ORGANIC_METRIC_GROUPS = Sets.newHashSet(MetricGroup.ENGAGEMENT, MetricGroup.VIDEO);
    public static final Set<MetricGroup> ENGAGEMENT_METRIC_GROUPS = Sets.newHashSet(MetricGroup.ENGAGEMENT);

    static {
        Set<String> metricGroups = Sets.newHashSet();
        for (MetricGroup metricGroup : MetricGroup.values()) {
            metricGroups.add(metricGroup.name());
        }

        ALL_METRIC_GROUPS = Collections.unmodifiableSet(metricGroups);

        Map<TwitterAdObjective, Set<String>> metricsGroupsByObjective = Maps.newHashMap();
        for (TwitterAdObjective adObjective : TwitterAdObjective.values()) {
            Set<String> metricGroupNames = Sets.newHashSet();
            for (MetricGroup metricGroup : adObjective.getMetricGroups()) {
                metricGroupNames.add(metricGroup.name());
            }
            metricsGroupsByObjective.put(adObjective, metricGroupNames);
        }
        METRIC_GROUPS_BY_OBJECTIVE = Collections.unmodifiableMap(metricsGroupsByObjective);
    }

    public static Set<String> getMetricGroups(TwitterSegmentationType twitterSegmentationType) {
        if (twitterSegmentationType != null && TwitterSegmentationType.CONVERSION_TAGS == twitterSegmentationType) {
            return WEB_CONVERSION_METRIC_GROUP;
        }

        return ALL_METRIC_GROUPS;
    }

    /*
    Later, when we have MEDIA_CREATIVE Data
     */
    public static Set<String> getMetricGroups() {
        return ALL_METRIC_GROUPS;
    }

}
