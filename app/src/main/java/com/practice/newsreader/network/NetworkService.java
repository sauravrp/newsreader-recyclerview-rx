package com.practice.newsreader.network;

import com.google.gson.JsonObject;
import com.practice.newsreader.model.NewsResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by h125673 on 1/29/16.
 */
public interface NetworkService

{
    @GET("/svc/mostpopular/v2/mostemailed/all-sections/1.json?api-key=fa5723452d7d2454cf24a2a3d920012c:10:66680873")
    void getNews(@Query("offset")int offset, Callback<JsonObject> cb);

    /** reference: https://joluet.github.io/blog/2014/07/07/rxjava-retrofit/ */

    @GET("/svc/mostpopular/v2/mostemailed/all-sections/1.json?api-key=fa5723452d7d2454cf24a2a3d920012c:10:66680873")
    Observable<NewsResult> getNews(@Query("offset")int offset);
}
