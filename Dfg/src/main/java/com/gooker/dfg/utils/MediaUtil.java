package com.gooker.dfg.utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.gooker.dfg.utils.common.StringUtils;

import java.io.IOException;

public class MediaUtil {
    private MediaPlayer mediaPlayer;
    private static MediaUtil utils = new MediaUtil();

    private MediaUtil() {
    }

    ;

    public static MediaUtil getInstance() {
        return utils;
    }

    // 初始化播放器
    public void initMediaplayer() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();
    }

    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {// 正在播放
            mediaPlayer.pause();// 暂停
        } else {// 没有播放
            mediaPlayer.start();
        }
    }

    public void destoryMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playMusic(String nativePath) {
        if (StringUtils.isEmpty(nativePath)) {
            return;
        }
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            return;
        }

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(nativePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        mediaPlayer.setOnCompletionListener(listener);
    }

    public MediaPlayer getMediaplayer() {
        return mediaPlayer;
    }
}
