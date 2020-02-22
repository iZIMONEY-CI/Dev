package com.imoney.payementmotor.paymentmod;

import com.imoney.payementmotor.audit.Audit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "paymentmods")
public class PaymentMod extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=5)
    @Column(name = "country_code")
    private String countrycode;

    @NotNull
    @Size(max=5)
    @Column(name = "mod_code")
    private String modcode;

    @NotNull
    @Size(max=20)
    @Column(name = "description")
    private String description;

    public PaymentMod(Long id, String countrycode, String modcode, String description) {
        this.id = id;
        this.countrycode = countrycode;
        this.modcode = modcode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getModecode() {
        return modcode;
    }

    public void setModecode(String modecode) {
        this.modcode = modecode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
