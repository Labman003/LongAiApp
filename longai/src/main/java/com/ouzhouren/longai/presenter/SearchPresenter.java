package com.ouzhouren.longai.presenter;

import android.content.Context;

import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.model.UserBusinessImp;
import com.ouzhouren.longai.model.UserModelInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenPC on 2015/9/3.
 */
public class SearchPresenter {
    private SearchViewInterface searchViewInterface;
    private UserModelInterface userModelInterface;
    private Context ctx;
    public static List<User> users = new ArrayList<User>();

    public SearchPresenter(SearchViewInterface searchViewInterface,Context ctx) {
        this.searchViewInterface = searchViewInterface;
        this.ctx = ctx;
        this.userModelInterface = new UserBusinessImp();
    }
    public  void restore(){
        if(users.size()==0){
            searchViewInterface.showNodata();
        }
        else {
            searchViewInterface.dismissNodata();
            searchViewInterface.refreshLovers(users);
        }
    }
    public void searchLovers(){
        searchViewInterface.showProgress();
        //todo 随机gens
        userModelInterface.getUsersByGens(245, new UserModelInterface.GetUsersByGensCallBack() {
            @Override
            public void onSuccess(List<User> musers) {
                searchViewInterface.dismissProgress();
                users = musers;
                if(users.size()==0){
                    searchViewInterface.showNodata();
                }
                else {
                    searchViewInterface.dismissNodata();
                    searchViewInterface.refreshLovers(users);
                }
            }

            @Override
            public void onFail(String e) {
                searchViewInterface.dismissProgress();
                searchViewInterface.showFail("没有更多合拍匹配了");
            }
        }, ctx);
    }
}
