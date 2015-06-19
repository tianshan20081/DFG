package com.gooker.dfg.ui.logins;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.gooker.dfg.R;

import java.util.ArrayList;
import java.util.List;


public class MainQQActivity extends Activity {
    private ViewPager viewPager;
    private ImageView imageView;
    private TextView textView1, textView2, textView3, textView4;
    private View view1, view2, view3, view4;
    private List<View> views;
    private int offSet = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ui_logins_qq_main);
        initView();
        initListener();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vPager);
        imageView = (ImageView) findViewById(R.id.cursor);

        textView1 = (TextView) findViewById(R.id.speaktext);
        textView2 = (TextView) findViewById(R.id.fridenttext);
        textView3 = (TextView) findViewById(R.id.grouptext);
        textView4 = (TextView) findViewById(R.id.changetext);

        LayoutInflater layoutInflater = getLayoutInflater();
        view1 = layoutInflater.inflate(R.layout.qqtab_1, null);
        view2 = layoutInflater.inflate(R.layout.qqtab_2, null);
        view3 = layoutInflater.inflate(R.layout.qqtab_3, null);
        view4 = layoutInflater.inflate(R.layout.qqtab_4, null);

        views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenW = displayMetrics.widthPixels;
        offSet = (screenW / 4 - bmpW) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(screenW, 0);
        imageView.setImageMatrix(matrix);
    }

    private void initListener() {
        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
        textView4.setOnClickListener(new MyOnClickListener(3));

        viewPager.setAdapter(new MyPagerAdapter(views));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(0);
        textView1.setTextColor(getResources().getColor(R.color.color_bai));
    }

    private class MyPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position));
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    private class MyOnPageChangeListener implements OnPageChangeListener {
        int one = offSet * 2 + bmpW;// 页卡1 -> 页卡2 偏移量

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageSelected(int arg0) {
            Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);
            switch (currIndex) {
                case 0:
                    textView1.setTextColor(getResources().getColor(R.color.color_bai));
                    textView2.setTextColor(getResources().getColor(R.color.title_bg));
                    textView3.setTextColor(getResources().getColor(R.color.title_bg));
                    textView4.setTextColor(getResources().getColor(R.color.title_bg));
                    break;
                case 1:
                    textView2.setTextColor(getResources().getColor(R.color.color_bai));
                    textView1.setTextColor(getResources().getColor(R.color.title_bg));
                    textView3.setTextColor(getResources().getColor(R.color.title_bg));
                    textView4.setTextColor(getResources().getColor(R.color.title_bg));
                    break;
                case 2:
                    textView3.setTextColor(getResources().getColor(R.color.color_bai));
                    textView2.setTextColor(getResources().getColor(R.color.title_bg));
                    textView1.setTextColor(getResources().getColor(R.color.title_bg));
                    textView4.setTextColor(getResources().getColor(R.color.title_bg));
                    break;
                case 3:
                    textView4.setTextColor(getResources().getColor(R.color.color_bai));
                    textView2.setTextColor(getResources().getColor(R.color.title_bg));
                    textView3.setTextColor(getResources().getColor(R.color.title_bg));
                    textView1.setTextColor(getResources().getColor(R.color.title_bg));
                default:
                    break;
            }
        }

    }

    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            viewPager.setCurrentItem(index);

            switch (index) {
                case 0:
                    textView1.setTextColor(getResources().getColor(R.color.color_bai));
                    textView2.setTextColor(getResources().getColor(R.color.title_bg));
                    textView3.setTextColor(getResources().getColor(R.color.title_bg));
                    textView4.setTextColor(getResources().getColor(R.color.title_bg));
                    break;
                case 1:
                    textView2.setTextColor(getResources().getColor(R.color.color_bai));
                    textView1.setTextColor(getResources().getColor(R.color.title_bg));
                    textView3.setTextColor(getResources().getColor(R.color.title_bg));
                    textView4.setTextColor(getResources().getColor(R.color.title_bg));
                    break;
                case 2:
                    textView3.setTextColor(getResources().getColor(R.color.color_bai));
                    textView2.setTextColor(getResources().getColor(R.color.title_bg));
                    textView1.setTextColor(getResources().getColor(R.color.title_bg));
                    textView4.setTextColor(getResources().getColor(R.color.title_bg));
                    break;
                case 3:
                    textView4.setTextColor(getResources().getColor(R.color.color_bai));
                    textView2.setTextColor(getResources().getColor(R.color.title_bg));
                    textView3.setTextColor(getResources().getColor(R.color.title_bg));
                    textView1.setTextColor(getResources().getColor(R.color.title_bg));
                default:
                    break;
            }

        }

    }
}
