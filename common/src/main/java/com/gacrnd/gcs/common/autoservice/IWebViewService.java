package com.gacrnd.gcs.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * @author Jack_Ou  created on 2020/9/23.
 */
public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);

    Fragment getWebViewFragment(String url,boolean canAutoRefresh);
}
