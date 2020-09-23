package com.gacrnd.gcs.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.gacrnd.gcs.base.loadsir.ErrorCallback;
import com.gacrnd.gcs.base.loadsir.LoadingCallback;
import com.gacrnd.gcs.webview.databinding.FragmentWebviewBinding;
import com.gacrnd.gcs.webview.utils.Constants;
import com.gacrnd.gcs.webview.webviewclient.MyWebViewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/23 22:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/23 22:18
 * @UpdateRemark: 更新说明
 */
public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {

    private static final String TAG = WebViewFragment.class.getSimpleName();
    private String mUrl;
    private FragmentWebviewBinding binding;
    private LoadService loadSir;
    private boolean canAutoRefresh;
    private boolean isError = false;

    public static WebViewFragment newInstance(String url,boolean canAutoRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_URL, url);
        bundle.putBoolean(Constants.KEY_AUTO_REFRESH,canAutoRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.KEY_URL);
            canAutoRefresh = bundle.getBoolean(Constants.KEY_AUTO_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.loadUrl(mUrl);
        binding.webview.setWebViewClient(new MyWebViewClient(this));
        //初始化loadsir
        loadSir = LoadSir.getDefault().register(binding.smartRefreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadSir.showCallback(LoadingCallback.class);
                binding.webview.reload();
            }
        });

        //关闭加载更多功能
        binding.smartRefreshLayout.setEnableLoadMore(false);
        binding.smartRefreshLayout.setEnableRefresh(canAutoRefresh);
        binding.smartRefreshLayout.setOnRefreshListener(this);

        //此处应该按照LoadSir的使用方法来，返回loadSir.getLoadLayout()
        return loadSir.getLoadLayout();
    }


    @Override
    public void pageStarted(String url) {
        if (loadSir != null) {
            loadSir.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if (loadSir != null) {
            if (!isError){
                binding.smartRefreshLayout.setEnableRefresh(canAutoRefresh);
                loadSir.showSuccess();
            } else {
                loadSir.showCallback(ErrorCallback.class);
                binding.smartRefreshLayout.setEnableRefresh(true);
            }
            binding.smartRefreshLayout.finishRefresh();
            isError = false;
        }
    }

    @Override
    public void onError() {
        isError = true;
        binding.smartRefreshLayout.finishRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        binding.webview.reload();
    }


}
