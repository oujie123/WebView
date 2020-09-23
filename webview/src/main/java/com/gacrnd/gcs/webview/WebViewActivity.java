package com.gacrnd.gcs.webview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gacrnd.gcs.webview.databinding.ActivityWebviewBinding;
import com.gacrnd.gcs.webview.utils.Constants;


/**
 * @author Jack_Ou  created on 2020/9/23.
 */
public class WebViewActivity extends AppCompatActivity {

    private ActivityWebviewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = null;
        String title = null;
        boolean showActionBar = false;
        if (intent != null) {
            url = intent.getStringExtra(Constants.KEY_URL);
            title = intent.getStringExtra(Constants.KEY_TITLE);
            showActionBar = intent.getBooleanExtra(Constants.KEY_SHOW_ACTION_BAR, true);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        binding.title.setText(title);
        binding.actionBar.setVisibility(showActionBar ? View.VISIBLE : View.GONE);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(url, true);
        fragmentTransaction.replace(R.id.web_view_fragment, fragment).commit();
    }
}
