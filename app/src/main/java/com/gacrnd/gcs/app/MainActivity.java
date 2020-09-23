package com.gacrnd.gcs.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gacrnd.gcs.app.databinding.ActivityMainBinding;
import com.gacrnd.gcs.base.autoservice.MyServiceLoader;
import com.gacrnd.gcs.common.autoservice.IWebViewService;


/**
 * app      app        app
 * 组件(module1)  组件(module2)
 * common (所有程序员可以修改的)
 * network层
 * base (结构师可以修改)
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不止一个类实现了load中的接口，所以要用迭代器
                IWebViewService webViewService = MyServiceLoader.load(IWebViewService.class);
                if (webViewService != null) {
                    webViewService.startWebViewActivity(MainActivity.this, "https://www.baidu.com", "百度", false);
                }
            }
        });
    }
}
