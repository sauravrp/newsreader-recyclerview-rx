package com.practice.newsreader.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.practice.newsreader.R;
import com.practice.newsreader.fragments.common.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by h125673 on 2/18/16.
 */
public class ArticleFragment extends BaseFragment
{
    public final static String URL = "ArticleFragment.URL";

    @Bind(R.id.webview)
    WebView mWebView;

    String mUrl;

    public static ArticleFragment newInstance(String url)
    {
        ArticleFragment fragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mUrl = getArguments().getString(URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient());

    }
}
