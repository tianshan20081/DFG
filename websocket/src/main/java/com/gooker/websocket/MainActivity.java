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


public class MainActivity extends Activity implements View.OnClickListener {

    private String TAG = " MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.conn).setOnClickListener(this);
        findViewById(R.id.httpConn).setOnClickListener(this);


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
                    Log.e(TAG, "onCompleted----->ex != null"+ ex.toString());

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
}
