package com.xxx.utils.url;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
    public static Map<String, String> toMap(String url) {
        Map<String, String> map = null;

        if (url != null && url.indexOf("&") > -1 && url.indexOf("=") > -1) {
            map = new HashMap<String, String>();

            String[] arrTemp = url.split("&");
            for (String str : arrTemp) {
                String[] qs = str.split("=");
                if (qs.length == 2) {
                	map.put(qs[0], qs[1]);
                }
            }
        }

        return map;
    }

    public static String getQueryString(String url, String name) {
        return toMap(url).get(name);
    }
}
