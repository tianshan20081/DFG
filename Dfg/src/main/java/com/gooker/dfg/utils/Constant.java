/**
 *
 */
package com.gooker.dfg.utils;

import android.os.Environment;

/**
 * Mar 23, 2014 11:55:16 AM
 */
public class Constant {
    public static String ACTIVE_UID;
    public static final String ADDRESS_ID = "addressId";
    public static final String APPKEY = "appkey";
    public static final String APPKEYVALUE = "2011060847";
    public static final String APPSECRET = "appSecret";
    public static final String APPSECRETVALUE = "2d0debf0d3828a08751f2de0a7f82f21";
    public static final String BALANCE = "balance";
    public static final String CLASS = "class";
    public static final int ACCOUNT = 2;
    public static final String CLIENT_VERSION = "clientv";
    public static final String CN_OPERATOR = "cn_operator";
    public static final String CONSUMER_KEY = "1865272664";
    public static final String CONSUMER_SECRET = "29d6f40ec5ed74a735b4ad8aaa0a75e4";
    public static final String CONSUMER_URL = "http://api.t.sina.com.cn/oauth/access_token";
    public static final String DENSITY = "density";
    public static final String FORMAT = "format";
    public static final String FORMATVALUE = "json";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final int HOME = 1;
    public static final String IMEI = "imei";
    public static final String IMSI = "imsi";
    public static final String LANGUAGE = "language";
    public static final String LOCAL_PATH_HOME_CLASSICS = "/itcast/home/classics/";
    public static final String LOCAL_PATH_HOME_POSTER = "/itcast/home/poster/";
    public static final String LOCAL_PATH_RECENT_BROWSE = "/itcast/recentbrowse/";
    public static final String LOCAL_PATH_SAVE_PRODUCT_BIG_PIC = "/itcast/saveimage/";
    public static final String LOCAL_PATH_SHOPCAR = "/itcast/shopcar/";
    public static final int MIN_SPACE_FOR_VERSION_UPDATA = 10485760;
    public static final String MODEL = "model";
    public static final int MORE = 5;
    public static final String NAME = "name";
    public static final int NEED_SYNCHRO_SHOPCAR = 0;
    public static final String NICK_NAME = "nick_name";
    public static final int NO_NEED_SYNCHRO_SHOPCAR = 1;
    public static final String PAGESIZE = "10";
    public static final String PLATFORM_NAME = "platformn";
    public static final String POINT = "point";
    public static final String SDPATH;
    public static final int SEARCH = 3;
    public static final int SHOPCAR = 4;
    public static int SHOPCAR_NUM = 0;
    public static final String SIGN = "sign";
    public static final String SINGMETHOD = "sign_method";
    public static final String SINGMETHODVALUE = "md5";
    public static final String SMS_CENTER_NUMBER = "sms_center_number";
    public static final String SOURCE = "source";
    public static final String SOURCE_CODE = "source";
    public static final String SYNCHRO_SHOPCAR_FLAG = "synchroShopcarFlag";
    public static final String T = "t";
    public static final String TEMP_LOCAL_PATH_CATEGORY = "/itcast/temp/category/";
    public static final String TEMP_LOCAL_PATH_FAVORITE = "/itcast/temp/favorite/";
    public static final String TEMP_LOCAL_PATH_PRODUCTBIGPIC = "/itcast/temp/productbigpic/";
    public static final String TEMP_LOCAL_PATH_PRODUCTDETAIL = "/itcast/temp/productdetail/";
    public static final String TEMP_LOCAL_PATH_PRODUCTLIST = "/itcast/temp/productlist/";
    public static final String TEMP_LOCAL_PATH_PRODUCTSMALLPIC = "/itcast/temp/productsmallpic/";
    public static final String TEMP_LOCAL_PATH_SEARCHLIST = "/itcast/temp/searchlist/";
    public static final String TEMP_LOCAL_PATH_WELCOME = "/itcast/welcome/";
    public static final int TIMEOUT_TIME = 30000;
    public static final String TTID = "ttid";
    public static final String UID = "uid";
    public static final String USER = "user";
    public static final String USERTOKEN = "usertoken";
    public static final String USER_ID = "userId";
    public static final String USER_TOKEN = "usertoken";
    public static final String VER = "ver";
    public static final String VERVALUE = "1.0";
    public static final String WELCOME_IMG_NAME = "welcomeImgName";
    public static final String X_RESOLUTION = "xResolution";
    public static final String Y_RESOLUTION = "yResolution";
    public final static int FAILED = 1;
    public final static int SUCCESS = 1;
    public final static int NET_FAILED = 2;
    public final static int TIME_OUT = 3;
    public static final int IMAGE_QUALITY = 100;
    public static final int NET_ERROR = 5;
    /**
     * 相册照片分割 最小时间 （单位：毫秒）
     */
    public static final long GroupMaxMinutes = 1 * 60 * 60 * 1000;

    public static int defaultIndex;
    public static boolean exit = true;
    public static int selectedHome;
    public static String selectedNum;
    public static String FLAG;
    public static String LIMIT_BUY = "limitbuy";
    public static String NEW_PRODUCT = "newproduct";
    public static String BRAND = "brand";
    public static String CATEGORY = "category";
    public static String HOT_PRODUCT = "hotproduct";
    public static String FILTER = "filter";
    public static String SEARCHURL = "search";

    static {
        int i = 0;
        defaultIndex = 1;
        selectedHome = i;
        selectedNum = "0";
        SDPATH = Environment.getExternalStorageDirectory().getPath();
        ACTIVE_UID = "uid";
        SHOPCAR_NUM = i;
    }
}
