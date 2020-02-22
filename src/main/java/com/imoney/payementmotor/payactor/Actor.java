package com.imoney.payementmotor.payactor;


import com.imoney.payementmotor.audit.Audit;
import com.sun.istack.Nullable;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "operators")
public class Actor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Nullable
    @Size(max=8)
    @Column(name = "vpc_Version")
    private String vpc_Version;

    @Nullable
    @Size(max=16)
    @Column(name = "vpc_Command")
    private String vpc_Command;

    @Nullable
    @Size(max = 8)
    @Column(name = "vpc_AccessCode")
    private String vpc_AccessCode;

    @Nullable
    @Size(max = 16)
    @Column(name = "vpc_Merchant")
    private String vpc_Merchant;

    @Nullable
    @Size(max = 10)
    @Column(name = "code")
    private String code;

    public Actor() {
    }

    public Actor(Long id, @Size(max = 8) String vpc_Version, @Size(max = 16) String vpc_Command, @Size(max = 8) String vpc_AccessCode, @Size(max = 16) String vpc_Merchant, @Size(max = 10) String code) {
        this.id = id;
        this.vpc_Version = vpc_Version;
        this.vpc_Command = vpc_Command;
        this.vpc_AccessCode = vpc_AccessCode;
        this.vpc_Merchant = vpc_Merchant;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVpc_Version() {
        return vpc_Version;
    }

    public void setVpc_Version(String vpc_Version) {
        this.vpc_Version = vpc_Version;
    }

    public String getVpc_Command() {
        return vpc_Command;
    }

    public void setVpc_Command(String vpc_Command) {
        this.vpc_Command = vpc_Command;
    }

    public String getVpc_AccessCode() {
        return vpc_AccessCode;
    }

    public void setVpc_AccessCode(String vpc_AccessCode) {
        this.vpc_AccessCode = vpc_AccessCode;
    }

    public String getVpc_Merchant() {
        return vpc_Merchant;
    }

    public void setVpc_Merchant(String vpc_Merchant) {
        this.vpc_Merchant = vpc_Merchant;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", vpc_Version='" + vpc_Version + '\'' +
                ", vpc_Command='" + vpc_Command + '\'' +
                ", vpc_AccessCode='" + vpc_AccessCode + '\'' +
                ", vpc_Merchant='" + vpc_Merchant + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
