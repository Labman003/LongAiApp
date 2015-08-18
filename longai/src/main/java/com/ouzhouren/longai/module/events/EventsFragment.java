package com.ouzhouren.longai.module.events;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;

public class EventsFragment extends Fragment {
    private Activity mAc;
    private LinearLayoutManager layoutManager;
private RecyclerView recyclerView;
    private EventFragmentAdapter mAdapter;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAc = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recycler_view);
     //   layoutManager = new LinearLayoutManager(mAc);
 layoutManager = new GridLayoutManager(mAc, 2);
        initData();//初始化数据
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        mAdapter = new EventFragmentAdapter();
        mAdapter.setOnItemClickLitener(new EventFragmentAdapter.OnItemClickLitener(){
            @Override
            public void onItemClick(View view, int position) {
                MyLogger logger = MyLogger.benLog();
                logger.i("click"+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(mAdapter);//设置适配器
        return fragmentView;
    }

    private void initData() {

    }


}
