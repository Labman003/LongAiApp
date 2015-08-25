package com.ouzhouren.longai.module.news;


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
import com.ouzhouren.longai.entity.News;
import com.ouzhouren.longai.module.news.activity.NewsDetails;
import com.ouzhouren.longai.module.news.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    public static List<News> newsLists = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
            news.setImgUrl("http://g.hiphotos.baidu.com/baike/s%3D500/sign=49ab240bb68f8c54e7d3c52f0a2b2dee/7e3e6709c93d70cfdd3d48e8fadcd100bba12b14.jpg");
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
                        news.setAuthor("作者"+i);
                        news.setTitle("标题" + i);
                        news.setImgUrl("http://img3.douban.com/view/photo/photo/public/p914300763.jpg");
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
