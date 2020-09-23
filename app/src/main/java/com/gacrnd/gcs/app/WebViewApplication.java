package com.gacrnd.gcs.app;

import com.gacrnd.gcs.base.BaseApplication;
import com.gacrnd.gcs.base.loadsir.CustomCallback;
import com.gacrnd.gcs.base.loadsir.EmptyCallback;
import com.gacrnd.gcs.base.loadsir.ErrorCallback;
import com.gacrnd.gcs.base.loadsir.LoadingCallback;
import com.gacrnd.gcs.base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/23 23:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 23:05
 * @UpdateRemark: 更新说明
 */
public class WebViewApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
