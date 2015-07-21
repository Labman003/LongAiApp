package com.ouzhouren.base;

import android.app.Application;

/**test
 * 做一些所有app都会用到的基础初始化或者配置。之后其他应用的application应该都继承此BaseApplication。
 */
public class BaseApplication extends Application {

	private static BaseApplication instance = null;

	public static BaseApplication getInstance() {
		if(null == instance)
		{
			//Application不存在
		}
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

}