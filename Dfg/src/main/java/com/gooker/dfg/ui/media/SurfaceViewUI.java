/**
 *
 */
package com.gooker.dfg.ui.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

import com.gooker.dfg.R;

import java.io.File;
import java.io.IOException;


/**
 * Jun 9, 2014 3:31:00 PM
 */
public class SurfaceViewUI extends Activity implements Callback {
    static final String TAG = "SURFACEVIEWUI";

    private MediaPlayer mediaPlayer;

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // When the surface is created, assign it as the
            // display surface and assign and prepare a data
            // source.
            File file = new File("/sdcard/Download/media/test.mp4");
            if (!file.exists()) {
                log("file is not exists");
            } else {
                log("file is ok");
            }
            mediaPlayer.setDisplay(holder);
            mediaPlayer.setDataSource("/sdcard/Download/media/test.mp4");
            mediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Illegal Argument Exception", e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Illegal State Exception", e);
        } catch (SecurityException e) {
            Log.e(TAG, "Security Exception", e);
        } catch (IOException e) {
            Log.e(TAG, "IO Exception", e);
        }
    }

    /**
     * @param string
     */
    private void log(String string) {
        // TODO Auto-generated method stub
        com.gooker.dfg.utils.common.Logger.i(TAG, string);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.release();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_media_surface);

        // Create a new Media Player.
        mediaPlayer = new MediaPlayer();

        // Get a reference to the Surface View.
        final SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        // Configure the Surface View.
        surfaceView.setKeepScreenOn(true);

        // Configure the Surface Holder and register the callback.
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.setFixedSize(400, 300);

        // Connect a play button.
        Button playButton = (Button) findViewById(R.id.buttonPlay);
        playButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        // Connect a pause button.
        Button pauseButton = (Button) findViewById(R.id.buttonPause);
        pauseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        // Add a skip button.
        Button skipButton = (Button) findViewById(R.id.buttonSkip);
        skipButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getDuration() / 2);
            }
        });

        /**
         * Listing 15-5: Controlling playback using the Media Controller
         */
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(new MediaPlayerControl() {

            @Override
            public void start() {
                // TODO Auto-generated method stub
                mediaPlayer.start();
            }

            @Override
            public void seekTo(int pos) {
                // TODO Auto-generated method stub
                mediaPlayer.seekTo(pos);
            }

            @Override
            public void pause() {
                // TODO Auto-generated method stub
                mediaPlayer.pause();
            }

            @Override
            public boolean isPlaying() {
                // TODO Auto-generated method stub
                return mediaPlayer.isPlaying();
            }

            @Override
            public int getDuration() {
                // TODO Auto-generated method stub
                return mediaPlayer.getDuration();
            }

            @Override
            public int getCurrentPosition() {
                // TODO Auto-generated method stub
                return mediaPlayer.getCurrentPosition();
            }

            @Override
            public int getBufferPercentage() {
                // TODO Auto-generated method stub
                return 0;
            }

            public int getAudioSessionId() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public boolean canSeekForward() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean canSeekBackward() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean canPause() {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }
}