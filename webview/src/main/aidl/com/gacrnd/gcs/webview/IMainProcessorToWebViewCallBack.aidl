// IMainProcessorToWebViewCallBack.aidl
package com.gacrnd.gcs.webview;

// Declare any non-default types here with import statements

interface IMainProcessorToWebViewCallBack {
    void onResult(String commandName,String result);
}
