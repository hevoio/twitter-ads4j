package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;

public class TwitterRange {

    @SerializedName("min")
    private long min;
    @SerializedName("max")
    private long max;

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }
}
