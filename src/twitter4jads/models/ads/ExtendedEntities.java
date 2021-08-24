package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExtendedEntities implements Serializable {

    @SerializedName("media")
    private List<MediaEntity> media;
}
