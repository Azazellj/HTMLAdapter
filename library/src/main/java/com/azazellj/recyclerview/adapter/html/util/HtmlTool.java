package com.azazellj.recyclerview.adapter.html.util;

import com.azazellj.recyclerview.adapter.html.base.HtmlScheme;

import org.jsoup.nodes.Element;

/**
 * Created by azazellj on 2/28/19.
 */
public class HtmlTool {

    public static boolean isElement(Element element, HtmlScheme scheme) {
        return element != null && !element.getElementsByTag(scheme.getTag()).isEmpty();
    }
}
