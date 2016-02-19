package com.practice.newsreader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by h125673 on 2/5/16.
 */
public class Media implements Serializable
{
    @Expose
    @SerializedName("media-metadata")
    public List<MediaMetaData> metaDataArray;
}
