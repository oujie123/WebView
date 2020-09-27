package com.gacrnd.gcs.webview.mainprocessor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
public class MainProcessorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return MainProcessorManager.getInstance();
    }
}
