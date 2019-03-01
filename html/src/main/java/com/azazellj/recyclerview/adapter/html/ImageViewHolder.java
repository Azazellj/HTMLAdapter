package com.azazellj.recyclerview.adapter.html;

import com.azazellj.recyclerview.adapter.html.base.HtmlScheme;
import com.azazellj.recyclerview.adapter.html.base.Protocol;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;

/**
 * Created by azazellj on 2/28/19.
 */
public class ImageViewHolder {
    public static String initImageViewHolder(Element element, String baseURL) {
        Elements attr = element.getElementsByTag(HtmlScheme.IMG.getTag());
        String url = attr.get(0).attributes().get("src");

        boolean hasScheme = url.contains(Protocol.HTTP) || url.contains(Protocol.HTTPS);

        String imgUrl = url;

        if (!hasScheme) {
            imgUrl = String.format("%s%s", baseURL, url);
        }

        return URLDecoder.decode(imgUrl);
    }
}
