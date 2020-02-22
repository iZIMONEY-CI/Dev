package com.imoney.payementmotor.enums;

public enum  ActeurCodeEnum {
    VISAMASTERCARD("01"),
    MOOV("02"),
    MTN("03"),
    ORG("04");

    private String url;
    ActeurCodeEnum(String url)
    {
        this.url=url;
    }

    public String url()
    {
        return url;
    }
}
