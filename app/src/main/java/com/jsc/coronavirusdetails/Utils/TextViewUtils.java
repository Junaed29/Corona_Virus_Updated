package com.jsc.coronavirusdetails.Utils;

import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.widget.TextView;

public class TextViewUtils {
    private static final String TAG = TextViewUtils.class.getSimpleName();

    private static class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    // http://stackoverflow.com/questions/4096851/remove-underline-from-links-in-textview-android
    public static void removeUnderlines(TextView textView) {
        Spannable p_Text = (Spannable) textView.getText();
        if (p_Text != null && p_Text.toString().length() > 0) {
            URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);

            for (URLSpan span : spans) {
                int start = p_Text.getSpanStart(span);
                int end = p_Text.getSpanEnd(span);
                p_Text.removeSpan(span);
                span = new com.jsc.coronavirusdetails.Utils.TextViewUtils.URLSpanNoUnderline(span.getURL());
                p_Text.setSpan(span, start, end, 0);
            }
        }
    }
}