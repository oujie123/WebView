package com.gacrnd.gcs.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.gacrnd.gcs.common.autoservice.IWebViewService;
import com.gacrnd.gcs.webview.utils.Constants;
import com.google.auto.service.AutoService;

/**
 * @author Jack_Ou  created on 2020/9/23.
 */
@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.KEY_URL, url);
            intent.putExtra(Constants.KEY_TITLE, title);
            intent.putExtra(Constants.KEY_SHOW_ACTION_BAR, isShowActionBar);
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWebViewFragment(String url,boolean canAutoRefresh) {
        return WebViewFragment.newInstance(url,canAutoRefresh);
    }
}
