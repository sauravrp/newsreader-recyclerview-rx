package com.practice.newsreader.module;

import com.practice.newsreader.network.LiveNetworkService;
import com.practice.newsreader.network.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by h125673 on 1/29/16.
 * http://code.tutsplus.com/tutorials/dependency-injection-with-dagger-2-on-android--cms-23345
 */


@Module
public class NetworkServiceModule
{
    public final static String URL = "http://api.nytimes.com";

    @Provides @Singleton
    NetworkService providesNetworkService()
    {
        return new LiveNetworkService(URL).getService();
    }



}
