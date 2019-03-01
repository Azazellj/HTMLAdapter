package com.azazellj.recyclerview.adapter.html.base;

/**
 * Created by azazellj on 2/28/19.
 */
public enum HtmlScheme {

    UL("ul"),
    OL("ol"),
    IMG("img"),
    IFRAME("iframe");

    private String tag;

    HtmlScheme(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}