package com.gooker.websocket;

import android.util.Log;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by sczhang on 15/7/6. 下午4:03
 * Package Name com.gooker.websocket
 * Project Name DFG
 */
public class ITrustManager implements X509TrustManager {
    /**
     * Checks whether the specified certificate chain (partial or complete) can
     * be validated and is trusted for client authentication for the specified
     * authentication type.
     *
     * @param chain    the certificate chain to validate.
     * @param authType the authentication type used.
     * @throws CertificateException     if the certificate chain can't be validated or isn't trusted.
     * @throws IllegalArgumentException if the specified certificate chain is empty or {@code null},
     *                                  or if the specified authentication type is {@code null} or an
     *                                  empty string.
     */
    /**
     * 给出同位体提供的部分或完整的证书链，构建到可信任的根的证书路径，
     * 并且返回是否可以确认和信任将其用于基于验证类型的客户端 SSL 验证。
     * 验证类型由实际使用的证书确定。
     * 例如，如果使用 RSAPublicKey，则 authType 应为 "RSA"。检查是否大小写敏感的。
     *
     * @param chain    - 同位体的证书链
     * @param authType - 基于客户端证书的验证类型
     * @throws CertificateException - 如果证书链不受此 TrustManager 信任。
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        Log.d("https_test", "checkClientTrusted authType = " + authType);
        for (X509Certificate certificate : chain) {
            Log.d("https_test", "checkClientTrusted certificate = " + certificate.toString());
        }

    }

    /**
     * Checks whether the specified certificate chain (partial or complete) can
     * be validated and is trusted for server authentication for the specified
     * key exchange algorithm.
     *
     * @param chain    the certificate chain to validate.
     * @param authType the key exchange algorithm name.
     * @throws CertificateException     if the certificate chain can't be validated or isn't trusted.
     * @throws IllegalArgumentException if the specified certificate chain is empty or {@code null},
     *                                  or if the specified authentication type is {@code null} or an
     *                                  empty string.
     */
    /**
     * 给出同位体提供的部分或完整的证书链，构建到可信任的根的证书路径，
     * 并且返回是否可以确认和信任将其用于基于验证类型的服务器 SSL 验证。
     * 验证类型是表示为一个 String 的密码套件的密钥交换算法部分，例如 "RSA"、"DHE_DSS"。
     * <p/>
     * 注：对于一些可输出的密码套件，密钥交换算法是在运行时的联络期间确定的。
     * 例如，对于 TLS_RSA_EXPORT_WITH_RC4_40_MD5，当临时的 RSA 密钥
     * 用于密钥交换时 authType 应为 RSA_EXPORT，当使用来自服务器证书的密钥时 authType
     * 应为 RSA。检查是否大小写敏感的。
     *
     * @param chain    - 同位体的证书链
     * @param authType - 使用的密钥交换算法
     * @throws CertificateException - 如果证书链不受此 TrustManager 信任。
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        Log.d("https_test", "checkServerTrusted authType = " + authType);
        for (X509Certificate certificate : chain) {
            Log.d("https_test", "checkServerTrusted certificate = " + certificate.toString());
        }
    }

    /**
     * Returns the list of certificate issuer authorities which are trusted for
     * authentication of peers.
     *
     * @return the list of certificate issuer authorities which are trusted for
     * authentication of peers.
     */
    /**
     * 返回受验证同位体信任的认证中心的数组。
     *
     * @return 可接受的 CA 发行者证书的非 null（可能为空）的数组。
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }


}
