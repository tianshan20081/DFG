package com.gooker.dfg.ui.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gooker.dfg.R;

public class ScTvUI extends Activity implements View.OnTouchListener {

    private TextView tvScInfo;
    private ScrollView scView;
    private String TAG = "ScTv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc_tv_ui);
        scView = (ScrollView) findViewById(R.id.scView);
        tvScInfo = (TextView) findViewById(R.id.tvScInfo);


        scView.setOnTouchListener(this);
        tvScInfo.setText(getString(R.string.sctvinfo));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sc_tv_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                if (scView.getScaleY() <= 0) {
                    Log.e(TAG, "SCROLL TO TOP");
                }
                if (scView.getChildAt(0).getMeasuredHeight() <= scView.getHeight() + scView.getScrollY()) {
                    Log.e(TAG, "SCROLL TO Bottom");
                    tvScInfo.append(getString(R.string.sctvinfo));
                    StringBuffer sb = new StringBuffer();
                    sb.append("scView.getChildAt(0).getMeasuredHeight()\t").append("\t").append(scView.getChildAt(0).getMeasuredHeight()).append("\t");
                    sb.append("scView.getHeight()").append("\t").append(scView.getHeight()).append("\t")
                            .append("scView.getScrollY()").append("\t").append(scView.getScrollY());
                    Log.e(TAG, sb.toString());
                }
                break;
        }
        return false;
    }
}
