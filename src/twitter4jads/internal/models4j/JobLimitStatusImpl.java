package twitter4jads.internal.models4j;

public class JobLimitStatusImpl implements JobLimitStatus {

    private final int jobLimit;
    private final int jobLimitRemaining;

    public JobLimitStatusImpl(int jobLimit, int jobLimitRemaining) {
        this.jobLimit = jobLimit;
        this.jobLimitRemaining = jobLimitRemaining;
    }

    @Override
    public int getJobLimit() {
        return this.jobLimit;
    }

    @Override
    public int getJobLimitRemaining() {
        return this.jobLimitRemaining;
    }

    @Override
    public String toString() {
        return "JobLimitStatusImpl {" +
                "remaining=" + this.jobLimitRemaining +
                ", limit=" + this.jobLimit +
                "}";
    }

}