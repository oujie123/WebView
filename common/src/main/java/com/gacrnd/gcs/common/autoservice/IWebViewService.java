package com.gacrnd.gcs.common.autoservice;

import android.content.Context;

/**
 * @author Jack_Ou  created on 2020/9/23.
 */
public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);
}
