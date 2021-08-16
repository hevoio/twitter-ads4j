package com.hevodata.twitterads4j.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Coordinates implements Serializable {

    @SerializedName("coordinates")
    private List<Double> coordinates;

    @SerializedName("type")
    private String type;
}
