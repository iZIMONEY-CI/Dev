package com.imoney.payementmotor.enums;

public enum PaymentStatus {
    PENDING(0),
    SUCCES(1),
    REFUSED(2),
    ERROR(3);

    private int url;
    PaymentStatus(int url)
    {
        this.url=url;
    }

    public int url()
    {
        return url;
    }
}
