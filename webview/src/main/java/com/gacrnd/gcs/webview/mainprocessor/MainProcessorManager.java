package com.gacrnd.gcs.webview.mainprocessor;

import android.os.RemoteException;
import android.util.Log;

import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;
import com.gacrnd.gcs.webview.IWebViewToMainProcessorInterface;
import com.gacrnd.gcs.webview.command.ICommand;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
public class MainProcessorManager extends IWebViewToMainProcessorInterface.Stub {

    private static final String TAG = MainProcessorManager.class.getSimpleName();
    private static volatile MainProcessorManager mInstance;
    //通过AutoService将Command加入列表。不用register方法
    private HashMap<String, ICommand> mCommand = new HashMap<>();

    public static MainProcessorManager getInstance() {
        if (mInstance == null) {
            synchronized (MainProcessorManager.class) {
                if (mInstance == null) {
                    mInstance = new MainProcessorManager();
                }
            }
        }
        return mInstance;
    }

    private MainProcessorManager() {
        ServiceLoader<ICommand> serviceLoader = ServiceLoader.load(ICommand.class);
        for (ICommand command : serviceLoader) {
            if (!mCommand.containsKey(command.name())) {
                mCommand.put(command.name(), command);
            }
        }
    }

    private void excuteCommand(String commandName, Map params, IMainProcessorToWebViewCallBack callBack) {
        mCommand.get(commandName).execute(params, callBack);
    }

    @Override
    public void handleWebViewCommand(String commandName, String jsonParams, IMainProcessorToWebViewCallBack callBack) throws RemoteException {
        excuteCommand(commandName, new Gson().fromJson(jsonParams, Map.class), callBack);
    }
}
