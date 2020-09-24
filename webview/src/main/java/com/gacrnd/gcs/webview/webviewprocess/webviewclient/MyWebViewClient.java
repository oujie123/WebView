package com.gacrnd.gcs.webview.webviewprocess.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gacrnd.gcs.webview.WebViewCallBack;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/23 23:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 23:43
 * @UpdateRemark: 更新说明
 */
public class MyWebViewClient extends WebViewClient {

    private static final String TAG = MyWebViewClient.class.getSimpleName();
    private WebViewCallBack callBack;

    public MyWebViewClient(WebViewCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (callBack != null) {
            callBack.pageStarted(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null!");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (callBack != null) {
            callBack.pageFinished(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null!");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (callBack != null) {
            callBack.onError();
        } else {
            Log.e(TAG, "WebViewCallBack is null!");
        }
    }
}
