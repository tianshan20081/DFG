package com.gooker.websocket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;


public class MainActivity extends Activity implements View.OnClickListener {

    private String TAG = " MainActivity";
    private int e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.conn).setOnClickListener(this);
        findViewById(R.id.httpConn).setOnClickListener(this);
        findViewById(R.id.HttpsBaiDuIndex).setOnClickListener(this);


    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.conn:
                webScoketClient();
                break;
            case R.id.httpConn:
                httpConn();
                break;
            case R.id.HttpsBaiDuIndex:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        httpsBaiDu();
//                        getHttps();
                        String info = getCaHttps();
                        Log.e(TAG, "HTTPS://WWW.BAIDU.COM------>" + info);
                    }
                }).start();
                break;
        }

    }

    private String getCaHttps() {
        String mUrl = "https://www.baidu.com";
        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = getAssets().open("bd.cer"); // 下载的证书放到项目中的assets目录中
            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
            Certificate cer = cerFactory.generateCertificate(inputStream);
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            keyStore.load(null, null);
            keyStore.setCertificateEntry("trust", cer);

            SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore);
            // Https 默认请求端口 443
            Scheme scheme = new Scheme("https", socketFactory, 443);
            HttpClient mHttpClient = new DefaultHttpClient();
            mHttpClient.getConnectionManager().getSchemeRegistry().register(scheme);

            BufferedReader reader = null;
            try {
                Log.e(TAG, "executeGet is in,murl:" + mUrl);
                HttpGet request = new HttpGet();
                request.setURI(new URI(mUrl));
                HttpResponse response = mHttpClient.execute(request);
                if (response.getStatusLine().getStatusCode() != 200) {
                    request.abort();
                    return result;
                }

                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer buffer = new StringBuffer();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                result = buffer.toString();
                Log.e(TAG, "mUrl=" + mUrl + "\nresult = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void httpsBaiDu() {
//        https://www.baidu.com/
//        String ulr = "http://www.baidu.com";
        String ulr = "https://www.baidu.com:443";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(ulr);
        try {
            HttpResponse execute = client.execute(get);
            if (null != execute) {
                Log.e(TAG, "statusCode---->" + execute.getStatusLine().getStatusCode());
                if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = execute.getEntity();
                    String info = EntityUtils.toString(entity);
                    Log.e(TAG, ulr + "----->" + info);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void httpConn() {

        String url = "http://10.0.1.3:8080/ws/test";
        AsyncHttpGet get = new AsyncHttpGet(url);
        AsyncHttpClient.getDefaultInstance().executeString(get, new AsyncHttpClient.StringCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                Log.e(TAG, result);
            }
        });

    }

    private void webScoketClient() {
        AsyncHttpGet get = new AsyncHttpGet("ws://10.0.1.3:8080/ws/info");

        AsyncHttpClient.getDefaultInstance().websocket(get, null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                Log.e(TAG, "onCompleted");
                if (ex != null) {
                    Log.e(TAG, "onCompleted----->ex != null");
                    Log.e(TAG, "onCompleted----->ex != null" + ex.toString());

                    return;
                }
                webSocket.send("a string");
                webSocket.send(new byte[10]);
                Log.e(TAG, "send message   ok");
                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    public void onStringAvailable(String s) {
                        Log.e(TAG, "I got a string: " + s);

                    }
                });
                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.e(TAG, "I got some bytes!");

                        // note that this data has been read
                        bb.recycle();
                    }

                });
            }
        });
    }

    private void getHttps() {
        String https = "https://www.baidu.com/";
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new ITrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new IHostnameVerifier());
            HttpsURLConnection conn = (HttpsURLConnection) new URL(https).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null)
                sb.append(line);
            Log.e(TAG, "HTTPS://WWW.BAIDU.COM----->" + sb.toString());
        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }
    }
}
