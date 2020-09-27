package com.gacrnd.gcs.app;

import android.content.ComponentName;
import android.content.Intent;

import com.gacrnd.gcs.base.BaseApplication;
import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;
import com.gacrnd.gcs.webview.command.ICommand;
import com.google.auto.service.AutoService;

import java.util.Map;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
@AutoService(ICommand.class)
public class CommandOpenPage implements ICommand {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map params, IMainProcessorToWebViewCallBack callBack) {
        String targetClass = (String) params.get("target_class");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.sApplication,targetClass));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
