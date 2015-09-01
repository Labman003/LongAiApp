package com.ouzhouren.longai.view.news;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.News;
import com.ouzhouren.longai.view.news.activity.NewsDetails;
import com.ouzhouren.longai.view.news.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    public static List<News> newsLists = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newsView = inflater.inflate(R.layout.fragment_news, container, false);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) newsView.findViewById(R.id.news_swipe_container);
        RecyclerView recyclerView = (RecyclerView) newsView.findViewById(R.id.news_rv);

        //添加布局
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i <= 2; i++) {
            News news = new News();
            news.setAuthor("作者" + i);
            news.setTitle("标题" + i);
            newsLists.add(news);
        }
        final NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsLists);

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent news_intent = new Intent(getActivity(), NewsDetails.class);
                news_intent.putExtra("position", position);
                startActivity(news_intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        //添加适配器
        recyclerView.setAdapter(newsAdapter);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        News news = new News();
                        int i = newsLists.size();
                        news.setAuthor("作者" + i);
                        news.setTitle("标题" + i);
                        //     news.setImgUrl("http://img3.douban.com/view/photo/photo/public/p914300763.jpg");
                        newsLists.add(news);
                        newsAdapter.notifyDataSetChanged();
                        //停止刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        return newsView;
    }

}
