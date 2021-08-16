package com.hevodata.twitterads4j.models.ads.tags;

import com.google.gson.annotations.SerializedName;
import com.hevodata.twitterads4j.models.ads.TwitterApplicationDetails;
import com.hevodata.twitterads4j.models.ads.TwitterEntity;

import java.util.List;

/**
 *
 * Date:  08/09/15.
 */
public class TwitterApplicationList extends TwitterEntity {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("apps")
    private List<TwitterApplicationDetails> apps;

    public TwitterApplicationList(String id, String name, List<TwitterApplicationDetails> apps) {
        this.id = id;
        this.name = name;
        this.apps = apps;
    }

    public TwitterApplicationList(){

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TwitterApplicationDetails> getApps() {
        return apps;
    }

    public void setApps(List<TwitterApplicationDetails> apps) {
        this.apps = apps;
    }
}
