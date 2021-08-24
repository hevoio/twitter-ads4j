package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;



public class BiddingRules {

    @SerializedName("minimum_cpe_bid_local_micro")
    private long minimumCpeBidMicro;

    @SerializedName("currency")
    private String currency;

    @SerializedName("maximum_cpe_bid_local_micro")
    private long maximumCpeBidMicro;

    @SerializedName("minimum_denomination")
    private long minimumDenomination;

    public long getMaximumCpeBidMicro() {
        return maximumCpeBidMicro;
    }

    public void setMaximumCpeBidMicro(long maximumCpeBidMicro) {
        this.maximumCpeBidMicro = maximumCpeBidMicro;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getMinimumCpeBidMicro() {
        return minimumCpeBidMicro;
    }

    public void setMinimumCpeBidMicro(long minimumCpeBidMicro) {
        this.minimumCpeBidMicro = minimumCpeBidMicro;
    }

    public long getMinimumDenomination() {
        return minimumDenomination;
    }

    public void setMinimumDenomination(long minimumDenomination) {
        this.minimumDenomination = minimumDenomination;
    }
}
