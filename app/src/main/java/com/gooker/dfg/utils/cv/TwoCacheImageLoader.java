/**
 * 
 */
package com.gooker.dfg.utils.cv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gooker.dfg.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * @author [ShaoCheng Zhang] Sep 5, 2013:3:02:53 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class TwoCacheImageLoader {
	/*
	 * 一级缓存的最大空间
	 */
	// private static final int MAX_MEMORY = (int)
	// (Runtime.getRuntime().maxMemory() / 10);
	private static final int MAX_MEMORY = 10;
	/*
	 * 定时清理缓存
	 */
	private static final long DELAY_BEFFORE_PIRGE = 10 * 1000;

	/*
	 * 一级引用 使用 直接引用的方式 0.75 加载因子
	 */
	private HashMap<String, Bitmap> mFirstLevelCache = new LinkedHashMap<String, Bitmap>(MAX_MEMORY / 2, 0.75f, true) {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc) 当超过一级缓存，就将老的值移动到二级缓存中
		 * 
		 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
		 */
		protected boolean removeEldestEntry(Entry<String, Bitmap> eldest) {
			if (size() > MAX_MEMORY) {
				mSecondLevelCache.put(eldest.getKey(), new SoftReference<Bitmap>(eldest.getValue()));
				return true;
			}

			return false;
		}
	};
	/*
	 * 二级缓存 使用 软引用的方式
	 */
	private HashMap<String, SoftReference<Bitmap>> mSecondLevelCache = new HashMap<String, SoftReference<Bitmap>>(MAX_MEMORY / 2) {

		private static final long serialVersionUID = 1L;

	};
	private Handler mPurgeHandler = new Handler();
	/*
	 * 定时清理
	 */
	private Runnable mClearCache = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			clear();
		}
	};

	/*
	 * 重置缓存清理的 Timer
	 */
	private void resetTimer() {
		mPurgeHandler.removeCallbacks(mClearCache);
		mPurgeHandler.postDelayed(mClearCache, DELAY_BEFFORE_PIRGE);
	}

	/*
	 * 清理缓存
	 */
	private void clear() {
		mFirstLevelCache.clear();
		mSecondLevelCache.clear();
	};

	/**
	 * 从缓存中获取图片，先从 一级缓存中获取，如果没有再从 二级缓存中获取
	 * 
	 * @param url
	 *            图片的url 下载地址 key
	 * @return 图片的引用 or null
	 */
	public Bitmap getBitmapFromCache(String url) {
		Bitmap bitmap = null;
		bitmap = getFromFirstLevelCache(url);
		if (null == bitmap) {
			bitmap = getBitmapFromSecondLevelCache(url);
		}
		return bitmap;
	}

	/**
	 * 从二级缓存中获取 图片，没有则返回 null
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getBitmapFromSecondLevelCache(String url) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		SoftReference<Bitmap> softReference = null;
		softReference = mSecondLevelCache.get(url);
		if (softReference != null) {
			bitmap = softReference.get();
			// 由于内存吃紧，软引用已经被 GC 回收了
			if (null == bitmap) {
				mSecondLevelCache.remove(url);
			}
		}
		return bitmap;
	}

	/**
	 * 从一级缓存中 来获取 图片
	 * 
	 * @param url
	 * @return
	 */
	private Bitmap getFromFirstLevelCache(String url) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		synchronized (mFirstLevelCache) {
			bitmap = mFirstLevelCache.get(url);
			if (null != bitmap) {
				/*
				 * 将最近访问的元素放到来链的头部，提高下次访问的检索速度(LRU 算法)
				 */
				mFirstLevelCache.remove(url);
				mFirstLevelCache.put(url, bitmap);
			}
		}
		return bitmap;
	}

	/**
	 * @param url2
	 * @return
	 */
	private Bitmap downloadImageFromInternet(String imageUrl) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		HttpClient client = AndroidHttpClient.newInstance("Android");
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSocketBufferSize(params, 3000);
		HttpResponse response = null;
		HttpPost post = null;
		HttpEntity entity = null;
		InputStream stream = null;
		try {
			post = new HttpPost(imageUrl);
			response = client.execute(post);
			if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
				if (entity != null) {
					stream = entity.getContent();
					if (stream != null) {
						bitmap = BitmapFactory.decodeStream(stream);

					}
				}

			}
			return bitmap;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (stream != null) {
					stream.close();
					stream = null;
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (entity != null) {
					entity.consumeContent();
					entity = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (post != null) {
					post.abort();
					post = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (client != null) {
					((AndroidHttpClient) client).close();
					client = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return bitmap;
	}

	/**
	 * 加载图片，如果缓存中有就直接从缓存中获取。没有则重新下载
	 * 
	 * @param url
	 * @param adapter
	 * @param holder
	 */
	public void loadImage(String url, BaseAdapter adapter, ImageView imageView) {
		resetTimer();
		Bitmap bitmap = getBitmapFromCache(url);
		if (null == bitmap) {
			imageView.setBackgroundResource(R.drawable.ic_launcher);
			ImageLoadTask imageLoadTask = new ImageLoadTask();
			imageLoadTask.execute(url, adapter, imageView);
		} else {
			imageView.setImageBitmap(bitmap);
		}
	}

	/**
	 * 放入缓存
	 * 
	 * @param url
	 * @param bitmap
	 */
	private void addImage2Cache(String key, Bitmap value) {
		if (null == key || value == null) {
			return;
		}

		synchronized (mFirstLevelCache) {
			mFirstLevelCache.put(key, value);
		}
	}

	class ImageLoadTask extends AsyncTask<Object, Void, Bitmap> {

		private String url;
		private BaseAdapter adapter;

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			url = (String) params[0];
			adapter = (BaseAdapter) params[1];
			Bitmap bitmap = downloadImageFromInternet(url);
			return bitmap;
		}

		protected void onPostExecute(Bitmap result) {
			if (result == null) {
				return;
			}
			/*
			 * 放入缓存
			 */
			addImage2Cache(url, result);
			/*
			 * 出发 getView() 方法执行，
			 */
			adapter.notifyDataSetChanged();
		};

	}

}
