package com.gooker.dfg.contentprovider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class RegionContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.aoeng.degu.permission.regionContentprovider";
    private static final int CITIES = 1;
    private static final int CITY_CODE = 2;
    private static final int CITY_NAME = 3;
    private static final int CITIES_IN_PROVINCE = 4;
    private static UriMatcher uriMatcher;

    private SQLiteDatabase database;

    static {
        // 开始映射 URI 和返回码
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 用于查询所有城市的URI
        uriMatcher.addURI(AUTHORITY, "cities", CITIES);
        // 用于根据城市代码查询城市信息 的URI
        uriMatcher.addURI(AUTHORITY, "code/#", CITY_CODE);
        // 用于根据城市名称查询城市信息的URI
        uriMatcher.addURI(AUTHORITY, "name/*", CITY_NAME);
        // 用于根据省名称查询省内所有城市的信息
        uriMatcher.addURI(AUTHORITY, "cities_in_province/*", CITIES_IN_PROVINCE);
    }

    private SQLiteDatabase openDatabase() {
        try {
            String databaseFilename = "/sdcard/region.db";
            if (!new File(databaseFilename).exists()) {
                InputStream in = getContext().getResources().getAssets().open("region.db");
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = in.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                in.close();
                fos.close();
            }
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
            return database;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        database = openDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        Cursor cursor = null;
        // 根据URI 获得返回码
        switch (uriMatcher.match(uri)) {
            case CITIES:
                cursor = database.query("v_cities_province", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CITY_CODE:// 根据城市代码查询城市信息
                String cityCode = uri.getPathSegments().get(1);
                if (selection == null) {
                    selection = "city_code='" + cityCode + "'";
                } else {
                    selection += " and (city_code ='" + cityCode + "')";
                }

                cursor = database.query("t_cities", projection, selection, selectionArgs, null, null, sortOrder);

                break;
            case CITY_NAME:// 根据城市名称查询城市信息
                String cityName = uri.getPathSegments().get(1);
                if (selection == null)
                    selection = "city_name='" + cityName + "'";
                else
                    selection += " and (city_name='" + cityName + "')";
                cursor = database.query("t_cities", projection, selection, selectionArgs, null, null, sortOrder);

                break;
            case CITIES_IN_PROVINCE:// 根据省名称查询省内的所有城市
                String provinceName = uri.getPathSegments().get(1);
                if (selection == null)
                    selection = "province_name='" + provinceName + "'";
                else
                    selection += " and (province_name='" + provinceName + "')";
                cursor = database.query("v_cities_province", projection, selection, selectionArgs, null, null, sortOrder);

                break;
            default:
                throw new IllegalArgumentException("<" + uri + ">格式不正确");
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
