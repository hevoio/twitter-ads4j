package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HashtagEntity implements Serializable {

    @SerializedName("indices")
    private List<Integer> indices;

    @SerializedName("text")
    private String text;
}
