package com.gacrnd.gcs.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.gacrnd.gcs.webview.WebChromeClientCallBack;
import com.gacrnd.gcs.webview.WebViewCallBack;
import com.gacrnd.gcs.webview.bean.JsParams;
import com.gacrnd.gcs.webview.webviewprocess.settings.WebDefaultSettings;
import com.gacrnd.gcs.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.gacrnd.gcs.webview.webviewprocess.webviewclient.MyWebViewClient;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @author Jack_Ou  created on 2020/9/24.
 */
public class BaseWebView extends WebView {

    private static final String TAG = BaseWebView.class.getSimpleName();
    private Gson gson;

    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "gacwebview");
        gson = new Gson();
    }

    public void registerWebViewCallBack(WebViewCallBack callBack, WebChromeClientCallBack chromeClientCallBack){
        setWebViewClient(new MyWebViewClient(callBack));
        setWebChromeClient(new MyWebChromeClient(chromeClientCallBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam){
        Log.e(TAG,jsParam);
        if (!TextUtils.isEmpty(jsParam)){
            final JsParams jsParams = gson.fromJson(jsParam,JsParams.class);
            if (jsParam != null){
                if ("showToast".equalsIgnoreCase(jsParams.getName())){
                    Toast.makeText(getContext(),String.valueOf(gson.fromJson(jsParams.getParam(), Map.class).get("message")),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
