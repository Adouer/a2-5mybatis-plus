package com.adouer.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 枚举类设置小学、中学、高中
 */
public enum GradeEnum {

    PRIMARY(1, "小学"),  SECONDORY(2, "中学"),  HIGH(3, "高中");

    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库存的值是code
    @JsonValue    //标记响应json值
    private final int code;
    private final String descp;
}