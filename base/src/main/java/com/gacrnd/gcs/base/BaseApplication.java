package com.gacrnd.gcs.base;

import android.app.Application;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/23 23:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 23:03
 * @UpdateRemark: 更新说明
 */
public class BaseApplication extends Application {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
