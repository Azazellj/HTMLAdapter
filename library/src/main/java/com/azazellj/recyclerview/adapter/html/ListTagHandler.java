package com.azazellj.recyclerview.adapter.html;

import android.text.Editable;
import android.text.Html;

import com.azazellj.recyclerview.adapter.html.base.HtmlScheme;

import org.xml.sax.XMLReader;

import java.util.Locale;

/**
 * Created by azazellj on 2/28/19.
 */
public final class ListTagHandler implements Html.TagHandler {
    private boolean isOrderedList;
    private boolean isUnorderedList;
    private int orderedIndex = 1;

    @Override
    public void handleTag(final boolean opening, final String tag, final Editable output, final XMLReader xmlReader) {
        if (tag.equals(HtmlScheme.UL.getTag()) && opening) {
            isUnorderedList = true;
        }
        if (tag.equals(HtmlScheme.OL.getTag()) && opening) {
            isOrderedList = true;
        }
        if (tag.equals(HtmlScheme.UL.getTag()) && !opening) {
            isUnorderedList = false;
            output.append("\n");
        }
        if (tag.equals(HtmlScheme.OL.getTag()) && !opening) {
            isOrderedList = false;
            orderedIndex = 1;
            output.append("\n");
        }
        if (tag.equals("li") && opening && isUnorderedList) {
            output.append("\n\t• ");
        }
        if (tag.equals("li") && opening && isOrderedList) {
            output.append(String.format(Locale.getDefault(), "%n\t%d. ", orderedIndex));
        }
        if (tag.equals("li") && !opening && isOrderedList) {
            orderedIndex++;
        }
    }
}