package com.ouzhouren.longai.common.utils;

import com.google.gson.Gson;
import com.ouzhouren.longai.model.Page;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by BenPC on 2015/9/1.
 */
public class PageUtil {
    public static <T> T
    fetchToList(String jsonPage, Type typeOfT) {
        Gson gson = new Gson();
        Page mPage = gson.fromJson(jsonPage, Page.class);
        List mList = mPage.getList();
        String listJson = gson.toJson(mList);
        return gson.fromJson(listJson, typeOfT);
    }
}
