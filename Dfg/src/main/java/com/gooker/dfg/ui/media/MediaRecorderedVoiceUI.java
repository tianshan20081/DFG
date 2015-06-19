package com.gooker.dfg.ui.media;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.listener.SpeakButtonTouchListener;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.MediaUtil;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.LogUtils;

import java.io.File;
import java.io.IOException;


public class MediaRecorderedVoiceUI extends BaseUI {

    private Button btnSpeak;
    private View lySpeaked;
    private TextView tvSpeakLength;
    private ImageView ivSpeakDel;
    protected String duration;
    protected String voicePath;
    protected String mLen;
    private MediaUtil mMediaUtil;
    private Builder dialog;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tvSpeakLength:
                File file = new File(voicePath);
                if (!file.exists()) {
                    return;
                }
                if (mMediaUtil.getMediaplayer().isPlaying()) {
                    mMediaUtil.stopMusic();
                } else {
                    mMediaUtil.playMusic(voicePath);
                }
                break;
            case R.id.ivSpeakDel:
                dialog = new Builder(MediaRecorderedVoiceUI.this);
                dialog.setMessage(R.string.voiceDel);
                dialog.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        File file = new File(voicePath);
                        LogUtils.i("voicePath" + voicePath);
                        if (file.exists()) {
                            if (file.delete()) {
                                btnSpeak.setVisibility(View.VISIBLE);
                                lySpeaked.setVisibility(View.GONE);
                                voicePath = "";
                            }
                        }
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_media_mediarecorder_voice);
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        btnSpeak = (Button) findView(R.id.btnSpeak);
        lySpeaked = (View) findView(R.id.lySpeaked);

        tvSpeakLength = (TextView) findView(R.id.tvSpeakLength);
        ivSpeakDel = (ImageView) findView(R.id.ivSpeakDel);

        ivSpeakDel = (ImageView) findViewById(R.id.ivSpeakDel);
        tvSpeakLength = (TextView) findViewById(R.id.tvSpeakLength);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(this);
        ivSpeakDel.setOnClickListener(this);
        tvSpeakLength.setOnClickListener(this);

        mMediaUtil = MediaUtil.getInstance();
        mMediaUtil.initMediaplayer();
        mMediaUtil.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mMediaUtil.stopMusic();
            }
        });

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        File voiceFile = new File(FileUtils.getArmFile());
        try {
            if (voiceFile.exists()) {
                voiceFile.delete();
            }
            voiceFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LogUtils.e(e);
        }
        voicePath = voiceFile.getAbsolutePath();
        LogUtils.e(voicePath + "---------------");
        SpeakButtonTouchListener touchListener = new SpeakButtonTouchListener(this, lySpeaked, voicePath);
        touchListener.setListener(new SpeakButtonTouchListener.OnActionListener() {
            @Override
            public void onActioncancle() {
            }

            @Override
            public void onActionUp() {
            }

            @Override
            public void onActionDown() {
                mMediaUtil.stopMusic();
            }

            @Override
            public void doSend(String length, String absolutePath) {
                btnSpeak.setVisibility(View.GONE);
                lySpeaked.setVisibility(View.VISIBLE);
                tvSpeakLength.setVisibility(View.VISIBLE);
                duration = length;
                tvSpeakLength.setText(length + "\"");
                mLen = length;
            }
        });
        btnSpeak.setOnTouchListener(touchListener);
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

}
