package com.gooker.dfg.ui.views;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.views.progress.ArcProgress;
import com.gooker.dfg.views.progress.CircleProgress;
import com.gooker.dfg.views.progress.DonutProgress;

import java.util.Timer;
import java.util.TimerTask;

// https://github.com/qyxxjd/Learning-use-OpenSource
public class ProgressUI extends BaseUI {

    private ProgressBar dialog;

    protected final int UPDATE = 100;

    private CircleProgress cp1;

    private CircleProgress cp2;

    private Timer mTimer;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE:
                    int pro = dialog.getProgress();
                    dialog.setProgress(pro + 10);

                    break;

                default:
                    break;
            }
        }

        ;
    };

    private DonutProgress dp1;

    private DonutProgress dp2;

    private DonutProgress dp3;

    private DonutProgress dp4;

    private ArcProgress ap1;

    private ArcProgress ap2;

    private ArcProgress ap3;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method
        // stub

    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method
        // stub
        setContentView(R.layout.ui_views_dialog);
        dialog = findView(R.id.pb);
        // dialog.setTitle("File is Downloading");
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method
        // stub

        cp1 = (CircleProgress) findView(R.id.cp1);
        cp2 = (CircleProgress) findView(R.id.cp2);
        dp1 = (DonutProgress) findViewById(R.id.dp1);
        dp2 = (DonutProgress) findViewById(R.id.dp2);
        dp3 = (DonutProgress) findViewById(R.id.dp3);
        dp4 = (DonutProgress) findViewById(R.id.dp4);

        ap1 = (ArcProgress) findViewById(R.id.ap1);
        ap2 = (ArcProgress) findViewById(R.id.ap2);
        ap3 = (ArcProgress) findViewById(R.id.ap3);
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
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated
                // method stub
                int i = 0;
                while (i < 100) {
                    SystemClock.sleep(1000);

                    Message message = handler.obtainMessage();
                    message.what = UPDATE;
                    handler.sendMessage(message);
                    i = i + 10;
                }

            }
        }).start();
        dialog.setMax(100);
        dialog.bringToFront();
        // dialog.

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated
                // method stub
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cp1.setProgress(cp1.getProgress() + 1);
                        cp2.setProgress(cp2.getProgress() + 1);
                        dp1.setProgress(dp1.getProgress() + 1);
                        dp2.setProgress(dp2.getProgress() + 1);
                        dp3.setProgress(dp3.getProgress() + 1);
                        dp4.setProgress(dp4.getProgress() + 1);
                        ap1.setProgress(ap1.getProgress() + 1);
                        ap2.setProgress(ap2.getProgress() + 1);
                        ap3.setProgress(ap3.getProgress() + 1);
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method
        // stub
        super.onDestroy();
        mTimer.cancel();
    }
}
