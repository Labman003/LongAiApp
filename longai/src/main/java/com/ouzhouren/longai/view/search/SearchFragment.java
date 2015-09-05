package com.ouzhouren.longai.view.search;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.presenter.SearchPresenter;
import com.ouzhouren.longai.presenter.SearchViewInterface;
import com.ouzhouren.longai.view.member.activity.DetailActivity;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SearchFragment extends Fragment implements SearchViewInterface {

    private Activity mAc;
    private FloatingActionButton fabBtn;
    private View rootView;
    private TextView noDataTv;
    private GridView gridview;
    private UserAdapter userAdapter;
    private SearchPresenter searchPresenter;
    private SweetAlertDialog dlg;
    private MyLogger logger = MyLogger.benLog();
    private Boolean isFirstCreateView = true;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAc = getActivity();
        this.searchPresenter = new SearchPresenter(this,mAc);
        logger.i("searchOncreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        findView(rootView);
        logger.i("searchOncreateView");
        init();
        return rootView;
    }


    private void findView(View rootView) {
        gridview = (GridView) rootView.findViewById(R.id.user_grid);
        fabBtn = (FloatingActionButton) rootView.findViewById(R.id.fabBtn);
        noDataTv = (TextView) rootView.findViewById(R.id.search_tv_nodata);
    }

    private void init() {
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        dlg.setTitleText("缘分系统正在为您连接有缘人...");
        gridview.setOnItemClickListener(userClickListener);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPresenter.searchLovers();
            }
        });
        searchPresenter.restore();
        if(isFirstCreateView){
            searchPresenter.searchLovers();
            isFirstCreateView = false;
        }
        logger.i("init结束");
    }
    // Adapter OnItemClick event
    private AdapterView.OnItemClickListener userClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            goDetail(i);
        }
    };

    @Override
    public void refreshLovers(List<User> users) {
        if(users!=null){
            if(userAdapter==null){
                userAdapter = new UserAdapter(mAc, users);

            }
            gridview.setAdapter(userAdapter);
            userAdapter.setUsers(users);
            userAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {
        dlg.dismiss();
        dlg = new SweetAlertDialog(mAc, SweetAlertDialog.PROGRESS_TYPE);
        dlg.setTitleText("缘分系统正在为您连接有缘人...");
        dlg.show();
    }

    @Override
    public void dismissProgress() {
        dlg.dismiss();
    }

    @Override
    public void showNodata() {
        noDataTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissNodata() {
        noDataTv.setVisibility(View.GONE);
    }

    @Override
    public void showFail(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_SHORT)
                .setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    @Override
    public void goDetail(int position) {
        Intent detailIntent = new Intent(mAc, DetailActivity.class);
        detailIntent.putExtra("position", position);
        startActivity(detailIntent);
    }
}
