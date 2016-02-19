package com.practice.newsreader.component;

import com.practice.newsreader.module.NetworkServiceModule;
import com.practice.newsreader.network.NetworkService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by h125673 on 1/29/16.
 */
@Singleton
@Component(modules =  {NetworkServiceModule.class})
public interface NewsReaderComponent
{
    NetworkService providesNetworkService();
}
