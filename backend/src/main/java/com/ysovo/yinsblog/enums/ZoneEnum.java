package com.ysovo.yinsblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ys
 * @date 2023/03/26
 */
@Getter
@AllArgsConstructor
public enum ZoneEnum {

    /**
     * 上海
     */
    SHANGHAI("Asia/Shanghai", "中国上海");

    /**
     * 时区
     */
    private final String zone;

    /**
     * 描述
     */
    private final String desc;

}
