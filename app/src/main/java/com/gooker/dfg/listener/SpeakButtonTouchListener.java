package com.gooker.dfg.listener;

import java.io.File;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaRecorder;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.listener.*;
import com.aoeng.degu.listener.RepeatedClickHandler;
import com.aoeng.degu.utils.common.StringUtils;

/**
 * 处理录音说话按钮的触摸事件的监听器
 */
public class SpeakButtonTouchListener implements OnTouchListener {
	private View parent;
	private String absolutePath;
	private long startTime;
	private OnActionListener listener;
	private MediaRecorder mMediaRecorder;
	private Context context;
	private ImageView iv_voice_rcd;
	private PopupWindow voiceRecordPopupWindow;
	private ObtainDecibelThread thread;
	private com.aoeng.degu.listener.RepeatedClickHandler repeatedClickHandler;
	private static final int MIN_INTERVAL_TIME = 2000;// 2s
	private static final int MAX_INTERVAL_TIME = 1000 * 60 * 5;// 5分钟
	private static int[] res = { R.drawable.amp1, R.drawable.amp2, R.drawable.amp3, R.drawable.amp4, R.drawable.amp5, R.drawable.amp6, R.drawable.amp7 };

	public SpeakButtonTouchListener(Context context, View parent, String viocePath) {
		this.parent = parent;
		this.context = context;
		this.absolutePath = viocePath;
	}

	public OnActionListener getListener() {
		return listener;
	}

	public void setListener(OnActionListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 按下
			if (listener != null)
				listener.onActionDown();
			startTime = System.currentTimeMillis();

			try {
				File file = new File(absolutePath);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
				showVoiceRecordPopupWindow(parent);
				mMediaRecorder = new MediaRecorder();
				mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);// 设置麦克风
				mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
				mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				mMediaRecorder.setOutputFile(file.getAbsolutePath());
				mMediaRecorder.prepare();
				mMediaRecorder.start();
				thread = new ObtainDecibelThread();
				thread.start();

			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		case MotionEvent.ACTION_CANCEL:// 当手指移动到view外面，会cancel
			if (thread != null) {
				thread.exit();
				thread = null;
			}
			// parent.setBackgroundResource(R.drawable.btn_style_two_normal);
			dismissVoiceRecordPopupWindow();
			if (mMediaRecorder != null) {
				mMediaRecorder.stop();
				mMediaRecorder.release();
				mMediaRecorder = null;
			}
			// 删除该录音文件
			try {
				if (!StringUtils.isEmpty(absolutePath)) {
					File file = new File(absolutePath);
					if (file.exists()) {
						file.delete();
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			showToast("录音失败，请按住说话");
			break;
		case MotionEvent.ACTION_UP:// 抬起
			if (thread != null) {
				thread.exit();
				thread = null;
			}
			// parent.setBackgroundResource(R.drawable.btn_style_two_normal);
			dismissVoiceRecordPopupWindow();
			if (mMediaRecorder != null) {

				mMediaRecorder.stop();
				mMediaRecorder.release();
				mMediaRecorder = null;
			}
			long intervalTime = System.currentTimeMillis() - startTime;
			if (intervalTime < MIN_INTERVAL_TIME) {
				try {
					showToast("录音失败，录音时间太短！");
					if (!StringUtils.isEmpty(absolutePath)) {
						File file = new File(absolutePath);
						if (file.exists()) {
							file.delete();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (intervalTime > MAX_INTERVAL_TIME) {
				try {
					showToast("录音失败，录音时间不能超过5分钟");
					if (!StringUtils.isEmpty(absolutePath)) {
						File file = new File(absolutePath);
						if (file.exists()) {
							file.delete();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				if (listener != null)
					listener.doSend(String.valueOf(intervalTime / 1000), absolutePath);
			}
			if (repeatedClickHandler == null)
				repeatedClickHandler = new RepeatedClickHandler();
			repeatedClickHandler.handleRepeatedTouch(v);
			break;
		}
		return true;
	}

	public interface OnActionListener {
		void onActionDown();

		void onActionUp();

		void onActioncancle();

		void doSend(String length, String absolutePath);
	}

	/**
	 * 显示录音的popupWindow
	 * 
	 * @param parent
	 */
	protected void showVoiceRecordPopupWindow(View parent) {
		View popupWindow_view = LayoutInflater.from(context).inflate(R.layout.popup_window_voice_record, null, false);
		iv_voice_rcd = (ImageView) popupWindow_view.findViewById(R.id.iv_voice_rcd);
		voiceRecordPopupWindow = new PopupWindow(popupWindow_view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		voiceRecordPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		voiceRecordPopupWindow.setOutsideTouchable(false);
		voiceRecordPopupWindow.setFocusable(false);
		voiceRecordPopupWindow.setTouchable(true);
		voiceRecordPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	/**
	 * 消失录音的popupWindow
	 * 
	 * @param parent
	 */
	protected void dismissVoiceRecordPopupWindow() {
		if (null != voiceRecordPopupWindow) {
			voiceRecordPopupWindow.dismiss();
		}
	}

	private class ObtainDecibelThread extends Thread {

		private volatile boolean running = true;

		public void exit() {
			running = false;
		}

		@Override
		public void run() {
			while (running) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (mMediaRecorder == null || !running) {
					break;
				}
				int x = mMediaRecorder.getMaxAmplitude();
				if (x != 0) {
					int f = (int) (10 * Math.log(x) / Math.log(10));
					if (f < 26)
						volumeHandler.sendEmptyMessage(0);
					else if (f < 30)
						volumeHandler.sendEmptyMessage(1);
					else if (f < 34)
						volumeHandler.sendEmptyMessage(2);
					else if (f < 38)
						volumeHandler.sendEmptyMessage(3);
					else if (f < 42)
						volumeHandler.sendEmptyMessage(4);
					else if (f < 46)
						volumeHandler.sendEmptyMessage(5);
					else
						volumeHandler.sendEmptyMessage(6);
				}
			}
		}
	}

	Handler volumeHandler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (iv_voice_rcd != null) {
				iv_voice_rcd.setImageResource(res[msg.what]);
			}
		}
	};

	private void showToast(String str) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
}