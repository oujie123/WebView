// IWebViewToMainProcessorInterface.aidl
package com.gacrnd.gcs.webview;

import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;

interface IWebViewToMainProcessorInterface {
    void handleWebViewCommand(String commandName,String jsonParams, in IMainProcessorToWebViewCallBack callback);
}
