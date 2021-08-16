package com.hevodata.twitterads4j;

import com.google.gson.annotations.SerializedName;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.hevodata.twitterads4j.internal.models4j.RateLimitStatus;
import com.hevodata.twitterads4j.internal.models4j.TwitterResponse;
import com.hevodata.twitterads4j.models.ads.RequestParameters;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseAdsListBatchPostResponse<T> implements Serializable, TwitterResponse {

    @SerializedName("data_type")
    private String dataType;

    @SerializedName("data")
    private List<T> data;

    @SerializedName("total_count")
    private Long totalCount;

    @SerializedName("request")
    private List<RequestParameters> request;

    @SerializedName("next_cursor")
    private String nextCursor;

    @SerializedName("cursor")
    private String cursor;

    @SerializedName("count")
    private Long count;

    private RateLimitStatus rateLimitStatus;

    private int accessLevel;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        if (data == null) {
            return Collections.emptyList();
        }
        return data;

    }

    void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<RequestParameters> getRequest() {
        return request;
    }

    public void setRequest(List<RequestParameters> request) {
        this.request = request;
    }

    public String getNextCursor() {
        if (nextCursor != null) {
            return nextCursor;
        } else if (cursor != null){
            return cursor;
        }
        return null;
    }

    void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        return rateLimitStatus;
    }

    public void setRateLimitStatus(RateLimitStatus rateLimitStatus) {
        this.rateLimitStatus = rateLimitStatus;
    }

    @Override
    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
