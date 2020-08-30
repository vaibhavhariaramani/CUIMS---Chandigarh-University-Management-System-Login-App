package com.example.cuimsbyvaibhavhariramani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    public WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView)findViewById(R.id.wv);
        wv.requestFocus();
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        wv.getSettings().setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");
        wv.loadUrl("https://uims.cuchd.in");

        //wv.loadUrl("javascript:document.getElementsByName('UserId').value = 'UserId'");
        //wv.loadUrl("javascript:document.getElementsByName('Password').value = 'Password'");
        //wv.loadUrl("javascript:document.forms['Login'].submit()");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
            Toast.makeText(this, "Going back ", Toast.LENGTH_SHORT).show();
        } else if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            Toast.makeText(this, "Exiting ", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "press back again to exit ", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}