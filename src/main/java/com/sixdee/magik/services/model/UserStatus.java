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
 * @since Feb 19, 2018 : 4:22:44 PM
 */

@Entity
@Table(name = "MFS_USER_STATUS")
public class UserStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int primId;

    @Column(name = "STATUS", nullable = false)
    private String status;

    /** -----------------------------------------------------. **/

    public int getPrimId() {
        return primId;
    }

    public void setPrimId(int primId) {
        this.primId = primId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}