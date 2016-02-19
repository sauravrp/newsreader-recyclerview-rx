package com.practice.newsreader.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.practice.newsreader.R;
import com.practice.newsreader.activities.ArticleActivity;
import com.practice.newsreader.component.DaggerNewsReaderComponent;
import com.practice.newsreader.component.NewsReaderComponent;
import com.practice.newsreader.fragments.common.BaseFragment;
import com.practice.newsreader.model.NewsResult;
import com.practice.newsreader.model.Result;
import com.practice.newsreader.module.NetworkServiceModule;
import com.practice.newsreader.network.NetworkService;
import com.practice.newsreader.ui.EndlessRecyclerViewScrollListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by h125673 on 1/28/16.
 */
public class MainFragment extends BaseFragment
{
    private final static String ARTICLES = "MainFragment.Articles";
     private final static String LIST_VIEW_STATE = "MainFragment.ListViewState";
    private final static String SCROLL_STATE =  "MainFragment.ScrollState";

    private final static String TAG = MainFragment.class.getCanonicalName();
    private NetworkService mNetworkService;

    @Bind(R.id.news_list_view)
    RecyclerView mListView;

    private LinearLayoutManager mLinearLayoutManager;
    private EndlessRecyclerViewScrollListener mEndlessRecyclerViewScrollListener;

    private NewsAdapter mAdapter;
    private int PAGE_COUNT = 20;

    private ArrayList<Result> mArticles = new ArrayList<Result>();

    private View.OnClickListener mOnArticleClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int pos = mListView.getChildAdapterPosition(v);
            Result result = mArticles.get(pos);
            ArticleActivity.startActivity(getActivity(), result.url);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
        {
            mArticles = (ArrayList<Result>) savedInstanceState.getSerializable(ARTICLES);
        }

        NewsReaderComponent component =
                DaggerNewsReaderComponent.builder().networkServiceModule(new NetworkServiceModule()).build();

        mNetworkService = component.providesNetworkService();

        mAdapter = new NewsAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(mLinearLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mListView.setAdapter(mAdapter);

        mEndlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(mLinearLayoutManager)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                fetchNews(page);
            }
        };

        mListView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
            {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e)
            {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
            {

            }
        });


        if(savedInstanceState != null)
        {
            Parcelable listState = savedInstanceState.getParcelable(LIST_VIEW_STATE);
            mLinearLayoutManager.onRestoreInstanceState(listState);

            EndlessRecyclerViewScrollListener.EndlessRecyclerViewScrollListenerState scrollState = (EndlessRecyclerViewScrollListener.EndlessRecyclerViewScrollListenerState) savedInstanceState.getSerializable(SCROLL_STATE);
            mEndlessRecyclerViewScrollListener.setState(scrollState);
        }
        else
        {
            fetchNews(0);

        }


        // https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews-and-RecyclerView
        mListView.addOnScrollListener(mEndlessRecyclerViewScrollListener);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARTICLES, mArticles);
        outState.putParcelable(LIST_VIEW_STATE, mLinearLayoutManager.onSaveInstanceState());
        outState.putSerializable(SCROLL_STATE, mEndlessRecyclerViewScrollListener.getState());
    }

    public void fetchNews(int page)
    {
        mNetworkService.getNews(page * PAGE_COUNT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.d(TAG, e.toString());
                    }

                    @Override
                    public void onNext(NewsResult result)
                    {
                        mArticles.addAll(result.results);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    protected class NewsHolder extends RecyclerView.ViewHolder
    {

        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.content)
        TextView mContent;
        @Bind(R.id.imageview)
        ImageView mImageView;

        public NewsHolder(View itemView)
        {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }


    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder>
    {
        public NewsAdapter()
        {
        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            view.setOnClickListener(mOnArticleClickListener);
            return new NewsHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsHolder holder, int position)
        {
            if (mArticles != null)
            {
                Result result = mArticles.get(position);

                if (result != null)
                {
                    holder.mTitle.setText(result.title);
                     holder.mContent.setText(result.mAbstract);
                    if (result.mediaList != null && result.mediaList.size() > 0 && result.mediaList.get(0).metaDataArray != null)
                    {
                        Picasso.with(getActivity()).load(result.mediaList.get(0).metaDataArray.get(0).url).into(holder.mImageView);
                    }
                    else
                    {
                       holder.mImageView.setImageDrawable(getResources().getDrawable(android.R.drawable.presence_offline));
                    }
                }
            }
        }

        @Override
        public int getItemCount()
        {
            if (mArticles == null)
            {
                return 0;
            }
            else
            {
                return mArticles.size();
            }
        }
    }
}
