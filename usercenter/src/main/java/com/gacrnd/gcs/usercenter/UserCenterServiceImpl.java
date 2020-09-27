package com.gacrnd.gcs.usercenter;

import android.content.Intent;

import com.gacrnd.gcs.base.BaseApplication;
import com.gacrnd.gcs.common.autoservice.IUserCenterService;
import com.google.auto.service.AutoService;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
@AutoService(IUserCenterService.class)
public class UserCenterServiceImpl implements IUserCenterService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
