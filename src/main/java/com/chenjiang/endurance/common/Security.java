package com.chenjiang.endurance.common;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Security {

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String passwordWithSalt(String password, String salt) {
        String data = Hashing.sha256()
                .hashString(salt + "-" + password, StandardCharsets.UTF_8)
                .toString();
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String sha256(String message) {
        String data = Hashing.sha256()
                .hashString(message, StandardCharsets.UTF_8)
                .toString();
        return data;
    }

}
