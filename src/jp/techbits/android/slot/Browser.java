package jp.techbits.android.slot;
   
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
  
public class Browser extends Activity
{
 
    private static WebView webview;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // タイトルにレイアウトを指定する

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.browser);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        webview = (WebView) findViewById(R.id.webView);
        webview.setWebViewClient(new CustomWebViewClient()); 
//        webview = new WebView(this);
//        setContentView(webview);
//        webview.setWebViewClient(new MyWebViewClient() {});
//        webview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//              return false;
//            }
//          });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginsEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);

        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setSupportZoom(true);

        webview.setVerticalScrollbarOverlay(true);
        webview.setHorizontalScrollbarOverlay(true);

//        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
//        if (android.os.Build.VERSION.SDK_INT > 10) {
//          getWindow().setFlags(0x01000000, 0x01000000);
//        }
        
        Intent intent = getIntent();
        CharSequence url = intent.getCharSequenceExtra("String Value");

//        webview.loadUrl("http://aquaman.cocolog-nifty.com/");
      webview.loadUrl(url.toString());
      
    }
 
    class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            setProgressBarIndeterminateVisibility(true);
            setTitle("Loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            setProgressBarIndeterminateVisibility(false);
            setTitle(webview.getTitle());
        }

        @Override
        public void onLoadResource(WebView webview, String url) {
            if (url.startsWith("http://www.youtube.com/get_video_info")) {
                Uri uri = Uri.parse(url);
                Intent varintent = new Intent(Intent.ACTION_VIEW);
                varintent.setData(Uri.parse("vnd.youtube:" + uri.getQueryParameter("video_id")));
                startActivity(varintent);
                webview.stopLoading();
                super.onLoadResource(webview, url);
            }
        }
    
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK  && webview.canGoBack() == true) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}