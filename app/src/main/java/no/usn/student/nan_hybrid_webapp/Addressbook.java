package no.usn.student.nan_hybrid_webapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;

import android.support.v4.app.ActivityCompat;
import android.Manifest;

public class Addressbook extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActivityCompat.requestPermissions((Activity) this, new String[] {Manifest.permission.READ_CONTACTS}, 1);

        WebView view = new WebView(this);
        setContentView(view);

        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setAllowFileAccessFromFileURLs(true);
        view.getSettings().setAllowUniversalAccessFromFileURLs(true);
        view.getSettings().setBuiltInZoomControls(false);
        view.addJavascriptInterface(new WebAppInterface(this), "Android");
        view.loadUrl("file:///android_asset/index.html");
    }
}
