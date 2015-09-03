package com.ouzhouren.longai.view.events;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.ouzhouren.longai.model.Event;
import com.ouzhouren.longai.presenter.EventPresenter;
import com.ouzhouren.longai.presenter.EventViewInterface;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EventsFragment extends Fragment implements DatePickerDialog.OnDateSetListener,EventViewInterface {
    private Activity mAc;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private EventsFragmentAdapter mAdapter;
    private TextView dateTv;
    private TextView noDataTv;
    private SweetAlertDialog dlg;
    private View fragmentView;
    private EventPresenter eventsPresenter;
    private boolean isFirstCreateView =true;
    private MyLogger logger = MyLogger.benLog();

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logger.i("eventF1");
        mAc = getActivity();
        this.eventsPresenter = new EventPresenter(mAc);
        this.eventsPresenter.setEventViewInterface(this);
        logger.i("eventF2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        logger.i("eventF3");
        fragmentView = inflater.inflate(R.layout.fragment_events, container, false);
        logger.i("eventF4");
        findView(fragmentView);
        logger.i("eventF5");
        init();
        logger.i("eventF6");
        return fragmentView;

    }

    private void init() {
        layoutManager = new GridLayoutManager(mAc, 2);
                recyclerView.setLayoutManager(layoutManager);//设置布局管理器,不设置会闪退
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime();
            }
        });

        eventsPresenter.restore();
//        if(isFirstCreateView){
//            eventsPresenter.addEvents(mAc);
//            isFirstCreateView = false;
//        }
    }


    private void findView(View fragmentView) {
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recycler_view);
        dateTv = (TextView) fragmentView.findViewById(R.id.events_tv_date);
        noDataTv = (TextView) fragmentView.findViewById(R.id.events_tv_nodata);
    }



    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

    }

    @Override
    public void chooseCity() {
    }

    @Override
    public void chooseTime() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                EventsFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(mAc.getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public long getTime() {
        return new Date().getTime();
    }

    @Override
    public void refreshEvents(String city, List<Event> events, String time) {
        if(events!=null){
            if(mAdapter==null){
                mAdapter = new EventsFragmentAdapter(mAc, events);
                mAdapter.setOnItemClickLitener(new EventsFragmentAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        goToEventDetail();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                mAdapter.setOnStateButtonClickLitener(new EventsFragmentAdapter.OnStateButtonClickLitener() {
                    @Override
                    public void onStateButtonClick(View view, int position) {
                        goToEventDetailAndEnroll();
                    }
                });
                recyclerView.setAdapter(mAdapter);
            }
            mAdapter.setEvents(events);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void addEvents(String city, List<Event> events, String time) {

    }

    @Override
    public void goToEventDetail() {
        MyLogger logger = MyLogger.benLog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("enroll_directlly", false);
        Intent intent = new Intent(mAc, DetailEventActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void goToEventDetailAndEnroll() {
        MyLogger logger = MyLogger.benLog();
        Intent intent = new Intent(mAc, DetailEventActivity.class);
        intent.putExtra("enroll_directlly", true);
        mAc.startActivity(intent);
    }

    @Override
    public void showProgress() {
        dlg.dismiss();
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        dlg.show();
    }

    @Override
    public void dismissProgress() {
        dlg.dismiss();
    }
    @Override
    public void showFail(String s) {
        Snackbar.make(fragmentView, s, Snackbar.LENGTH_SHORT)
                .setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }


    @Override
    public void showNodata() {
        noDataTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissNodata() {
        noDataTv.setVisibility(View.GONE);
    }
}
