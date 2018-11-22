package com.chenjiang.endurance.common;

import java.util.Date;

public class TimeUtil {

    public static Long currentTS() {
        return new Date().getTime() / 1000;
    }

}
