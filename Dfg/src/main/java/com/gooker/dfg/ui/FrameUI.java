package com.gooker.dfg.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.Logger;

import cn.jpush.android.api.JPushInterface;


public class FrameUI extends Activity implements OnClickListener {
    private static final String TAG = FrameUI.class.getName();
    private RelativeLayout rl;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private String str2;
    private String str1;
    private int we;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_views);
        this.findViewById(R.id.mergeView).setOnClickListener(this);
        str1 = "　一书，除了生动还原纪录片之外。一书，除了生动还原纪录片之外一书，除了生动还原纪录片之外";
        str2 = "纪录片的延伸与互补";
        style1();
        style2();
    }

    /**
     *
     */
    private void style1() {
        // TODO Auto-generated method stub
        tv1 = (TextView) this.findViewById(R.id.tv1);
        tv2 = (TextView) this.findViewById(R.id.tv2);
        tv1.setText(str1);
        tv2.setText(str2);
        we = getWindowManager().getDefaultDisplay().getWidth();
        TextPaint paint1 = tv1.getPaint();
        TextPaint paint2 = tv2.getPaint();
        float len1 = paint1.measureText(str1);
        float len2 = paint2.measureText(str2);
        Logger.i(TAG, we + "	" + len1 + "	" + len2);
        if (len1 + len2 > we) {
            LayoutParams params = new LayoutParams((int) ((len1 * we) / (len1 + len2)), LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            tv1.setLayoutParams(params);
            LayoutParams param2 = new LayoutParams((int) ((len2 * we) / (len1 + len2)), LayoutParams.WRAP_CONTENT);
            param2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            tv2.setLayoutParams(param2);
        }
    }

    /**
     *
     */
    private void style2() {
        // TODO Auto-generated method stub
        tv3 = (TextView) this.findViewById(R.id.tv3);
        tv4 = (TextView) this.findViewById(R.id.tv4);
        tv3.setText(str1);
        tv4.setText(str2);
        TextPaint paint3 = tv3.getPaint();
        TextPaint paint4 = tv4.getPaint();
        float len3 = paint3.measureText(str1);
        float len4 = paint4.measureText(str2);
        if (len3 + len4 > we) {
            LayoutParams param3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            param3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            tv3.setLayoutParams(param3);
            LayoutParams param4 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            param4.addRule(RelativeLayout.BELOW, R.id.tv3);
            param4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            tv4.setLayoutParams(param4);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.mergeView:
                Intent intent = new Intent(this, MergeView.class);
                startActivity(intent);
                break;
            default:
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
