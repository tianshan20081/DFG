/**
 *
 */
package com.gooker.dfg.ui.cv;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.cv.BigImageLoadingDialog;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


/**
 * @author [ShaoCheng Zhang] Sep 8, 2013:2:06:07 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class BigImageShowerUI extends Activity {

    private BigImageLoadingDialog imageLoadingDialog;
    private ImageView ivImageBig;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_cv_bigimage_shower);

        ivImageBig = (ImageView) this.findViewById(R.id.ivImageBig);

        imageLoadingDialog = new BigImageLoadingDialog(BigImageShowerUI.this);
        imageLoadingDialog.show();

        new AsyncTask<Void, Void, Void>() {

            private Bitmap bitmap;
            private InputStream stream;

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                HttpClient client = new DefaultHttpClient();
                String uri = "http://img.my.csdn.net/uploads/201309/08/1378614857_1788.png";
                HttpPost post = new HttpPost(uri);
                try {
                    HttpResponse response = client.execute(post);
                    if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        stream = response.getEntity().getContent();
                        bitmap = BitmapFactory.decodeStream(stream);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                            stream = null;
                        }
                    } catch (Exception e2) {
                        // TODO: handle exception
                    }
                }

                return null;
            }

            /*
             * (non-Javadoc)
             *
             * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
             */
            @Override
            protected void onPostExecute(Void result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                imageLoadingDialog.dismiss();
                if (null != bitmap) {
                    ivImageBig.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(BigImageShowerUI.this, "图片下载失败", Toast.LENGTH_LONG).show();
                }

            }
        }.execute();

        // new Handler().postDelayed(new Runnable() {
        //
        // @Override
        // public void run() {
        // // TODO Auto-generated method stub
        // imageLoadingDialog.dismiss();
        // }
        // }, 1000 * 2);

    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        finish();
        return true;
    }
}
