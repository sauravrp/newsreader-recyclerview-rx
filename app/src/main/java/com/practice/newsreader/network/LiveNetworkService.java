package com.practice.newsreader.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.newsreader.model.Media;
import com.practice.newsreader.model.MediaList;
import com.practice.newsreader.model.json.MediaListJsonConverter;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by h125673 on 1/29/16.
 */
public class LiveNetworkService
{
    private final static String TAG = LiveNetworkService.class.getCanonicalName();
    private String mUrl;
    NetworkService mService;

    private static RestAdapter.Log mAdapterLog = new RestAdapter.Log()
    {
        @Override
        public void log(String message)
        {
            Log.d(TAG, message);
        }
    };

    public LiveNetworkService(String url)
    {
        mUrl = url;

        Gson gson = new GsonBuilder().registerTypeAdapter(MediaList.class, new MediaListJsonConverter())
            .setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

        RestAdapter retrofit = new RestAdapter.Builder()
                .setLog(mAdapterLog)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(mUrl)
                .setConverter(new GsonConverter(gson))
                .build();



        mService = retrofit.create(NetworkService.class);
    }

    public NetworkService getService()
    {
        return mService;
    }

    public String getUrl()
    {
        return mUrl;
    }
}
