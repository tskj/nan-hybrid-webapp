package no.usn.student.nan_hybrid_webapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.view.Gravity;
import android.text.Html;

public class WebAppInterface {

    Context _context;

    WebAppInterface(Context c) {
        _context = c;
    }

    @JavascriptInterface
    public void toastMessage(String toast) {
        Toast t = Toast.makeText(_context, Html.fromHtml(toast), Toast.LENGTH_LONG);
        t.setGravity(Gravity.TOP, 0, 0);
        t.show();
    }
}
