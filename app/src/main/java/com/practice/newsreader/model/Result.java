package com.practice.newsreader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by h125673 on 2/5/16.
 */
public class Result implements Serializable
{
    @Expose
    @SerializedName("url")
    public String url;

    @Expose
    @SerializedName("title")
    public String title;

    @Expose
    @SerializedName("abstract")
    public String mAbstract;

    @Expose
    @SerializedName("media")
    public MediaList mediaList;
}
