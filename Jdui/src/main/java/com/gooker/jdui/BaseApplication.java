package com.gooker.jdui;

import android.app.Application;
import android.content.res.Configuration;

import com.gooker.jdui.config.Constants;
import com.gooker.jdui.image.ImageLoaderConfig;


/**
 * @author Tau.Chen 陈涛
 * @version V_1.0.0
 * @email tauchen1990@gmail.com,1076559197@qq.com
 * @date 2013年9月12日
 * @description
 */
public class BaseApplication extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
       ImageLoaderConfig.initImageLoader(this, Constants.BASE_IMAGE_CACHE);
    }

    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
    }

}
