/**
 *
 */
package com.gooker.dfg.ui.wv;

import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.views.ProgressBarWebview;


/**
 * @author sczhang 2014年12月6日 下午3:57:30
 * @Email {zhangshch0131@126.com}
 */
public class PbWebViewUI extends BaseUI {

    private ProgressBarWebview pbarWebview;

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_wv_pbar);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        pbarWebview = (ProgressBarWebview) findViewById(R.id.wbDetail);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#processLogic()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
        String url = "";
        WebSettings settings = pbarWebview.getSettings();
        // 添加对 JavaScript 的支持
        settings.setJavaScriptEnabled(true);
        // 添加 放大缩小控件
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setDefaultZoom(ZoomDensity.CLOSE);
        // 初始时候页面总宽度为 手机宽度。
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        if (URLUtil.isHttpsUrl(url)) {
            // 请求链接 为 https 访问时
            pbarWebview.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    // TODO Auto-generated method stub
                    handler.proceed();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    // 当页面加载完毕的时候 加载进度条消失
                    // ViewUtils.dismissDia(dialog);
                }
            });
        } else if (URLUtil.isHttpUrl(url)) {
            // 请求访问为HTTP 访问

        }

        pbarWebview.setWebViewClient(new WebViewClient() {
            /*
             * (non-Javadoc)
             *
             * @see
             * android.webkit.WebViewClient#shouldOverrideUrlLoading(android
             * .webkit.WebView, java.lang.String)
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                pbarWebview.loadUrl(url);
                return true;
            }
        });

        if (URLUtil.isNetworkUrl(url)) {
            pbarWebview.loadUrl(url);
        }
    }

}
