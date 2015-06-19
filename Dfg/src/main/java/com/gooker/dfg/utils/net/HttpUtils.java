/**
 *
 */
package com.gooker.dfg.utils.net;


import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author [ShaoCheng Zhang] Oct 15, 2013:4:28:27 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class HttpUtils {
    private static HttpClient client = new DefaultHttpClient();
    private String reqUrl = "";

    /**
     * HttpPost HttpClient FutureTask
     *
     * @param url       请求　url
     * @param rawParams 服务器响应字符串
     * @return
     * @throws Exception
     */
    public static String postRequest(final String url, final Map<String, String> rawParams) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                HttpPost post = new HttpPost(url);
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                for (String key : rawParams.keySet()) {
                    pairs.add(new BasicNameValuePair(key, rawParams.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(pairs, "gbk"));
                HttpResponse response = client.execute(post);
                if (null != response && response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    String result = EntityUtils.toString(response.getEntity());
                    return result;
                }
                return null;
            }
        });

        new Thread(task).start();
        return task.get();
    }

    /**
     * HttpGet HttpClient FutureTask
     *
     * @param url       请求　url
     * @param rawParams 服务器响应字符串
     * @return
     * @throws Exception
     */
    public static String getRequest(final String url) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                HttpGet post = new HttpGet(url);
                HttpResponse response = client.execute(post);
                if (null != response && response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    String result = EntityUtils.toString(response.getEntity());
                    return result;
                }
                return null;
            }
        });

        new Thread(task).start();
        return task.get();
    }

    /**
     * @return
     */
    public static HttpClient getHttpsClient() {
        // TODO Auto-generated method stub
        HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        SchemeRegistry registry = new SchemeRegistry();
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
        registry.register(new Scheme("https", socketFactory, 443));
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        DefaultHttpClient client = new DefaultHttpClient();
        SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
        DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        return httpClient;
    }

    /**
     * @return
     * @throws Exception
     */
    public static HttpClient getHttpsClient2() throws Exception {
        HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        SchemeRegistry registry = new SchemeRegistry();
        // SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        // KeyStore trustStore =
        // KeyStore.getInstance(KeyStore.getDefaultType());
        KeyStore trustStore = KeyStore.getInstance("BKS");
        trustStore.load(null, null);
        SSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
        socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
        registry.register(new Scheme("https", socketFactory, 443));
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        DefaultHttpClient client = new DefaultHttpClient();
        SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
        DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        return httpClient;
    }

    public static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
