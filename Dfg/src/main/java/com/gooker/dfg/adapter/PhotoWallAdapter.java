/**
 *
 */
package com.gooker.dfg.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.ImagesURL;
import com.gooker.dfg.utils.common.Toaster;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author [ShaoCheng Zhang] Sep 3, 2013:9:41:22 AM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoWallAdapter extends ArrayAdapter<String> implements OnScrollListener {
    private static final String TAG = PhotoWallAdapter.class.getName().toUpperCase();
    /*
     * 记录所有正在下载或等待下载的任务
     */
    private Set<BitmapWorkerTask> taskCollection;
    /**
     * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会及啊在机场附近一天地图片一处掉
     */
    private LruCache<String, Bitmap> mMemoryCache;
    /*
     * GridView 的实例
     */
    private GridView mPhotoWall;
    /*
     * 第一张可见图片的小标
     */
    private int mFirstVisiableItem;
    /*
     * 一屏幕有多少张图片可见
     */
    private int mVisiableItemCount;
    /*
     * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片
     */
    private boolean isFirstEnter = true;

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     * @param photoWall
     */
    public PhotoWallAdapter(Context context, int textViewResourceId, String[] objects, GridView photoWall) {
        // TODO Auto-generated constructor stub
        super(context, textViewResourceId, objects);
        mPhotoWall = photoWall;
        taskCollection = new HashSet<BitmapWorkerTask>();
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        Toaster.log(TAG, "Runtime.getRuntime().maxMemory()----->" + Runtime.getRuntime().maxMemory());
        int cacheSize = maxMemory / 20;
        // 设置图片缓存大小为程序可用内存的 1/8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            /*
             * (non-Javadoc)
             *
             * @see android.support.v4.util.LruCache#sizeOf(java.lang.Object,
             * java.lang.Object)
             */
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // TODO Auto-generated method stub

                return bitmap.getRowBytes() * bitmap.getHeight();
                // return bitmap.getByteCount();
            }
        };
        mPhotoWall.setOnScrollListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.ArrayAdapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final String url = getItem(position);
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.ui_cv_photowall_item, null);
        }
        final ImageView photo = (ImageView) view.findViewById(R.id.photo);
        // 给 ImageView 设置一个 tag ，保证异步加载图片时不会乱序
        photo.setTag(url);
        setImageView(url, photo);
        return view;
    }

    /**
     * 给 ImageView 设置图片，首先从 LruCache 中取出图片的缓存，设置到 ImageView 上，如果 LruCache
     * 中没有该图片的缓存,就给 ImageView 设置一张默认图片
     * <p/>
     * 图片的 Url 地址，用于作为 LruCache 的键
     *
     * @param photo 用于显示图片的控件
     */
    private void setImageView(String imageUrl, ImageView imageView) {
        // TODO Auto-generated method stub
        Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.ic_launcher);
        }
    }

    /**
     * 将一张图片存储到 LruCache 中
     *
     * @param key    LruCache 的键，这里传入图片的 URL 地址
     * @param bitmap LruCahe 的键 ，这里传入从网络上下载下来的 Bitmap 对象
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        // TODO Auto-generated method stub
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从 LruCache 中获取一张图片，如果不存在就返回 null
     *
     * @param key LruCache 的键 ，这里传入 图片的 url
     * @return 对应键的 bitmap 对象，或者 null
     */
    private Bitmap getBitmapFromMemoryCache(String key) {
        // TODO Auto-generated method stub
        return mMemoryCache.get(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android
     * .widget.AbsListView, int)
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
        // 仅当GridView 静止时候才去下载图片，gridView 滑动时取消所有正在下载的任务
        if (scrollState == SCROLL_STATE_IDLE) {
            loadBitmaps(mFirstVisiableItem, mVisiableItemCount);
        } else {
            cancelAllTasks();
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务
     */
    public void cancelAllTasks() {
        // TODO Auto-generated method stub
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }

    /**
     * 加载 bitmap 对象，此方法会在 LruCache 中检查所有屏幕中可见的 ImageView 的bitmap 对象， 如果发现任何一个
     * ImageView 的bitmap 对象不在缓存中，就会开启异步线程去下载图片
     *
     * @param firstVisiableItem 第一个可见的imageView
     * @param visiableItemCount 屏幕中总共可以显示的元素个数
     */
    private void loadBitmaps(int firstVisiableItem, int visiableItemCount) {
        // TODO Auto-generated method stub
        try {
            for (int i = firstVisiableItem; i < firstVisiableItem + visiableItemCount; i++) {
                String imageUrl = ImagesURL.imageThumbUrls[i];
                Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
                if (bitmap == null) {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    taskCollection.add(task);
                    task.execute(imageUrl);
                } else {
                    ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
                    if (imageView != null && bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.
     * AbsListView, int, int, int)
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        mFirstVisiableItem = firstVisibleItem;
        mVisiableItemCount = totalItemCount;
        // 下载的任务应该由 onScrollStateChanged 里调用，但首次进入程序时 onScollStateChanged 并不会调用
        // 因此在这里为首次进入程序开始下载任务
        if (isFirstEnter && visibleItemCount > 0) {
            loadBitmaps(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * 异步下载图片的任务。
     *
     * @author guolin
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        /**
         * 图片的URL地址
         */
        private String imageUrl;

        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(params[0]);
            if (bitmap != null) {
                // 图片下载完成后缓存到LrcCache中
                addBitmapToMemoryCache(params[0], bitmap);
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
            ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);
        }

        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl 图片的URL地址
         * @return 解析后的Bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                con.setDoInput(true);
                con.setDoOutput(true);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }

}