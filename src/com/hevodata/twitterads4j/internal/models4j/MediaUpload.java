package com.hevodata.twitterads4j.internal.models4j;

import com.hevodata.twitterads4j.internal.http.HttpParameter;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * Date: 08/05/14
 * Time: 4:35 PM
 */
public class MediaUpload {

    private InputStream imageInputStream;

    public MediaUpload(InputStream imageInputStream) {
        this.imageInputStream = imageInputStream;
    }

    HttpParameter[] asHttpParameterArray() {
        ArrayList<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("media", "", imageInputStream));
        HttpParameter[] paramArray = new HttpParameter[params.size()];
        return params.toArray(paramArray);
    }


    public InputStream getImageInputStream() {
        return imageInputStream;
    }
}
