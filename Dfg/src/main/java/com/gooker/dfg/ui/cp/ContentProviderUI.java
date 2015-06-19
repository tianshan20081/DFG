package com.gooker.dfg.ui.cp;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.SmsAdapter;
import com.gooker.dfg.utils.common.Toaster;


public class ContentProviderUI extends Activity implements OnClickListener {
    private ListView lvContectsInfo;
    private Cursor cursor;
    private ContentResolver contentResolver;
    private SimpleCursorAdapter simpleCursorAdapter;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_cp_home);
        this.findViewById(R.id.btnContectsInfo).setOnClickListener(this);
        this.findViewById(R.id.btnSmsInfo).setOnClickListener(this);
        this.findViewById(R.id.btnCities).setOnClickListener(this);
        this.findViewById(R.id.btnCityInfoById).setOnClickListener(this);
        this.findViewById(R.id.btnCityInfoByName).setOnClickListener(this);
        this.findViewById(R.id.btnCitiesByProvinceName).setOnClickListener(this);
        lvContectsInfo = (ListView) this.findViewById(R.id.lvContectsInfo);

        contentResolver = getContentResolver();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnCitiesByProvinceName:
                uri = Uri.parse("content://com.aoeng.degu.permission.regionContentprovider/cities_in_province/陕西");
                cursor = contentResolver.query(uri, new String[]{"city_code as _id", "city_name", "province_code"}, null, null, "city_code");
                simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.activity_list_item, cursor, new String[]{"city_name"}, new int[]{android.R.id.text1});

                lvContectsInfo.setAdapter(simpleCursorAdapter);
                break;
            case R.id.btnCityInfoByName:
                uri = Uri.parse("content://com.aoeng.degu.permission.regionContentprovider/name/西安");
                cursor = contentResolver.query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    Toaster.toastCenter(this, cursor.getString(cursor.getColumnIndex("city_name")), true);
                }
                break;
            case R.id.btnCityInfoById:
                uri = Uri.parse("content://com.aoeng.degu.permission.regionContentprovider/code/024");
                cursor = contentResolver.query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    Toaster.toastCenter(this, cursor.getString(cursor.getColumnIndex("city_name")), true);
                }

                break;

            case R.id.btnCities:
                uri = Uri.parse("content://com.aoeng.degu.permission.regionContentprovider/cities");
                cursor = contentResolver.query(uri, new String[]{"city_code as _id", "city_name", "province_code"}, null, null, null);
                simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.activity_list_item, cursor, new String[]{"city_name"}, new int[]{android.R.id.text1});
                lvContectsInfo.setAdapter(simpleCursorAdapter);
                break;
            case R.id.btnSmsInfo:
                cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, "address like ? ", new String[]{"1%"}, null);
                SmsAdapter smsAdapter = new SmsAdapter(this, cursor);
                lvContectsInfo.setAdapter(smsAdapter);
                break;
            case R.id.btnContectsInfo:
                cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

                simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.activity_list_item, cursor, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, new int[]{android.R.id.text1});
                lvContectsInfo.setAdapter(simpleCursorAdapter);
                break;

            default:
                break;
        }
    }

}
