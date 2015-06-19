package com.gooker.dfg.services;

import android.content.Context;

import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.http.HttpEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public abstract class DownloadResponseHandler extends FileAsyncHttpResponseHandler {
    private boolean flag;

    public DownloadResponseHandler(Context context) {
        super(context);
    }

    public DownloadResponseHandler(File file) {
        super(file);
    }

    @Override
    protected byte[] getResponseData(HttpEntity entity) throws IOException {
        flag = true;

        if (entity != null) {
            InputStream instream = entity.getContent();
            long contentLength = entity.getContentLength();
            FileOutputStream buffer = new FileOutputStream(getTargetFile(), true);
            if (instream != null) {
                try {
                    byte[] tmp = new byte[1024 * 100];
                    int l, count = 0;
                    while ((l = instream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {
                        if (!flag)
                            break;
                        count += l;
                        buffer.write(tmp, 0, l);
                        sendProgressMessage(count, (int) contentLength);
                    }
                } finally {
                    instream.close();
                    buffer.flush();
                    buffer.close();
                }
            }
        }
        return null;
    }

    public void stop() {
        flag = false;
    }

}
