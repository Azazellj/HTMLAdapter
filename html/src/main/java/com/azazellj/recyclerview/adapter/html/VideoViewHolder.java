package com.azazellj.recyclerview.adapter.html;

import android.widget.ImageView;

import com.azazellj.recyclerview.adapter.html.base.HtmlScheme;
import com.azazellj.recyclerview.adapter.html.base.Protocol;
import com.azazellj.recyclerview.adapter.html.util.URLs;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by azazellj on 2/28/19.
 */
public class VideoViewHolder {

    public static void initVideoViewHolder(Element element, ImageView imageView, String baseURL) {
        Elements attr = element.getElementsByTag(HtmlScheme.IFRAME.getTag());
        String videoURL = attr.get(0).attributes().get("src");

        boolean hasScheme = videoURL.contains(Protocol.HTTP) || videoURL.contains(Protocol.HTTPS);

        if (!hasScheme) {
            videoURL = String.format("%s%s", baseURL, videoURL);
        }

        String fullVideoURL = videoURL;
        String videoID = null;

        String normalPath = "watch?v";
        String embedPath = "/embed/";
        String shortPath = "youtu.be";
        String videoKey = "v";

        if (videoURL.contains(normalPath)) {
            videoID = URLs.getQueryParameter(videoURL, videoKey);
        }
        if (videoURL.contains(embedPath) || videoURL.contains(shortPath)) {
            if (videoURL.contains("?")) {
                fullVideoURL = videoURL.substring(0, videoURL.indexOf("?"));
            }
            String[] items = fullVideoURL.split("/");
            videoID = items[items.length - 1];
        }

        String youtubePlaceholderPattern = "http://img.youtube.com/vi/%s/0.jpg";
        String imageURL = String.format(youtubePlaceholderPattern, videoID);
    }
}
