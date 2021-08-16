package com.hevodata.twitterads4j;

import com.google.gson.annotations.SerializedName;
import com.hevodata.twitterads4j.internal.models4j.RateLimitStatus;
import com.hevodata.twitterads4j.internal.models4j.TwitterResponse;
import com.hevodata.twitterads4j.models.ads.RequestParameters;

import java.io.Serializable;


/**
 *
 * Date: 02/02/14
 * Time: 11:56 AM
 */
public class BaseAdsResponse<T> implements Serializable, TwitterResponse {

    @SerializedName("data")
    private T data;

    @SerializedName("request")
    private RequestParameters request;

    private RateLimitStatus rateLimitStatus;

    private int accessLevel = NONE;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RequestParameters getRequest() {
        return request;
    }

    public void setRequest(RequestParameters request) {
        this.request = request;
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
