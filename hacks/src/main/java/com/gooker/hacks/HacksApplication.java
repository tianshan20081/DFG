package com.gooker.hacks;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by sczhang on 15/6/23. 上午9:40
 * Package Name com.gooker.hacks
 * Project Name DFG
 */
public class HacksApplication extends MultiDexApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);



    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
