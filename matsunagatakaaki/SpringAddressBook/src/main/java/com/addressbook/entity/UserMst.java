package com.addressbook.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the user_mst database table.
 * エンティティクラス
 */
@Entity
@Table(name="user_mst")
@NamedQuery(name="UserMst.findAll", query="SELECT u FROM UserMst u")
public class UserMst implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @NotEmpty
    private String address;

    @Column
    @NotEmpty
    @Pattern(regexp="^[\\D]+$")
    private String name;

    @NotEmpty
    @Column
    @Pattern(regexp = "^[0-9]{10,11}$")
    private String tel;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}