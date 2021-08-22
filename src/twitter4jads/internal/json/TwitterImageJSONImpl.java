package twitter4jads.internal.json;

import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.models4j.Image;

import static twitter4jads.internal.json.z_T4JInternalParseUtil.getLong;

/**
 * Created with IntelliJ IDEA.
 *
 * Date: 08/05/14
 * Time: 7:49 PM
 */
public class TwitterImageJSONImpl implements Image {
    private Long width;
    private Long height;
    private String imageType;

    public TwitterImageJSONImpl(JSONObject json) {
        width =  z_T4JInternalParseUtil.getLong("w",json);
        height = z_T4JInternalParseUtil.getLong("h",json);
        imageType = z_T4JInternalParseUtil.getRawString("image_type",json);
    }

    @Override
    public Long width() {
        return width;
    }

    @Override
    public Long height() {
        return height;
    }

    @Override
    public String imageType() {
        return imageType;
    }
}
