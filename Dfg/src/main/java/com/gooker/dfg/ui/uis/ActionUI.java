package com.gooker.dfg.ui.uis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gooker.dfg.R;


public class ActionUI extends Activity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_uis_action);

        new AlertDialog.Builder(this).setMessage("我是通过 action 启动的Activity").create().show();

        this.findViewById(R.id.btnOpenUI).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnOpenUI:
                // 注意在定义 Activity 时候加上了 mimeType 在使用的时候一定要传递 putType() ;否则会报出无法找到
                // action 的异常
                intent = new Intent("com.aoeng.degu.ui.uis.ACTION_UIS");
                intent.putExtra("data", "参数值");
                intent.setType("audio/*");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
