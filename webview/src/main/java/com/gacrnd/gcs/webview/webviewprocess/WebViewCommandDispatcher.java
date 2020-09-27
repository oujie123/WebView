package com.gacrnd.gcs.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.gacrnd.gcs.base.BaseApplication;
import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;
import com.gacrnd.gcs.webview.IWebViewToMainProcessorInterface;
import com.gacrnd.gcs.webview.mainprocessor.MainProcessorService;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
public class WebViewCommandDispatcher {
    private static volatile WebViewCommandDispatcher mInstance;
    private IWebViewToMainProcessorInterface commandProcessor;

    public static WebViewCommandDispatcher getInstance() {
        if (mInstance == null) {
            synchronized (WebViewCommandDispatcher.class) {
                if (mInstance == null) {
                    mInstance = new WebViewCommandDispatcher();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessorService.class);
        BaseApplication.sApplication.bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    public void sendCommand(String command, String params, final BaseWebView webView) {
        if (commandProcessor != null) {
            try {
                commandProcessor.handleWebViewCommand(command, params, new IMainProcessorToWebViewCallBack.Stub() {
                    @Override
                    public void onResult(String commandName, String result) throws RemoteException {
                        Log.e("JacK", "commandName:" + commandName + " result:" + result);
                        webView.handleCallback(commandName, result);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            commandProcessor = IWebViewToMainProcessorInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            commandProcessor = null;
            init();
        }

        @Override
        public void onBindingDied(ComponentName name) {
            commandProcessor = null;
            init();
        }
    };
}
