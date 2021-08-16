package com.hevodata.twitterads4j.util;

import com.hevodata.twitterads4j.internal.models4j.RateLimitStatusImpl;
import com.hevodata.twitterads4j.internal.http.HttpResponse;

/**
 *
 * Date: 4/4/16
 * Time: 10:53 AM
 */
public class TwitterAdHttpUtils {

    public static RateLimitStatusImpl createFromResponseHeader(HttpResponse res) {

        int remainingHits = 10;//"X-Rate-Limit-Remaining"
        int limit = 10;//"X-Rate-Limit-Limit"
        int resetTimeInSeconds = 0;//not included in the response header. Need to be calculated.

        String strLimit = res.getResponseHeader("X-Rate-Limit-Limit");
        if (strLimit != null) {
            limit = Integer.parseInt(strLimit);
        }

        String remaining = res.getResponseHeader("X-Rate-Limit-Remaining");
        if (remaining != null) {
            remainingHits = Integer.parseInt(remaining);
        }

        String reset = res.getResponseHeader("X-Rate-Limit-Reset");
        if (reset != null) {
            long longReset = Long.parseLong(reset);
            resetTimeInSeconds = (int) longReset;
        }

        return new RateLimitStatusImpl(remainingHits, limit, resetTimeInSeconds);
    }

    public static RateLimitStatusImpl createFromResponseHeaderForCostBasedRateLimit(HttpResponse res) {
        int remainingHits = 10;//"X-Rate-Limit-Remaining"
        int limit = 10;//"X-Rate-Limit-Limit"
        int resetTimeInSeconds = 0;//not included in the response header. Need to be calculated.

        String strLimit = res.getResponseHeader("X-Cost-Rate-Limit-Limit");
        if (strLimit != null) {
            limit = Integer.parseInt(strLimit);
        }

        String remaining = res.getResponseHeader("X-Cost-Rate-Limit-Remaining");
        if (remaining != null) {
            remainingHits = Integer.parseInt(remaining);
        }

        String reset = res.getResponseHeader("X-Cost-Rate-Limit-Reset");
        if (reset != null) {
            long longReset = Long.parseLong(reset);
            resetTimeInSeconds = (int) longReset;
        }

        String costValueStr = res.getResponseHeader("X-Request-Cost");
        int costValue = 0;
        if (costValueStr != null) {
            costValue = Integer.parseInt(costValueStr);
        }

        return new RateLimitStatusImpl(remainingHits, limit, resetTimeInSeconds, costValue);
    }
}
