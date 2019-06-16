package com.bit.sc.common;

public enum ResidentCode {
    HJRK(1, "户籍人口"),
    LDRK(2, "流动人口"),
    JWRK(3, "境外人口");

    private int code;
    private String info;

    ResidentCode(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }
}
