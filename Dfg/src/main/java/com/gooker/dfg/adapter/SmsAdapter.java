package com.gooker.dfg.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gooker.dfg.R;


public class SmsAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;
    private TextView tvPhoneNO;
    private TextView tvSmsInfo;

    public SmsAdapter(Context context, Cursor c) {
        super(context, c);
        // TODO Auto-generated constructor stub
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = layoutInflater.inflate(R.layout.ui_cp_sms_item, null);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TODO Auto-generated method stub
        tvPhoneNO = (TextView) view.findViewById(R.id.tvPhoneNO);
        tvSmsInfo = (TextView) view.findViewById(R.id.tvSmsInfo);
        tvPhoneNO.setText(cursor.getString(cursor.getColumnIndex("address")));
        tvSmsInfo.setText(cursor.getString(cursor.getColumnIndex("body")));
    }

}
