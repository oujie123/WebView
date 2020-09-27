package com.gacrnd.gcs.app;

import android.os.RemoteException;
import android.util.Log;

import com.gacrnd.gcs.base.autoservice.MyServiceLoader;
import com.gacrnd.gcs.common.autoservice.IUserCenterService;
import com.gacrnd.gcs.common.eventbus.LoginEvent;
import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;
import com.gacrnd.gcs.webview.IWebViewToMainProcessorInterface;
import com.gacrnd.gcs.webview.command.ICommand;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
@AutoService(ICommand.class)
public class CommandLogin implements ICommand {

    private static final String TAG = CommandLogin.class.getSimpleName();
    IUserCenterService userCenterService = MyServiceLoader.load(IUserCenterService.class);
    private IMainProcessorToWebViewCallBack callBack;
    private String callbacknameFromNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map params, IMainProcessorToWebViewCallBack callBack) {
        if (userCenterService != null && !userCenterService.isLogin()) {
            userCenterService.login();
            this.callBack = callBack;
            callbacknameFromNativeJs = params.get("callbackname").toString();
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.e(TAG, "eventName:" + event.userName);
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        if (this.callBack != null) {
            try {
                this.callBack.onResult(callbacknameFromNativeJs, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
