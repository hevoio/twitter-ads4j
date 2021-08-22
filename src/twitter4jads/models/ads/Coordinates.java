package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Coordinates implements Serializable {

    @SerializedName("coordinates")
    private List<Double> coordinates;

    @SerializedName("type")
    private String type;
}
