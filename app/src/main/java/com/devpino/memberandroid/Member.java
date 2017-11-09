package com.devpino.memberandroid;

import java.io.Serializable;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6800961906229486245L;

	private int no;
	private String memberName = null;
	private String email = null;

    public static final String KEY_EMAIL = "email";
    public static final String KEY_MEMBER_NAME = "member_name";

    public static final String KEY_ROWID = "_id";


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
