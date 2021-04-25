package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Sep 7, 2018 : 6:25:47 PM
 */

@Entity
@Table(name = "MFS_PASSWORD_HISTORY")
public class PasswordHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PASSWORD_1")
    private String password1;

    @Column(name = "PASSWORD_2")
    private String password2;

    @Column(name = "PASSWORD_3")
    private String password3;

    @Column(name = "PASSWORD_4")
    private String password4;

    @Column(name = "PASSWORD_5")
    private String password5;

    @Column(name = "PASSWORD_6")
    private String password6;

    @Column(name = "PASSWORD_7")
    private String password7;

    @Column(name = "PASSWORD_8")
    private String password8;

    @Column(name = "PASSWORD_9")
    private String password9;

    @Column(name = "PASSWORD_10")
    private String password10;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword3() {
        return password3;
    }

    public void setPassword3(String password3) {
        this.password3 = password3;
    }

    public String getPassword4() {
        return password4;
    }

    public void setPassword4(String password4) {
        this.password4 = password4;
    }

    public String getPassword5() {
        return password5;
    }

    public void setPassword5(String password5) {
        this.password5 = password5;
    }

    public String getPassword6() {
        return password6;
    }

    public void setPassword6(String password6) {
        this.password6 = password6;
    }

    public String getPassword7() {
        return password7;
    }

    public void setPassword7(String password7) {
        this.password7 = password7;
    }

    public String getPassword8() {
        return password8;
    }

    public void setPassword8(String password8) {
        this.password8 = password8;
    }

    public String getPassword9() {
        return password9;
    }

    public void setPassword9(String password9) {
        this.password9 = password9;
    }

    public String getPassword10() {
        return password10;
    }

    public void setPassword10(String password10) {
        this.password10 = password10;
    }

}