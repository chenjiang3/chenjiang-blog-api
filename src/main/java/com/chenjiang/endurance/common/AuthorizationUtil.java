package com.chenjiang.endurance.common;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationUtil {

    public static Map<String, String> parseAuthInfo(String authorization) {
        String[] e = authorization.split("&");
        Map<String, String> auth = new HashMap<>();
        for (String s : e) {
            String[] kv = s.split("=");
            if (kv[0] != null && kv[1] != null) {
                auth.put(kv[0], kv[1]);
            }
        }
        return auth;
    }

}
