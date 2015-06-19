package com.gooker.dfg.services;

import android.content.Context;
import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class MultiThreadDownloadTask {

    private String url;            // 网络地址
    private File file;            // 本地地址
    private int amount;        // 线程数量
    private Context context;    // 上下文
    private Handler handler;    // 用来发送文件大小和当前进度的Handler

    private long contentLength;    // 文件总大小
    private long threadLength;    // 每个线程负责多少

    private Set<File> fileSet = new TreeSet<File>();
    private Set<DownloadResponseHandler> handlerSet = new HashSet<DownloadResponseHandler>();

    public static final int MESSAGE_WHAT_CONTENT_LENGTH = 0;
    public static final int MESSAGE_WHAT_COMPLETED_LENGTH = 1;

    public MultiThreadDownloadTask(String url, File file, int amount, Context context, Handler handler) {
        this.url = url;
        this.file = file;
        this.amount = amount;
        this.context = context;
        this.handler = handler;
    }

    public void start() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(5000);
        client.head(url, new AsyncHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                calculateLength(headers);    // 获取文件总大小, 计算每个线程负责多少
                startSubthread();            // 开启若干子线程, 下载数据
            }

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    private void calculateLength(Header[] headers) {
        for (Header header : headers)
            if ("Content-Length".equals(header.getName()))
                contentLength = Long.parseLong(header.getValue());    // 从请求头中获取文件总大小

        handler.sendMessage(handler.obtainMessage(MESSAGE_WHAT_CONTENT_LENGTH, contentLength));        // 发送文件大小
        threadLength = (contentLength + amount - 1) / amount;        // 计算每个线程负责多少
    }

    private void startSubthread() {
        for (int i = 0; i < amount; i++) {
            File tempfile = new File(context.getCacheDir(), "temp" + i);    // 指定每个线程要存储的文件路径
            fileSet.add(tempfile);                                            // 把临时文件存入TreeSet

            long begin = i * threadLength + tempfile.length();                // 计算开始位置
            long end = i * threadLength + threadLength - 1;                    // 计算结束位置
            if (begin > end)
                continue;
            System.out.println(i + ": " + begin + ", " + end);

            DownloadResponseHandler responseHandler = new DownloadResponseHandler(tempfile) {
                public void onSuccess(int statusCode, Header[] headers, File file) {
                    System.out.println(file + " 已完成");
                    if (getCompletedLength() == contentLength)        // 判断所有线程下载长度是否和文件大小相等
                        merge();    // 合并文件
                }

                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                    throwable.printStackTrace();
                }

                public void onProgress(int bytesWritten, int totalSize) {
                    handler.sendMessage(handler.obtainMessage(MESSAGE_WHAT_COMPLETED_LENGTH, getCompletedLength()));    // 发送当前进度
                }
            };
            handlerSet.add(responseHandler);    // 装入集合

            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(5000);
            client.addHeader("Range", "bytes=" + begin + "-" + end);        // 设置每个线程的下载范围
            client.get(url, responseHandler);
        }
    }

    private long getCompletedLength() {
        long completedLength = 0;
        for (File tempfile : fileSet)                // 遍历所有临时文件
            completedLength += tempfile.length();    // 统计大小
        return completedLength;
    }

    private void merge() {
        try {
            FileOutputStream out = new FileOutputStream(file);            // 定义输出流指向本地要存储的文件
            for (File tempfile : fileSet) {                                // 遍历所有临时文件
                FileInputStream in = new FileInputStream(tempfile);
                byte[] buffer = new byte[8192];
                int length;
                while ((length = in.read(buffer)) != -1)
                    out.write(buffer, 0, length);                        // 合并文件
                in.close();
                tempfile.delete();                                        // 删除临时文件
            }
            out.close();
            System.out.println(file + " 下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        for (DownloadResponseHandler responseHandler : handlerSet)
            responseHandler.stop();
    }

}