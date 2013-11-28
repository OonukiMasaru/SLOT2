package jp.techbits.android.slot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class _Browser extends Activity {
    private WebView varWebView;
    @SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        
        varWebView = (WebView)findViewById(R.id.webView);

        varWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
              return false;
            }
          });
        
        WebSettings setting = varWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setPluginsEnabled(true);
//        setting.setPluginState(WebSettings.PluginState.ON);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) { 
//        	varWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        } else { 
//        	varWebView.getSettings().setPluginsEnabled(true);
//        }
        
        if (android.os.Build.VERSION.SDK_INT  > 10){
        	getWindow().setFlags(0x01000000,0x01000000);
        }
//        getWindow().setFlags(
//        	    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
//        	   ,WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
//        	);        
        Intent intent = getIntent();
        CharSequence url = intent.getCharSequenceExtra("String Value");
        
    	varWebView.loadUrl(url.toString());
        }
}

