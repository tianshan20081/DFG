package com.gooker.dfg.ui.views;

import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.views.progress.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;


public class NumProgressBarUI extends BaseUI {

    private NumberProgressBar numPb1;
    private NumberProgressBar numPb8;
    private NumberProgressBar numPb7;
    private NumberProgressBar numPb6;
    private NumberProgressBar numPb5;
    private NumberProgressBar numPb4;
    private NumberProgressBar numPb3;
    private NumberProgressBar numPb2;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method
        // stub

    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method
        // stub

        setContentView(R.layout.ui_views_num_progressbar);
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method
        // stub
        numPb1 = (NumberProgressBar) findView(R.id.numPb1);
        numPb2 = (NumberProgressBar) findView(R.id.numPb2);
        numPb3 = (NumberProgressBar) findView(R.id.numPb3);
        numPb4 = (NumberProgressBar) findView(R.id.numPb4);
        numPb5 = (NumberProgressBar) findView(R.id.numPb5);
        numPb6 = (NumberProgressBar) findView(R.id.numPb6);
        numPb7 = (NumberProgressBar) findView(R.id.numPb7);
        numPb8 = (NumberProgressBar) findView(R.id.numPb8);

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method
        // stub

    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method
        // stub

        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated
                // method stub
                runOnUiThread(new Runnable() {
                    public void run() {
                        numPb1.setProgress(numPb1.getProgress() + 1);
                        numPb2.setProgress(numPb2.getProgress() + 1);
                        numPb3.setProgress(numPb3.getProgress() + 1);
                        numPb4.setProgress(numPb4.getProgress() + 1);
                        numPb5.setProgress(numPb5.getProgress() + 1);
                        numPb6.setProgress(numPb6.getProgress() + 1);
                        numPb7.setProgress(numPb7.getProgress() + 1);
                        numPb8.setProgress(numPb8.getProgress() + 1);
                    }
                });
            }
        }, 1000, 100);
    }
}
