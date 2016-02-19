package com.practice.newsreader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by h125673 on 2/5/16.
 */
public class NewsResult implements Serializable
{
    @Expose
    @SerializedName("status")
    public String status;

    @Expose
    @SerializedName("copyright")
    public String copyright;

    @Expose
    @SerializedName("num_results")
    public int num_results;

    @Expose
    @SerializedName("results")
    public ArrayList<Result> results;

}
