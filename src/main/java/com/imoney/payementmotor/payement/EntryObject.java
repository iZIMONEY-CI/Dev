package com.imoney.payementmotor.payement;

public class EntryObject {
    private double amount;
    private String phonenumber;
    private String title;

    public EntryObject() {
    }

    public EntryObject(double amount, String phonenumber, String title) {
        this.amount = amount;
        this.phonenumber = phonenumber;
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
