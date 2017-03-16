package no.usn.student.nan_hybrid_webapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;

public class Addressbook extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        WebView view = new WebView(this);
        setContentView(view);

        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(false);
        view.addJavascriptInterface(new WebAppInterface(this), "Android");
        view.loadUrl("file:///android_asset/index.html");
    }
}
