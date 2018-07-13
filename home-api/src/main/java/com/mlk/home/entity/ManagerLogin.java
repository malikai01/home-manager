package com.mlk.home.entity;

import com.mlk.home.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by malikai on 2018-5-22.
 */
@Table(name = "manager_login")
public class ManagerLogin extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "password")
    private String password;

    @Column(name = "register_time")
    private Date registerTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "login_nick_name")
    private String loginNickName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_number_attachId")
    private Integer cardNumberAttachId;

    @Column(name = "card_number_attachId2")
    private Integer cardNumberAttachId2;

    @Column(name = "phone")
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getLoginNickName() {
        return loginNickName;
    }

    public void setLoginNickName(String loginNickName) {
        this.loginNickName = loginNickName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardNumberAttachId() {
        return cardNumberAttachId;
    }

    public void setCardNumberAttachId(Integer cardNumberAttachId) {
        this.cardNumberAttachId = cardNumberAttachId;
    }

    public Integer getCardNumberAttachId2() {
        return cardNumberAttachId2;
    }

    public void setCardNumberAttachId2(Integer cardNumberAttachId2) {
        this.cardNumberAttachId2 = cardNumberAttachId2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
