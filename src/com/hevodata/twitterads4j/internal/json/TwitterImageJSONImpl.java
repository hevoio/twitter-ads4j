package com.hevodata.twitterads4j.internal.json;

import com.hevodata.twitterads4j.internal.org.json.JSONObject;
import com.hevodata.twitterads4j.internal.models4j.Image;

import static com.hevodata.twitterads4j.internal.json.z_T4JInternalParseUtil.getLong;

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
