package com.chenjiang.endurance.common;

import org.apache.ibatis.jdbc.AbstractSQL;
import org.springframework.util.StringUtils;

public class SQL extends AbstractSQL<SQL>{

    @Override
    public SQL getSelf() {
        return this;
    }

    public SQL VALUES_IF(String columns, String values, boolean ifExits) {
        if (ifExits) {
            super.VALUES(columns, values);
        }
        return this;
    }

    public SQL VALUES_IF(String columns, String values, boolean ifExits, String otherwise) {
        if (ifExits) {
            super.VALUES(columns, values);
        } else {
            super.VALUES(columns, otherwise);
        }
        return this;
    }

    public SQL WHERE_IF(String conditions, boolean ifExits) {
        if (ifExits) {
            super.WHERE(conditions);
        }
        return this;
    }

    public SQL SET_IF(String sets, boolean ifExits, String otherwise) {
        if (ifExits) {
            SET(sets);
        } else if (!StringUtils.isEmpty(otherwise)) {
            SET(otherwise);
        }
        return this;
    }

    public SQL SET_IF(String sets, boolean ifExits) {
        return SET_IF(sets, ifExits, null);
    }

}
