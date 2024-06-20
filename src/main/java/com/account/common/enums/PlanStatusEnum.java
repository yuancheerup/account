package com.account.common.enums;

public enum PlanStatusEnum {
    NO_START("未开始"),
    IS_PROCESS("进行中"),
    END("已结束"),
    DO_END("已完成");

    private String value;

    PlanStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
