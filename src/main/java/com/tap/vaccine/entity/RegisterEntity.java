package com.tap.vaccine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="register_table")
public class RegisterEntity {
	
	
	@Column(name="ID")
	@Id
	private int id;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="MOBILE_NUMBER")
	private long mobileNumber;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="DOB")
	private String dob;

	@Column(name="LOGIN_ATTEMPTS")
	private Integer loginAttempts;
	
	@Column(name="MEMBER_COUNT")
	private int member_count;
	
	public RegisterEntity() {
		System.out.println("Entity Object..");
	}

	public RegisterEntity(String userName, String email, String password, long mobileNumber, String gender,
			String dob) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dob = dob;
	}

	public RegisterEntity(int id, String userName, String email, String password, long mobileNumber, String gender,
			String dob) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dob = dob;
	}

	public RegisterEntity(String userName, String email, String password, long mobileNumber, String gender,
			String dob, int loginAttempts) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dob = dob;
		this.loginAttempts = loginAttempts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public int getMember_count() {
		return member_count;
	}

	public void setMember_count(int member_count) {
		this.member_count = member_count;
	}

	@Override
	public String toString() {
		return "RegisterEntity [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + ", gender=" + gender + ", dob=" + dob + ", loginAttempts="
				+ loginAttempts + ", member_count=" + member_count + "]";
	}
	
}
