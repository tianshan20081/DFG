package com.gooker.dfg.ui.cv;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.gooker.dfg.R;


public class TabsByGroupUI extends ActivityGroup {
    private LinearLayout bodyView, headview;
    private LinearLayout one, two, three, four;
    private int flag = 0; // Íš¹ý±êŒÇÌø×ª²»Í¬µÄÒ³Ãæ£¬ÏÔÊŸ²»Í¬µÄ²Ëµ¥Ïî

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_cv_tabsbygroupui);
        initMainView();
        // ÏÔÊŸ±êŒÇÒ³Ãæ
        showView(flag);
        one.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 0;
                showView(flag);
            }
        });
        two.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 1;
                showView(flag);
            }
        });
        three.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 2;
                showView(flag);
            }
        });
        four.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 3;
                showView(flag);
            }
        });

    }

    /*
     * ³õÊŒ»¯Ö÷œçÃæ
     */
    public void initMainView() {
        headview = (LinearLayout) findViewById(R.id.head);
        bodyView = (LinearLayout) findViewById(R.id.body);
        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        three = (LinearLayout) findViewById(R.id.three);
        four = (LinearLayout) findViewById(R.id.four);
    }

    // ÔÚÖ÷œçÃæÖÐÏÔÊŸÆäËûœçÃæ
    public void showView(int flag) {
        switch (flag) {
            case 0:
                bodyView.removeAllViews();
                View v = getLocalActivityManager().startActivity("one", new Intent(TabsByGroupUI.this, OneView.class)).getDecorView();

                one.setBackgroundResource(R.drawable.frame_button_background);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);

                bodyView.addView(v);
                break;
            case 1:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity("two", new Intent(TabsByGroupUI.this, TwoView.class)).getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_background);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);
                break;
            case 2:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity("three", new Intent(TabsByGroupUI.this, ThreeView.class)).getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_background);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);
                break;
            case 3:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity("four", new Intent(TabsByGroupUI.this, FourView.class)).getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_background);
                break;
            default:
                break;
        }
    }
}