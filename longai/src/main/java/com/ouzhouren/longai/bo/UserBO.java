package com.ouzhouren.longai.bo;

import android.content.Context;

import com.ouzhouren.base.cache.ACache;
import com.ouzhouren.longai.entity.User;

/**
 * Created by BenPC on 2015/8/3.
 */
public class UserBO {
    private static String CACHE_USER = "CacheUser";
    private UserBO userBO;
    private ACache aCache;

    private UserBO(Context ctx) {
        this.aCache = ACache.get(ctx);//创建ACache组件
    }

    public UserBO getInstance(Context ctx) {
        if (null == userBO) {
            userBO = new UserBO(ctx);
        }
        return userBO;
    }

    public boolean logon(User user) {

        return true;
    }

    public boolean login() {
        return true;
    }

    public boolean logout() {
        return true;
    }

}
