package no.usn.student.nan_hybrid_webapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.view.Gravity;
import android.text.Html;

import android.database.Cursor;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class WebAppInterface {

    Context _context;

    WebAppInterface(Context c) {
        _context = c;
    }

    @JavascriptInterface
    public String listAllContacts() {
        String xml = "<contacts>";

        Cursor k = _context.getContentResolver().query(Contacts.CONTENT_URI, null, null, null, Contacts._ID);
        if (k.getCount() > 0) {
            while (k.moveToNext()) {
                if (!k.getString(k.getColumnIndex(Contacts.HAS_PHONE_NUMBER)).equals("1")) {
                    continue;
                }

                xml += "<contact>";

                xml += "<id>" + k.getString(k.getColumnIndex(Contacts._ID)) + "</id>";
                xml += "<name>" + k.getString(k.getColumnIndex(Contacts.DISPLAY_NAME)) + "</name>";
                xml += "<tlf>" + getPhoneNumbers(k.getString(k.getColumnIndex(Contacts._ID))) + "</tlf>";

                xml += "</contact>";
            }
        }
        k.close();

        xml += "</contacts>";
        return xml;
    }

    public String getPhoneNumbers(String id) {
        String tlfs = "";

        Cursor p = _context.getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = " + id, null, null);
        while (p.moveToNext()) {
            tlfs += p.getString(p.getColumnIndex(Phone.NUMBER)) + "\r\n";
        }
        p.close();

        return tlfs;
    }

    @JavascriptInterface
    public void toastMessage(String toast) {
        Toast t = Toast.makeText(_context, Html.fromHtml(toast), Toast.LENGTH_LONG);
        t.setGravity(Gravity.TOP, 0, 0);
        t.show();
    }
}
