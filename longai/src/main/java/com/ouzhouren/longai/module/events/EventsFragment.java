package com.ouzhouren.longai.module.events;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class EventsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private Activity mAc;
    private LinearLayoutManager layoutManager;
private RecyclerView recyclerView;
    private EventsFragmentAdapter mAdapter;
    private TextView dateTv;

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
        dateTv = (TextView) fragmentView.findViewById(R.id.events_tv_date);
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                EventsFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(mAc.getFragmentManager(), "Datepickerdialog");
            }
        });
     //   layoutManager = new LinearLayoutManager(mAc);
 layoutManager = new GridLayoutManager(mAc, 2);
        initData();//初始化数据
        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        mAdapter = new EventsFragmentAdapter();
        mAdapter.setOnItemClickLitener(new EventsFragmentAdapter.OnItemClickLitener(){
            @Override
            public void onItemClick(View view, int position) {
                MyLogger logger = MyLogger.benLog();
                Bundle bundle = new Bundle();
                bundle.putBoolean("enroll_directlly",false);
                Intent intent = new Intent(mAc,DetailEventActivity.class);
                mAc.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mAdapter.setOnStateButtonClickLitener(new EventsFragmentAdapter.OnStateButtonClickLitener() {
            @Override
            public void onStateButtonClick(View view, int position) {
                MyLogger logger = MyLogger.benLog();
                Intent intent = new Intent(mAc,DetailEventActivity.class);
                intent.putExtra("enroll_directlly",true);
                mAc.startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);//设置适配器
        return fragmentView;
    }

    private void initData() {

    }


    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

    }
}
