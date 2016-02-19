package com.practice.newsreader.fragments.common;

import android.app.Fragment;
import android.os.Bundle;

import com.practice.newsreader.activities.common.BaseActivity;

/**
 * Created by h125673 on 1/28/16.
 */
public class BaseFragment extends Fragment
{
    BaseActivity mBaseActivity;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() != null && getActivity() instanceof BaseActivity)
        {
            mBaseActivity = (BaseActivity) getActivity();
        }
    }

    @Override
    public void onDetach()
    {
        mBaseActivity = null;
        super.onDetach();
    }
}
