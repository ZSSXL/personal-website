package com.zss.personalspace.util;

import java.util.UUID;

/**
 * @author ZSS
 * @date 2019/8/16 18:59
 * @description UUID生成
 */
public class UUIDUtil {

    /**
     * 生成id
     *
     * @return String
     */
    public static String getUuid() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "");
    }
}
