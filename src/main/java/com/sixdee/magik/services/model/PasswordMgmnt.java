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
 * @since Jul 25, 2018 : 5:53:06 PM
 */

@Entity
@Table(name = "MFS_PASSWORD_MGMNT")
public class PasswordMgmnt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    private int type;

    @Column(name = "EXPIRY_DAYS", nullable = true)
    private int expiryDays;

    @Column(name = "CAPS_REQUIRED", nullable = true)
    private int capsReq;

    @Column(name = "SP_CHAR_REQ", nullable = true)
    private int spCharReq;

    @Column(name = "LENGTH", nullable = true)
    private int length;
    
  /*  @Column(name = "AVOID_CONSECUTIVE_CHARS", nullable = true)
    private int avoidConsec;*/
    
/*    @Column(name = "AVOID_SAME_CHAR", nullable = true)
    private int avoidSameChar;*/
    
	/*
	 * @Column(name = "THREE_CONSE-CHARS" , nullable = true) private int
	 * threeConsChars;
	 * 
	 * @Column(name = "THREE_CONSE_DIGITS_LETTERS" , nullable = true) private int
	 * threeConsDigitsLetters;
	 */
    


	/*
	 * public int getThreeConsDigitsLetters() { return threeConsDigitsLetters; }
	 * 
	 * public void setThreeConsDigitsLetters(int threeConsDigitsLetters) {
	 * this.threeConsDigitsLetters = threeConsDigitsLetters; }
	 */

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	/*
	 * public int getThreeConsChars() { return threeConsChars; }
	 * 
	 * public void setThreeConsChars(int threeConsChars) { this.threeConsChars =
	 * threeConsChars; }
	 */

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(int expiryDays) {
        this.expiryDays = expiryDays;
    }

    public int getCapsReq() {
        return capsReq;
    }

    public void setCapsReq(int capsReq) {
        this.capsReq = capsReq;
    }

    public int getSpCharReq() {
        return spCharReq;
    }

    public void setSpCharReq(int spCharReq) {
        this.spCharReq = spCharReq;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

	/*public int getAvoidConsec() {
		return avoidConsec;
	}

	public void setAvoidConsec(int avoidConsec) {
		this.avoidConsec = avoidConsec;
	}*/
/*
	public int getAvoidSameChar() {
		return avoidSameChar;
	}

	public void setAvoidSameChar(int avoidSameChar) {
		this.avoidSameChar = avoidSameChar;
	}*/

}