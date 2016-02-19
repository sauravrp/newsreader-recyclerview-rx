package com.practice.newsreader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by h125673 on 2/5/16.
 */
public class MediaMetaData implements Serializable
{
    @Expose
    @SerializedName("url")
    public String url;

    @Expose
    @SerializedName("height")
    public int height;

    @Expose
    @SerializedName("width")
    public int width;
}
