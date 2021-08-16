package com.hevodata.twitterads4j.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * Date: 29/01/14
 * Time: 11:51 AM
 */
public class TwitterEntity implements Serializable {

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TwitterEntity{" +
               "id='" + id + '\'' +
               '}';
    }
}
