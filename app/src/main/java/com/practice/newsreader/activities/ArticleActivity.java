package com.practice.newsreader.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

import com.practice.newsreader.activities.common.BaseActivity;
import com.practice.newsreader.fragments.ArticleFragment;

/**
 * Created by h125673 on 2/18/16.
 */
public class ArticleActivity extends BaseActivity
{
    public static final String URL = "URL";

    public static void startActivity(Context ctx, String url)
    {
        Intent intent = new Intent(ctx, ArticleActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra(URL, url);
        ctx.startActivity(intent);

    }
    @Override
    protected Fragment createFragment()
    {
        return ArticleFragment.newInstance(getIntent().getStringExtra(URL));
    }
}
