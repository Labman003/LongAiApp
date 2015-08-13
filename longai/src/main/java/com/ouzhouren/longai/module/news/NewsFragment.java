package com.ouzhouren.longai.module.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ouzhouren.longai.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NewsFragment extends Fragment {
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
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) newsView.findViewById(R.id.news_swipe_container);
        ListView listView = (ListView) newsView.findViewById(R.id.news_lv_list);

        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("title", "标题" + i);
            listItem.put("author", "作者" + i);
            listItem.put("icon", "http://g.hiphotos.baidu.com/baike/s%3D500/sign=49ab240bb68f8c54e7d3c52f0a2b2dee/7e3e6709c93d70cfdd3d48e8fadcd100bba12b14.jpg");
            listItems.add(listItem);
        }
        //SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.news_list_item, new String[]{"title", "author", "icon"}, new int[]{R.id.news_listItem_tv_title, R.id.news_listItem_tv_author, R.id.news_listItem_iv_icon});
        listView.setAdapter(new NewsAdapter(getActivity(),listItems));

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });
        return newsView;
    }

}
