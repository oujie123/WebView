package com.gacrnd.gcs.app;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.gacrnd.gcs.base.BaseApplication;
import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;
import com.gacrnd.gcs.webview.command.ICommand;
import com.google.auto.service.AutoService;

import java.util.Map;


/**
 * @author Jack_Ou  created on 2020/9/25.
 */
@AutoService(ICommand.class)
public class CommandShowToast implements ICommand {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(final Map params, IMainProcessorToWebViewCallBack callBack) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.sApplication, String.valueOf(params.get("message")), Toast.LENGTH_LONG).show();
            }
        });
    }
}
