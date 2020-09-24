package com.gacrnd.gcs.webview.webchromeclient;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.gacrnd.gcs.base.autoservice.MyServiceLoader;
import com.gacrnd.gcs.webview.WebChromeClientCallBack;

/**
 * @author Jack_Ou  created on 2020/9/24.
 */
public class MyWebChromeClient extends WebChromeClient {

    private static final String TAG = MyWebChromeClient.class.getSimpleName();
    private WebChromeClientCallBack mCallback;

    public MyWebChromeClient(WebChromeClientCallBack callBack) {
        this.mCallback = callBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mCallback != null) {
            mCallback.onReceivedTitle(title);
        } else {
            Log.e(TAG,"WebChromeClientCallBack is null !");
        }
    }
}
