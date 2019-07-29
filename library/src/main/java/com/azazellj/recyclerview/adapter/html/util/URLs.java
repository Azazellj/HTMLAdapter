package com.azazellj.recyclerview.adapter.html.util;

import android.support.annotation.Nullable;

/**
 * Created by azazellj on 3/1/19.
 */
public class URLs {
    @Nullable
    public static String getQueryParameter(final String url, final String key) {
        if (!url.contains("?")) {
            return null;
        }

        String query = url.substring(url.indexOf("?") + 1);

        String[] params = query.split("&");
        for (String param : params) {
            String[] pair = param.split("=");
            if (pair[0].equals(key)) {
                return pair[1];
            }
        }
        return null;
    }
}
