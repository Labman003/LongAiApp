package com.ouzhouren.longai.common.utils;

import android.content.Context;
import android.os.Environment;

import com.litesuits.http.HttpConfig;
import com.litesuits.http.LiteHttp;

/**
 * Created by BenPC on 2015/8/3.
 */
public class LiteHttpUtil {
    private static LiteHttp liteHttp;
    public static LiteHttp getLiteHttp(Context ctx){
        if(null==liteHttp){
            liteHttp = LiteHttp.newApacheHttpClient(null);
            HttpConfig config = liteHttp.getConfig();
            // set default cache path to all request
            config.setCacheDirPath(Environment.getExternalStorageDirectory() + "/longai-lite-cache");
            // set a new config to lite-http
            liteHttp.initConfig(config);
        }
        return liteHttp;
    }
}
