package com.ouzhouren.longai.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.model.Moment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MomentsFragment extends Fragment {
    public static List<Moment> momentLists = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MomentsAdapter momentsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newsView = inflater.inflate(R.layout.fragment_moments, container, false);
        init(newsView);
        showMoments();



//        momentsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent news_intent = new Intent(getActivity(), NewsDetails.class);
//                news_intent.putExtra("position", position);
//                startActivity(news_intent);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        News news = new News();
//                        int i = newsLists.size();
//                        news.setAuthor("作者" + i);
//                        news.setTitle("标题" + i);
//                        news.setImgUrl("http://img3.douban.com/view/photo/photo/public/p914300763.jpg");
//                        newsLists.add(news);
//                        newsAdapter.notifyDataSetChanged();
//                        //停止刷新动画
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });

        return newsView;
    }

    private void showMoments() {
        for (int i = 0; i <= 5; i++) {
            Moment moment = new Moment();
            moment.setContent("寻找我的另一半！！！");
            moment.setNickName("许阿喵" + (momentLists.size() + 1));
            moment.setPubTime(2016);
            momentLists.add(moment);
        }
        momentsAdapter = new MomentsAdapter(getActivity(), momentLists);
        //添加适配器
        recyclerView.setAdapter(momentsAdapter);
    }

    private void init(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.moments_swipe_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.moments_rv);
        //添加布局
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}


