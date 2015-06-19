package com.gooker.dfg.ui;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.DBServices;

import cn.jpush.android.api.JPushInterface;


public class SqlLiteDBUI extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_data_sqlite);

        DBServices dbServices = new DBServices(this);
        Cursor cursor = dbServices.query("select * from t_test", null);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, cursor, new String[]{"name"}, new int[]{android.R.id.text1});

        // setListAdapter(simpleCursorAdapter);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        JPushInterface.onPause(this);
    }
}
