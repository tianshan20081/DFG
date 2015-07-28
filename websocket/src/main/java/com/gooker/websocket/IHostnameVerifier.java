package com.gooker.websocket;

import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by sczhang on 15/7/6. 下午4:02
 * Package Name com.gooker.websocket
 * Project Name DFG
 */
public class IHostnameVerifier implements HostnameVerifier {
    /**
     * Verifies that the specified hostname is allowed within the specified SSL
     * session.
     *
     * @param hostname the hostname.
     * @param session  the SSL session of the connection.
     * @return {@code true} if the specified hostname is allowed, otherwise
     * {@code false}.
     */
    /**
     * 验证主机名和服务器验证方案的匹配是可接受的。
     *
     * (这里我们所有的都接受)
     *
     * @param hostname - 主机名
     * @param session - 到主机的连接上使用的 SSLSession
     *
     * @return 如果主机名是可接受的，则返回 true
     */
    @Override
    public boolean verify(String hostname, SSLSession session) {
        Log.d("https_test", "verify hostname = " + hostname);
        for (String name : session.getValueNames()) {

            Log.d("https_test", "verify session "+ name +" = " + session.getValue(name));
        }
        return true;
    }
}
