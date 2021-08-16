package com.hevodata.twitterads4j.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExtendedEntities implements Serializable {

    @SerializedName("media")
    private List<MediaEntity> media;
}
