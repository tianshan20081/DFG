/**
 * 
 */
package com.gooker.dfg.ui.media;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.Logger;

/**
 * Jun 9, 2014 3:08:18 PM
 * 
 */
public class VideoViewUI extends Activity {
	private static final String TAG = com.aoeng.degu.ui.media.VideoViewUI.class.getName();
	private VideoView videoView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_media_videoview);
		congigureVideoView();

	}

	/**
	 * 
	 */
	private void congigureVideoView() {
		// TODO Auto-generated method stub
		log("congigureVideoView()");
		videoView = (VideoView) this.findViewById(R.id.videoView);
		videoView.setKeepScreenOn(true);
		videoView.setVideoPath("/sdcard/Download/media/test.mp4");

		MediaController controller = new MediaController(this);
		videoView.setMediaController(controller);
	}

	/**
	 * @param string
	 */
	private void log(String string) {
		// TODO Auto-generated method stub
		Logger.i(TAG, string);
	}

}
