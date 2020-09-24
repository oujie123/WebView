package com.gacrnd.gcs.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

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

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG,consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
