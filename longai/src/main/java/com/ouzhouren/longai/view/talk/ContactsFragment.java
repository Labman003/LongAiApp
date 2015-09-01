package com.ouzhouren.longai.view.talk;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouzhouren.longai.R;

public class ContactsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private Activity mAc;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAc = getActivity();
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.contacts_rv_contact_list);
        layoutManager = new LinearLayoutManager(mAc);
      //  initData();//初始化数据
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        mAdapter = new ContactsAdapter();
        mAdapter.setOnItemClickLitener(new ContactsAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i =new Intent(mAc,TalkActivity.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(mAdapter);//设置适配器
        return fragmentView;
    }




}
