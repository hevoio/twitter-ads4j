package com.hevodata.twitterads4j.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SymbolEntity implements Serializable {

    @SerializedName("indices")
    private List<Integer> indices;

    @SerializedName("text")
    private String text;
}
