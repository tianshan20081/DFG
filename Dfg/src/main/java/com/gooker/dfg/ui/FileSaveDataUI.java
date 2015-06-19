package com.gooker.dfg.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.Toaster;

import java.io.InputStream;
import java.io.OutputStream;

import cn.jpush.android.api.JPushInterface;


public class FileSaveDataUI extends Activity implements OnClickListener {
    private EditText etDataInfo;
    private TextView tvDataInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_data_file);
        this.findViewById(R.id.btnSaveData).setOnClickListener(this);
        this.findViewById(R.id.btnGetData).setOnClickListener(this);
        etDataInfo = (EditText) this.findViewById(R.id.etDataInfo);
        tvDataInfo = (TextView) this.findViewById(R.id.tvDataInfo);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnSaveData:
                String dataInfo = etDataInfo.getText().toString().trim();
                if (TextUtils.isEmpty(dataInfo)) {
                    Toaster.toast(this, "数据不可为空", false);
                    return;
                }
                try {
                    OutputStream os = openFileOutput("file.txt", Activity.MODE_PRIVATE);
                    os.write(dataInfo.getBytes());
                    os.close();
                    Toaster.toast(this, "数据添加完毕", false);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.btnGetData:
                try {
                    InputStream is = openFileInput("file.txt");
                    byte[] bs = new byte[1000];
                    is.read(bs);
                    String info = new String(bs, "utf-8");
                    tvDataInfo.setText(info);
                    is.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
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
