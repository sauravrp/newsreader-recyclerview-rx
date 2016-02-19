package com.practice.newsreader.activities.common;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.practice.newsreader.R;

/**
 * Created by h125673 on 1/28/16.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null)
        {
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer, createFragment()).commit();
        }

    }


    protected abstract Fragment createFragment();
}
