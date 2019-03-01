package com.azazellj.recyclerview.adapter.html;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.azazellj.recyclerview.adapter.html.base.HtmlScheme;
import com.azazellj.recyclerview.adapter.html.base.TextGravity;
import com.azazellj.recyclerview.adapter.html.util.HtmlTool;

import org.jsoup.nodes.Element;

/**
 * Created by azazellj on 2/28/19.
 */
public class TextViewHolder {

    public static void initTextView(Element element, TextView textView, OnLinkClickListener listener) {
        initHTMLText(element, textView, listener);
        initTextGravity(element, textView);
        initTextGravity(element, textView);
    }

    public static void initTextGravity(Element element, TextView textView) {
        String style = element.attributes().get("style");

        if (style.contains(TextGravity.CENTER.getName())) {
            textView.setGravity(Gravity.CENTER);
        }

        boolean isEnd = style.contains(TextGravity.END.getName());
        boolean isRight = style.contains(TextGravity.END.getName());

        if (isEnd || isRight) {
            textView.setGravity(Gravity.END);
        }
    }

    public static void initHTMLText(Element element, TextView textView, final OnLinkClickListener listener) {
        boolean isUL = HtmlTool.isElement(element, HtmlScheme.UL);
        boolean isOL = HtmlTool.isElement(element, HtmlScheme.OL);

        String html = isUL || isOL ? element.outerHtml() : element.html();

        CharSequence sequence = Html.fromHtml(html, null, new ListTagHandler());
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span, listener);
        }
        textView.setText(strBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span, final OnLinkClickListener listener) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                listener.onLinkClick(span.getURL());
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }
}
