package com.imoney.payementmotor.payement;

import com.imoney.payementmotor.audit.Audit;
import com.sun.istack.Nullable;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transactions")
public class Transaction  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=25)
    @Column(name = "transaction_id")
    private String transactionid;

    @NotNull
    @Size(max=20)
    @Column(name = "operator_number")
    private String operatornumber;

    @Nullable
    @Size(max=20)
    @Column(name = "customer_number")
    private  String customernumber;

    @NotNull
    @Size(max=20)
    @Column(name = "payment_id")
    private String paymentid;

    @NotNull
    @NumberFormat
    @Column(name = "payment_status")
    private int paymentstatus;

    @NotNull
    @NumberFormat
    @Column(name = "amount")
    private double amount;

    @Nullable
    @NumberFormat
    @Column(name = "calback_status")
    private int calbackstatus;

    @Nullable
    @Size(max=50)
    @Column(name = "calback_message")
    private String calbackmessage;

    @Nullable
    @Size(max=10)
    @Column(name = "currency")
    private String currency;

    @Nullable
    @Column(name = "title")
    @Size(max=50)
    private String title;

    @Nullable
    @Size(max=100)
    @Column(name = "description")
    private String Description;

    public Transaction(Long id, String transactionid, String operatornumber, String customernumber, String paymentid, int paymentstatus, double amount, int calbackstatus, String calbackmessage, String currency, String title, String description) {
        this.id = id;
        this.transactionid = transactionid;
        this.operatornumber = operatornumber;
        this.customernumber = customernumber;
        this.paymentid = paymentid;
        this.paymentstatus = paymentstatus;
        this.amount = amount;
        this.calbackstatus = calbackstatus;
        this.calbackmessage = calbackmessage;
        this.currency = currency;
        this.title = title;
        Description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getOperatornumber() {
        return operatornumber;
    }

    public void setOperatornumber(String operatornumber) {
        this.operatornumber = operatornumber;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public int getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(int paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCalbackstatus() {
        return calbackstatus;
    }

    public void setCalbackstatus(int calbackstatus) {
        this.calbackstatus = calbackstatus;
    }

    public String getCalbackmessage() {
        return calbackmessage;
    }

    public void setCalbackmessage(String calbackmessage) {
        this.calbackmessage = calbackmessage;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
