/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class User {
    /*[userId] [int] IDENTITY(1,1) NOT NULL,
	[fullName] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [varchar](255) NULL,
	[image] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[accountBalance] [money] NULL,
	[dob] [date] NULL,
	[gender] [bit] NULL,
	[acccountId] [int] NULL,*/
    private int userId;
    private String fullName;
    private String address;
    private String image;
    private double accountBalance;
    private Date dob;
    private boolean gender;
    private int accountId;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    public User() {
    }

    public User(int userId, String fullName, String image, int accountId) {
        this.userId = userId;
        this.fullName = fullName;
        this.image = image;
        this.accountId = accountId;
    }

    public User(int userId, String fullName, String image) {
        this.userId = userId;
        this.fullName = fullName;
        this.image = image;
    }
    
    
    
    public User(int userId, String fullName, String address, String image, double accountBalance, Date dob, boolean gender, int accountId) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.image = image;
        this.accountBalance = accountBalance;
        this.dob = dob;
        this.gender = gender;
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", image=" + image + ", accountBalance=" + accountBalance + ", dob=" + dob + ", gender=" + gender + ", accountId=" + accountId + '}';
    }

    public short getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
