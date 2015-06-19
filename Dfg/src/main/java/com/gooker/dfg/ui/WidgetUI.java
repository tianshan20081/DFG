package com.gooker.dfg.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.gooker.dfg.R;

import cn.jpush.android.api.JPushInterface;


public class WidgetUI extends Activity {
    private TextView tv02;
    private TextView tv01;
    private TextView tv03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_wigets);
        tv01 = (TextView) this.findViewById(R.id.tv01);
        tv02 = (TextView) this.findViewById(R.id.tv02);
        tv03 = (TextView) this.findViewById(R.id.tv03);
        StringBuffer htl = new StringBuffer("<font color='red'>I love Android .</font><br>");
        htl.append("<font color='#0000ff'><big><i>I love Android .</i></big></font><p>");
        htl.append("<font color='@" + android.R.color.white + "' ><tt><b><big><u>I love Android.</u></big></b></tt>>></font><p>");
        htl.append("<big><a href='http://weibo.com/tianshan20081'>我的新浪微博</a></big>");
        // 将带预定义标签的字符串转换成 CharSequence 对象
        CharSequence charSequence = Html.fromHtml(htl.toString());
        tv01.setText(charSequence);
        tv01.setMovementMethod(LinkMovementMethod.getInstance());
        StringBuffer buffer2 = new StringBuffer("My Url: http://weibo.com/tianshan20081\n");
        buffer2.append("My Email : zhangshch2008@qq.com\n");
        buffer2.append("My Phone : 13811691807\n");
        tv02.setText(buffer2.toString());
        tv02.setMovementMethod(LinkMovementMethod.getInstance());
        tv03.setEllipsize(TextUtils.TruncateAt.END);
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
