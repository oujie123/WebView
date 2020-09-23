package com.gacrnd.gcs.webview;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/23 23:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 23:57
 * @UpdateRemark: 更新说明
 */
public interface WebViewCallBack {
    void pageStarted(String url);
    void pageFinished(String url);
    void onError();
}
