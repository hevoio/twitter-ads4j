package twitter4jads.internal.models4j;

import twitter4jads.internal.http.HttpParameter;

import java.util.ArrayList;
import java.util.List;

public class VideoTweetRequest {
    private String text;
    private String imageId;
    private String videoId;
    private boolean nullCast = false;

    public HttpParameter[] asHttpParamArray(){
        List<HttpParameter> parameterList = new ArrayList<>();
        parameterList.add(new HttpParameter("tweet_text",text));
        parameterList.add(new HttpParameter("video_id",videoId));
        parameterList.add(new HttpParameter("poster_image_id",imageId));
        parameterList.add(new HttpParameter("nullcast",String.valueOf(nullCast)));
        return parameterList.toArray(new HttpParameter[parameterList.size()]);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public boolean isNullCast() {
        return nullCast;
    }

    public void setNullCast(boolean nullCast) {
        this.nullCast = nullCast;
    }
}
