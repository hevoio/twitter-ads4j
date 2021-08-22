package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;

public class TwitterMetricsBySegmentation {

    public static final String SEGMENT = "segment";
    public static final String METRICS = "metrics";

    @SerializedName(SEGMENT)
    private NewSegment segment;

    @SerializedName(METRICS)
    private TwitterAdStatistics metrics;

    public NewSegment getSegment() {
        return segment;
    }

    public void setSegment(NewSegment segment) {
        this.segment = segment;
    }

    public TwitterAdStatistics getMetrics() {
        return metrics;
    }

    public void setMetrics(TwitterAdStatistics metrics) {
        this.metrics = metrics;
    }
}
